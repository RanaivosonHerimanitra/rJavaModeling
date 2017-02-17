/*
 * A disease has an evolution of cases number for a given geographical site.
 * TODO: refactor every arrays (single) to a LinkList: 
 * http://beginnersbook.com/2013/12/linkedlist-in-java-with-example/
 */

package ews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.math3.random.EmpiricalDistribution;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

public class Sites {
	private String filepathSite;
	private String siteId;
	private String [] monitoredDiseases;
	private String [] sitesList;
	private double rank;
	CSVReader csv ;
	ArrayList<String[]> mycsv ;
	// a constructor for the site
	public  Sites (String path) throws IOException
	{
		setFilePath(path);
		//sitesList = new String [54];
		//should read in database
		csv = new CSVReader(filepathSite,",",true);
		mycsv = csv.readCSV();
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
	public String[] getAlertStatusFor(String id, double rk) throws IOException
	{
		setSiteId(id);
		setRank(rk);
		double [] myvector = (double[]) getVector(siteId,true);
		ArrayUtils.reverse(myvector);
		String [] myalert = new String[myvector.length];
		double valueAtPercRank= getPercentileValueAt(rank,siteId);
		//loop and if < valueAtPercRank then normal otherwise in alert:
		myalert[0]="";
		myalert[1]="";
		myalert[2]="";
		for (int i =0 ; i< (myvector.length-3);i++)
		{
			double [] temp = {myvector[i],myvector[i+1],myvector[i+2]};
			List<Double> myLastThreeValues= Arrays.asList( ArrayUtils.toObject(temp));
			double maxThreeValues= (double) Collections.max(myLastThreeValues);
			if (  maxThreeValues>= valueAtPercRank)
			{
				myalert[i+2]="alert";
			} else {
				myalert[i+2]="normal";
			}
		}
		//then reverse:
		ArrayUtils.reverse(myalert);
		return myalert;
	}
	//return index of a given week
	public int searchIndex(String colname,String val)
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
	
	//should be of the form: "YYYY_WW"
	public String[] getAlertStatusForWeek(String wk) throws IOException
	{
		String[] myalert  = getAlertStatusFor(siteId,rank);
		int myindex = searchIndex("code",wk);
		System.out.println("INDEX OF CODE " + myindex);
		System.out.println("LENGTH OF ALERTS " + myalert.length);
		//init the array to be returned using myindex:
		String[] myalert2 = new String[myalert.length-myindex+1];
		int k=0;
		System.out.println("LENGTH OF ALERTS2 " + myalert2.length);
		for ( int i=myindex; i<myalert2.length ;i++)
		{
			myalert2[k]=myalert[i];
			k++;
		}
		return myalert2;
	}
	public String getSiteId ()
	{
		return siteId;
	}
	// get list of all sites in alert and by week
	public LinkedHashMap<String, String[] > getHistoricalStatus() throws IOException
	{
		LinkedHashMap<String, String[] > sitesList = new LinkedHashMap<String, String[] >();
		//get all siteId first:
		int Ncol = csv.getNumberCols();
		String[] myvalues;
		String mycols;
		//get all status second:
		for ( int k =0; k<Ncol; k++)
		{
			myvalues=csv.getColumnValues(k);
			mycols = csv.getNameOf(myvalues);
			if ( !mycols.equals("deb_sem") && !mycols.equals("code") )
			{
				sitesList.put(mycols, getAlertStatusFor(mycols,90) );
			}	
		}
		//return a LinkedHashMap:
		return sitesList;
	}

	public String [] getMonitoredDiseases(String id) 
	{
		//read somewhere and return
		return monitoredDiseases;
	}
	
	//get double vector corresponding to a column name:
	public Object getVector (String siteId,boolean convert)
	{
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
	public double getPercentileValueAt(double rank,String siteId) throws IOException
	{	
		double[] myvector = (double[]) getVector(siteId,true);	
		double percentile = new Percentile().evaluate(myvector, rank);
		//EmpiricalDistribution distribution = new EmpiricalDistribution(myvector.length);
	   // distribution.load(myvector);
	    //double percentile = distribution.cumulativeProbability(4);
	    return percentile;
	}
	public double [] propSitesAlert()
	{
		double [] z = {1.,2.}; //should read somewhere in a db/file?!!
		return z;
	}
}