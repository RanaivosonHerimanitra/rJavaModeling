/*
 * Unit tests with JUnit, RANAIVOSON Herimanitra
 */
package ews;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CSVReaderTest {
	
	
	

	@Before
	public void setUp()  {
		//ne doit pas prendre de parametres!!
		//csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest" + i +".csv";
		//csv= new CSVReader(csvFile,",",false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//count number of columns that are null or empty in case where user says no header:
	public void testNoHeader() throws Exception 
	{
			String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest0.csv";
			CSVReader csv= new CSVReader(csvFile,",",false);
			
		    ArrayList<String[]> mycsv= csv.readCSV();
			int ncol = csv.getNumberCols();
			int colNull = 0;
			for (int k=0; k<ncol ; k++)
	    	 {
				 String [] mycolumn = mycsv.get(k);
				 if ( csv.getNameOf(mycolumn)=="" || csv.getNameOf(mycolumn).isEmpty() )
				 {
					 colNull +=1;
				 } 
	    	 }
			System.out.println("Ncol: "+ncol+" colNull: "+colNull);
			assertTrue("La premiere ligne du fichier doit contenir les noms de variables",
					 colNull!=ncol);
			//on peut aussi faire ca:
			//assertFalse("fdfdfd",colNull==ncol);
			//assertEquals("fsfsf",colNull, ncol);
	}
		
	// 
	@Test
	public void testMalFormedHeader() throws Exception
	{
		String [] malFormedHeaders ={",","#","'",";","."};
		for (int u=1; u<3; u++)
		{
			String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest"+u+".csv";
			CSVReader csv= new CSVReader(csvFile,",",true);
			//setUp( String.valueOf(u) );
			ArrayList<String[]> mycsv= csv.readCSV();
			int Ncol = csv.getNumberCols();
			for (int k=0; k<Ncol ; k++)
	    	{
				 String [] mycolumn = mycsv.get(k);
				 boolean cond = Arrays.asList(malFormedHeaders).contains(csv.getNameOf(mycolumn));
				 if (cond)
				 {
					 assertTrue("Le nom de colonne" + k +" est non valide!",
							cond==false);
				 }
	    	}	
		}
	}
	//le fichier n'existe pas:
	@Test
	public void testNoFichier () throws Exception
	{
		String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest5.csv";
		CSVReader csv= new CSVReader(csvFile,",",true);
		//setUp("5");
		assertTrue("Ne doit pas lire un fichier vide", csv.getEmpty()==false);
	}
}