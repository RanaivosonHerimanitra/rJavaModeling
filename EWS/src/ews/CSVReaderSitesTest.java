package ews;
import static org.junit.Assert.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CSVReaderSitesTest {

	@BeforeClass
	public static void setUp() throws Exception {
		System.out.println("DEBUT DES TESTS");
	}
	
	@AfterClass
	public static void setUp2() throws Exception {
		System.out.println("FIN DES TESTS");
	}

	@Test
	public void test() throws IOException, SQLException {
		
		ArrayList<String> alertObserved  = new ArrayList<String>();
		ArrayList<String> alertExpected  = new ArrayList<String>();
				
				
		//what we expect:
		alertExpected.add(0,"alert");
		alertExpected.add(1,"normal");
		alertExpected.add(2,"normal");
		alertExpected.add(3,"normal");
		alertExpected.add(4,"normal");
		alertExpected.add(5,"normal");
		
				
		/*
		 * Case 1, now add 03 consecutive values > rank=42 (90th percentile)
		 * Expected: Alert!
		 */
		String path2="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/data-ground-thruth/PaluConfTest6case1.csv";
		Sites mysite2 = new Sites(path2);
		mysite2.getAlertStatusFor("mae", 90.);
		Diseases disease = mysite2.getDiseases();
		ArrayList<String> alerts = disease.getAlertStatus();
		ArrayList<String> semaine = disease.getAlertWeek();
		ArrayList<Double> mae = disease.getNbCases();
		for (int i=mae.size()-1; i>mae.size()-5 ; i--)
		{
			System.out.println("Semaine: "+ semaine.get(i) + " Nbcases: "+ mae.get(i-1) + ", 90th perc="+ mysite2.getPercentileValueAt(90,"mae",semaine.get(i)) + ", statut:"+ alerts.get(i) );
			alertObserved.add(alerts.get(i) );
		}
		
		System.out.println("*********************************************");
				
		/*
		 * Case 2, now add 03 consecutive values < rank = 42 (90th percentile)
		*/
		String path3="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/data-ground-thruth/PaluConfTest6case2.csv";
		Sites mysite3 = new Sites(path3);
		mysite3.getAlertStatusFor("mae", 90.);
		Diseases disease3 = mysite3.getDiseases();
		ArrayList<String> alerts3 = disease3.getAlertStatus();
		ArrayList<String> semaine3 = disease3.getAlertWeek();
		ArrayList<Double> mae3 = disease3.getNbCases();
		for (int i=mae3.size()-1 ; i>mae3.size()-5; i--)
		{
			System.out.println("Semaine: "+ semaine3.get(i) + " Nbcases: "+ mae3.get(i-1) + ", 90th perc="+ mysite3.getPercentileValueAt(90,"mae",semaine.get(i)) + ", statut:"+ alerts3.get(i) );
			alertObserved.add(alerts3.get(i) );		
		}
		System.out.println("*********************************************");
		
		//expected vs actuals:	
		for (int u=0; u<alertExpected.size(); u++)
		 {	
			assertEquals(alertExpected.get(u) , alertObserved.get(u) );
		 }
	}
}
