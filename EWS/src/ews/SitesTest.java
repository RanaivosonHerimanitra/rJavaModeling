/*
 * allows us to test methods of Sites.java which
 * produces algorithm and statics on different
 * sentinel sites
 */
package ews;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import ews.Sites;

public class SitesTest {

	public static void main(String[] args) throws IOException {
						
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
					
				}
				System.out.println("*********************************************");
				
				
				
				//instantiate and add path of csv file:
				String path4="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConf.csv";
				Sites mysite4 = new Sites(path4);
				//get alert status of all sites:
				LinkedHashMap<String, String[] > histStat= mysite4.getHistoricalStatus();
				// Generating a Set of entries
		        Set set = histStat.entrySet();
		        // Displaying elements of LinkedHashMap
		        Iterator iterator = set.iterator();
		        while(iterator.hasNext()) {
		            Map.Entry me = (Map.Entry) iterator.next();
		            System.out.print("Site: "+ me.getKey() +  " : ");
		            String[] status = (String[]) me.getValue();
		            for ( int k=0; k<status.length; k++)
		            {
		            	System.out.print(status[k] +"-" );
		            }
		            System.out.println("");
		         }
	}
}
