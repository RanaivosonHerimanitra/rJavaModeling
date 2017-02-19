/*
 * Diseases.java
 * 
 */
package ews;

import java.util.ArrayList;

public class Diseases {
	private ArrayList<String> alertStatus = new ArrayList<>();
	private ArrayList<String> pastValues = new ArrayList<>();
	private ArrayList<String> sites = new ArrayList<>();
	private ArrayList<String> alertWeek= new ArrayList<>();
	private ArrayList<Double> nbCases= new ArrayList<>();
    public Diseases ()
    {
    	
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
