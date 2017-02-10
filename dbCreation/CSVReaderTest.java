package ews;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CSVReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//public void test() {
	//	fail("Not yet implemented");
	//}
	
	// Sur PaluConfTest0
		public void testNoHeader() throws IOException
		{
			String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest0.csv";
			CSVReader csv= new CSVReader(csvFile,",");
			ArrayList<String[]> mycsv= csv.readCSV();
			int Ncol = csv.getNumberCols();
			 for (int k=0; k<Ncol ; k++)
	    	 {
				 String [] mycolumn = mycsv.get(k);
				 assertTrue("Le nom de colonne" + k +" ne doit pas etre laisser vide",
						 csv.getNameOf(mycolumn)=="");
	    	 }
		}
		
		// Sur PaluConfTest1:
		public void testNullHeader() throws IOException
		{
			String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest1.csv";
			CSVReader csv= new CSVReader(csvFile,",");
			ArrayList<String[]> mycsv= csv.readCSV();
			int Ncol = csv.getNumberCols();
			 for (int k=0; k<Ncol ; k++)
	    	 {
				 String [] mycolumn = mycsv.get(k);
				 assertTrue("Le nom de colonne" + k +" ne doit pas etre laisser vide",
						 csv.getNameOf(mycolumn)=="");
	    	 }
		}
		
		
		// Sur PaluConfTest2:
		public void testNameColumn() throws IOException
		{
			String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest2.csv";
			CSVReader csv= new CSVReader(csvFile,",");
			ArrayList<String[]> mycsv= csv.readCSV();
			int Ncol = csv.getNumberCols();
			 for (int k=0; k<Ncol ; k++)
	    	 {
				 String [] mycolumn = mycsv.get(k);
				 assertTrue("Le nom de colonne" + k +" ne doit pas contenir de guillement",
						 csv.getNameOf(mycolumn)=="'");
	    	 }
			
		}
	
	// Sur PaluConfTest3:
	public void testSepHeader() throws IOException
	{
		String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest3.csv";
		CSVReader csv= new CSVReader(csvFile,",");
		ArrayList<String[]> mycsv= csv.readCSV();
		int Ncol = csv.getNumberCols();
		 for (int k=0; k<Ncol ; k++)
    	 {
			 String [] mycolumn = mycsv.get(k);
			 assertTrue("Le nom de colonne" + k +" ne doit pas contenir de virgule",
					 csv.getNameOf(mycolumn)==",");
    	 }
	}
		
	public void testMeanColumn(String [] val)
	{
		
	}

}
