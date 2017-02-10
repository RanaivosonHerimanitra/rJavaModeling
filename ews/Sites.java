/*
 * A disease has an evolution of cases number for a given geographical site.
 */
package ews;

import java.io.IOException;
import java.util.ArrayList;

public class Sites {
	private String filepathSite;
	private String siteId;
	private String [] monitoredDiseases;
	private String [] sitesList;
	private boolean alertStatus;
	
	// a constructor for the site
	public  Sites ()
	{
		filepathSite ="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/sentinel_codes.csv";
		sitesList = new String [54];
		//then should loop and fill:
	}
	// methods:
	public boolean alertStatus(String id)
	{
		//should read in database that contains output status given id
		siteId=id;
		return alertStatus;
	}
	public String getSiteId ()
	{
		return siteId;
	}
	public ArrayList<String[]> readLocalData() throws IOException
	{
		//should read in database
		CSVReader conn = new CSVReader(filepathSite,",");
		ArrayList<String[]> mydata = conn.readCSV();
		return mydata;
	}
	public String [] getMonitoredDiseases(String id) 
	{
		//read somewhere and return
		return monitoredDiseases;
	}
	public void prepareToMap(String id, String idDisease, boolean partial)
	{
		
	}
	public double [] getWeeklyNbCasesData(String id, String idDisease)
	{
		double [] z = {1.,2.}; //should read somewhere in a db/file?!!
		return z;
	}
	// get list of all sites
	public String [] getSitesList()
	{
		return sitesList;
	}
	
	public void loadSiteData(String id, String idDisease,boolean partial) 
	{
		//load data here using db:
	}
	public double [] propSitesAlert()
	{
		double [] z = {1.,2.}; //should read somewhere in a db/file?!!
		return z;
	}
	//see implementation on the console:
	public static void main(String[] args) throws IOException
	{
		Sites site = new Sites();
		ArrayList<String[]> mydata= site.readLocalData();
		for (int k=0; k<mydata.size(); k++)
		{
			System.out.println(mydata.get(k));
		}
		
	}
}
