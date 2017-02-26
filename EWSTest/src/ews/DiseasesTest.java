package ews;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>DiseasesTest</code> contains tests for the class <code>{@link Diseases}</code>.
 *
 * @generatedBy CodePro at 23/02/17 20:22
 * @author herimanitra
 * @version $Revision: 1.0 $
 */
public class DiseasesTest {
	
	/**
	 * Run the void addNewSite(String,String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 23/02/17 20:22
	 */
	@Test
	public void testAddNewSite_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addWeek("");
		fixture.addNbCases(1.0);
		fixture.mycsv = new CSVReader("", "", true);
		fixture.addPastValues("");
		String filepathOrigin = "";
		String filepathDestination = "";
		String newFile = "";
		fixture.addNewSite(filepathOrigin, filepathDestination, newFile);
		assertTrue(fixture.hasBeenAppended()==false);
	}

    /**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 23/02/17 20:22
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(DiseasesTest.class);
	}
}