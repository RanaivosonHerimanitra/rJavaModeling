package ews;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	//count number of columns that are null or empty
	public void testNoHeader() throws IOException
	{
			String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest0.csv";
			CSVReader csv= new CSVReader(csvFile,",",false);
			//csv.checkMalFormedHeaders();
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
	}
		
	// Sur PaluConfTest1:
	@Test
	public void testMalFormedHeader() throws IOException
	{
		String [] malFormedHeaders ={",","#","'",";","."};
		for (int u=1; u<3; u++)
		{
			String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest"+u+".csv";
			CSVReader csv= new CSVReader(csvFile,",",true);
			//csv.checkMalFormedHeaders();
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
}
