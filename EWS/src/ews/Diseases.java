/*
 * Diseases.java
 * 
 */
package ews;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Diseases {
	private ArrayList<String> alertStatus = new ArrayList<>();
	private ArrayList<String> pastValues = new ArrayList<>();
	private ArrayList<String> sites = new ArrayList<>();
	private ArrayList<String> alertWeek= new ArrayList<>();
	private ArrayList<Double> nbCases= new ArrayList<>();
	CSVReader mycsv = new CSVReader(null, null, false);
    public Diseases ()
    {
    	
    }
    public  void addNewSite(String filepathOrigin, String filepathDestination, String newFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(filepathOrigin)));
		BufferedReader br1 = new BufferedReader(new FileReader(new File(filepathDestination)));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(newFile)));
		String sCurrentLineOrigin;
		String sCurrentLineDestination;
		while((sCurrentLineOrigin = br.readLine()) != null && (sCurrentLineDestination =br1.readLine())!=null  ) {

			 System.out.println("AVANT: " + sCurrentLineDestination);
			 if ( sCurrentLineOrigin.split(",").length==3)
			 {
				 sCurrentLineDestination = sCurrentLineDestination + "," + sCurrentLineOrigin.split(",")[2] ;
				 
			 } else {
				 sCurrentLineDestination = sCurrentLineDestination + "," + " " ;
				 
			 }
			 System.out.println("APRES: " + sCurrentLineDestination);
			 bw.write(sCurrentLineDestination);
			 bw.newLine();

		}

		bw.close();br.close();br1.close();
	}
    public void addNbCases(double val)
    {
    	nbCases.add(val);
    }
    public void addAlertStatus(String val)
    {
    	alertStatus.add(val);
    }
    public void addWeek(String val)
    {
    	alertWeek.add(val);
    }
    public void addPastValues(String val)
    {
    	pastValues.add(val);
    }
    public void addSites(String val)
    {
    	sites.add(val);
    }
    //getters:
    public ArrayList<String> getAlertStatus()
    {
    	return alertStatus;
    }
   
    public ArrayList<String> getSites()
    {
    	return sites;
    }
    public ArrayList<String> getAlertWeek()
    {
    	return alertWeek;
    }
    public ArrayList<Double> getNbCases()
    {
    	return nbCases;
    }
}
