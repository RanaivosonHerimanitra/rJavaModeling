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
	private boolean isAppended = false;
	CSVReader mycsv ;
    public Diseases ()
    {
    	
    }
    //
    public boolean hasBeenAppended ()
    {
    	return isAppended;
    }
    //
    public void addNewSite(String origin, String dest, String newFile) throws IOException {
    	if (origin.equals("") || dest.equals("") || newFile.equals("") )
    	{
    		isAppended=false;
    	} else {
    		BufferedReader br = new BufferedReader(new FileReader(new File(origin)));
    		BufferedReader br1 = new BufferedReader(new FileReader(new File(dest)));
    		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(newFile)));
    		String lineOrg;
    		String lineDest;
    		while((lineOrg = br.readLine()) != null && (lineDest =br1.readLine())!=null  ) {
    			 System.out.println("AVANT: " + lineDest);
    			 if ( lineOrg.split(",").length==3)
    			 {
    				 lineDest = lineDest + "," + lineOrg.split(",")[2] ; 
    			 } else {
    				 lineDest = lineDest + "," + " " ; 
    			 }
    			 System.out.println("APRES: " + lineDest);
    			 bw.write(lineDest);
    			 bw.newLine();
    		}
    		isAppended=true;
    		bw.close();br.close();br1.close();
    	}
	}
    //
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
