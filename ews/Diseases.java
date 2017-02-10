package ews;

public class Diseases {
    private String filepathDisease;
	private String diseaseId;
	private String [] sitesConcerned;
	private String [] diseasesList;
	
	// a constructor for Disease
	public  Diseases()
	{
		setDiseaseId("malaria");
		setFilepathDisease("/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data");
		setDiseasesList(new String[3]);
		//Then should loop and fill
		sitesConcerned = new String[54];
	}
	//return all sites concerned by a given disease
	public String [] getSitesConcerned (String diseaseId)
	{
		return sitesConcerned;
	}
	public double [] numberOfCases (String idDisease) 
	{
		
		double [] z = {1.,2.}; //should read somewhere in a db/file?
		return z;
	}
	public void loadData(String id, boolean partial) 
	{
		//load data here using db:
	}
	public double [] getHistoricalAlerts(String idDisease)
	{
		double [] z = {1.,2.}; //should read somewhere in a db/file?
		return z;
	}
	public double [] getHistoricalPercrankVal(String idDisease)
	{
		double [] z = {1.,2.}; //should read somewhere in a db/file?
		return z;
	}
	public String getFilepathDisease() {
		return filepathDisease;
	}
	public void setFilepathDisease(String filepathDisease) {
		this.filepathDisease = filepathDisease;
	}
	public String getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String [] getDiseasesList() {
		return diseasesList;
	}
	public void setDiseasesList(String [] diseasesList) {
		this.diseasesList = diseasesList;
	}
}
