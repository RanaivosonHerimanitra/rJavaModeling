package ews;
import java.io.IOException;

import ews.Sites;

public class SitesTest {

	public static void main(String[] args) throws IOException {
		Sites mysite = new Sites();
		double rank= mysite.getPercentileValueAt(90,"mae");
		System.out.println(rank);
		double[] mae = (double[]) mysite.getVector("mae",true);
		String[] myalert = mysite.getAlertStatusFor("mae", 90);
		String[] myalert2 = mysite.getAlertStatusForWeek("2015_11");
		for ( int k=0;k<myalert.length;k++)
		{
			System.out.print(myalert[k]+ " ");
		}
		System.out.println("");
		for ( int k=0;k<myalert2.length;k++)
		{
			System.out.print(myalert2[k]+ " ");
		}
	}
}
