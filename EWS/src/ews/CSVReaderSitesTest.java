package ews;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CSVReaderSitesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		
		LinkedList<String> alertObserved  = new LinkedList<String>();
		LinkedList<String> alertExpected  = new LinkedList<String>();
				
				
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
		String path2="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest6case1.csv";
		Sites mysite2 = new Sites(path2);
		mysite2.getAlertStatusFor("mae", 90.);
		Diseases disease = mysite2.getDiseases();
		ArrayList<String> alerts = disease.getAlertStatus();
		ArrayList<String> semaine = disease.getAlertWeek();
		ArrayList<Double> mae = disease.getNbCases();
		for (int i=mae.size()-1; i>mae.size()-5 ; i--)
		{
			System.out.println("Semaine: "+ semaine.get(i) + " Nbcases: "+ mae.get(i) + ", 90th perc="+ mysite2.getPercentileValueAt(90,"mae",semaine.get(i)) + ", statut:"+ alerts.get(i) );
			alertObserved.add(alerts.get(i) );
		}
		
		System.out.println("*********************************************");
				
		/*
		 * Case 2, now add 03 consecutive values < rank = 42 (90th percentile)
		*/
		String path3="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest6case2.csv";
		Sites mysite3 = new Sites(path3);
		mysite3.getAlertStatusFor("mae", 90.);
		Diseases disease3 = mysite3.getDiseases();
		ArrayList<String> alerts3 = disease3.getAlertStatus();
		ArrayList<String> semaine3 = disease3.getAlertWeek();
		ArrayList<Double> mae3 = disease3.getNbCases();
		for (int i=mae3.size()-1 ; i>mae3.size()-5; i--)
		{
			System.out.println("Semaine: "+ semaine3.get(i) + " Nbcases: "+ mae3.get(i) + ", 90th perc="+ mysite3.getPercentileValueAt(90,"mae",semaine.get(i)) + ", statut:"+ alerts3.get(i) );
			alertObserved.add(alerts3.get(i) );		
		}
		System.out.println("*********************************************");
		
		//expected vs actuals!
		for (int u=0; u<alertExpected.size(); u++)
		{	
			assertEquals(alertExpected.get(u) , alertObserved.get(u) );
		}
	}
}
