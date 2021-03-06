/*
 * A disease has an evolution of cases number for a given geographical site.
 * http://beginnersbook.com/2013/12/linkedlist-in-java-with-example/
 */

package ews;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.LinkedHashMap;


import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.math3.random.EmpiricalDistribution;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

public class Sites  {
	Diseases disease = new Diseases();
	private String filepathSite;
	private String siteId;
	private String [] monitoredDiseases;
	LinkedHashMap<String, String[] > sitesList = new LinkedHashMap<String, String[] >();
	private double rank;
	CSVReader csv ;
	ArrayList<String[]> mycsv ;
	
	// a constructor for the site
	public  Sites (String path) throws IOException, SQLException
	{
		setFilePath(path);
		csv = new CSVReader(filepathSite,",",true,false);
		mycsv = csv.readCSV();
		rank=90;
	}
	
	//
	public void setSiteId(String str)
	{
		siteId=str;
	}
	public void setRank(double rk)
	{
		rank=rk;
	}
	public void setFilePath(String val)
	{
		filepathSite=val;
	}
	// methods:
	// by default, return status for the current week :
	public void getAlertStatusFor(String id, double rk) throws IOException, SQLException
	{
	
		setSiteId(id);
		setRank(rk);
		double [] myvector = (double[]) getVector(siteId,true);
		String  [] myweek = (String[]) getVector("code",false);
		ArrayUtils.reverse(myvector);
		ArrayUtils.reverse(myweek);
		String [] myalert = new String[myvector.length];
		double valueAtPercRank= 0;
		//loop and if < valueAtPercRank then normal otherwise in alert:
		myalert[0]="normal";
		myalert[1]="normal";
		myalert[2]="normal";
		for (int i =3 ; i< myvector.length;i++)
		{
			valueAtPercRank= getPercentileValueAt(rank,siteId,myweek[i]);
			boolean anormal =  ( (myvector[i-1] -valueAtPercRank) >=0.00001) &&  ( (myvector[i-2] -valueAtPercRank) >=0.00001) &&  ( (myvector[i-3] -valueAtPercRank) >=0.00001);
			if ( anormal)
			{
				myalert[i]="alert";
				//System.out.println(myweek[i]+" est en alerte car "+ myvector[i-1] +","+myvector[i-2]+","+myvector[i-3]+" ont depasse le 90th perc:"+valueAtPercRank);
				disease.addAlertStatus("alert");
				//past 03 values:
				disease.addPastValues(myvector[i-1] +"-"+myvector[i-2]+"-"+myvector[i-3]);
				disease.addSites(siteId);
				disease.addWeek(myweek[i]);
				disease.addNbCases(myvector[i]);
			} else {
				myalert[i]="normal";
				disease.addAlertStatus("normal");
				//revoir ici car redondance
				disease.addPastValues(myvector[i-1] +"-"+myvector[i-2]+"-"+myvector[i-3]);
				disease.addSites(siteId);
				disease.addWeek(myweek[i]);
				disease.addNbCases(myvector[i]);
			}
		}
		//then reverse:
		//ArrayUtils.reverse(myalert);
		//return myalert;
	}
	//return index of a given week
	public int searchIndex(String colname,String val) throws IOException, SQLException
	{
		int myindex=-1;
		String[] myweeks= (String[]) getVector(colname,false);
		for (int i=0; i< myweeks.length;i++)
		{
			if ( myweeks[i].equals(val) )
			{
				myindex = i;
				break;
			}
		}
		return myindex;
	}
	
	
	public String getSiteId ()
	{
		return siteId;
	}
	// get list of all sites in alert and by week
	

	public String [] getMonitoredDiseases(String id) 
	{
		//read somewhere and return
		return monitoredDiseases;
	}
	
	//get double vector corresponding to a column name:
	public Object getVector (String siteId,boolean convert) throws IOException, SQLException
	{
		CSVReader csv= new CSVReader(filepathSite,",",true,false);
   	    ArrayList<String[]> mycsv= csv.readCSV();
		//loop thru all index and search for siteId:
	    String [] mycolumn = mycsv.get(0);
		String colName = csv.getNameOf(mycolumn);
		
		double[] myvector = new double[mycolumn.length-1];
		   	    if ( !colName.equals(siteId) )
		   	    {
		   	    	for (int index=1; index<mycsv.size();index++)
		   			{
		   				mycolumn = mycsv.get(index);
		   				colName = csv.getNameOf(mycolumn);
		   				if ( siteId.equals(colName) )
		   				{
		   					//find the index corresponding to siteId ,passed in arguments
		   					if (convert==false)
		   					{
		   						mycolumn= mycsv.get(index);
		   					} else {
		   					//then convert to double,convert and add, skip 1st column which is col's name
			   					for (int i=1 ; i< (mycolumn.length-1) ; i++)
			   					{
			   						if( mycolumn[i].isEmpty() || mycolumn[i].equals("") ) {
			   							myvector[i]= 0.;
			   						} else {
			   							myvector[i]= Double.parseDouble(mycolumn[i]);
			   						}
			   		    		}
		   					}
		   					break ;
		   				}
		   			}
		   	    }
		  if (convert==true) {
			  return myvector;
		  } else {
			  return mycolumn;
		  }
	}
	
	/* renvoie la valeur du percentile 
	 * correspondant à une période du jeu de données
	 */
	public double getPercentileValueAt(double rank,String siteId,String wk) throws IOException, SQLException
	{	
		//System.out.println("searching for: " + wk);
		double[] myvector = (double[]) getVector(siteId,true);	
		String[] myweek = (String[]) getVector("code",false);
		//for (int k=0;k<myvector.length;k++)
		//{
		//	System.out.println(siteId + "= " + myvector[k] );
		//}
		//get index of the supplied code week:
		int index_wk=-1;
		for ( int k=0; k<myweek.length;k++)
		{
			//System.out.println("myweek[k]="+ myweek[k]+ ", wk="+ wk);
			if ( myweek[k].equals(wk) )
			{
				index_wk=k;
				break;
			}
		}
		// get vector:
		int u=0;
		double[]  myvector_sliced = new double[myweek.length - index_wk +1] ;
		for (int k=index_wk;k<myweek.length-1;k++)
		{
			myvector_sliced[u] = myvector[k];
			u++;
		}
		double percentile = new Percentile().evaluate(myvector_sliced, rank);
	    return percentile;
	}
	//return static about a given disease
	//especially past alerts and stuff
	public Diseases getDiseases()
	{
		return disease;
	}
	
}
