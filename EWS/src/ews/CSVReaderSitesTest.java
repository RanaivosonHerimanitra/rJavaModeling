package ews;
import static org.junit.Assert.*;

import java.io.IOException;
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
		int k=0;
		
		//what we expect:
		alertExpected.add(0,"alert");
		alertExpected.add(1,"alert");
		alertExpected.add(2,"alert");
		alertExpected.add(3,"normal");
		alertExpected.add(4,"normal");
		alertExpected.add(5,"normal");
		
		//instantiate and add path of csv file:
		String path1="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest6.csv";
		Sites mysite1 = new Sites(path1);
		double rank1= mysite1.getPercentileValueAt(90,"mae");
		System.out.println("Le 90ieme percentile commence à partir de: " + rank1);
				
		/*
		 * Case 1, now add 03 consecutive values > rank=42 (90th percentile)
		 * Expected: Alert!
		 */
		String path2="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest6case1.csv";
		Sites mysite2 = new Sites(path2);
		double rank2= mysite2.getPercentileValueAt(90,"mae");
		//get time series for mae:
		double[] mae2 = (double[]) mysite2.getVector("mae",true);
		String[] semaine2 = (String[]) mysite2.getVector("code",false);
		String[] alerts2 = mysite2.getAlertStatusFor("mae", 90);
		for (int i=1; i<4 ;i++)
		{
			System.out.println("Semaine: "+ semaine2[i] + " Nbcases: "+ mae2[i] + ", 90th perc="+ rank2 + ", statut:"+ alerts2[i]);
			alertObserved.add(k,alerts2[i]);
			k++;
		}
		System.out.println("*********************************************");
				
		/*
		 * Case 2, now add 03 consecutive values < rank = 42 (90th percentile)
		*/
		String path3="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest6case2.csv";
		Sites mysite3 = new Sites(path3);
		double rank3= mysite3.getPercentileValueAt(90,"mae");
		//get time series for mae:
		double[] mae3 = (double[]) mysite3.getVector("mae",true);
		String[] semaine3 = (String[]) mysite3.getVector("code",false);
		String[] alerts3 = mysite3.getAlertStatusFor("mae", 90);
			
		for (int i=1; i<4 ;i++)
		{
			System.out.println("Semaine: "+ semaine3[i] + " Nbcases: "+ mae3[i] + ", 90th perc="+ rank3 + ", statut:"+ alerts3[i]);
			alertObserved.add(k,alerts3[i]);
			k++;
		}
		System.out.println("*********************************************");
		
		for (int u=0; u<alertObserved.size(); u++)
		{
			//expected vs actuals!
			assertEquals(alertExpected.get(u) , alertObserved.get(u) );
		}
	}
}
