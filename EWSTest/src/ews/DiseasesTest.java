package ews;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>DiseasesTest</code> contains tests for the class <code>{@link Diseases}</code>.
 *
 * @generatedBy CodePro at 21/02/17 04:04
 * @author herimanitra
 * @version $Revision: 1.0 $
 */
public class DiseasesTest {
	/**
	 * Run the Diseases() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testDiseases_1()
		throws Exception {

		Diseases result = new Diseases();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void addAlertStatus(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testAddAlertStatus_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");
		String val = "";

		fixture.addAlertStatus(val);

		// add additional test code here
	}

	/**
	 * Run the void addNbCases(double) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testAddNbCases_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");
		double val = 1.0;

		fixture.addNbCases(val);

		// add additional test code here
	}

	/**
	 * Run the void addPastValues(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testAddPastValues_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");
		String val = "";

		fixture.addPastValues(val);

		// add additional test code here
	}

	/**
	 * Run the void addSites(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testAddSites_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");
		String val = "";

		fixture.addSites(val);

		// add additional test code here
	}

	/**
	 * Run the void addWeek(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testAddWeek_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");
		String val = "";

		fixture.addWeek(val);

		// add additional test code here
	}

	/**
	 * Run the ArrayList<String> getAlertStatus() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testGetAlertStatus_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");

		ArrayList<String> result = fixture.getAlertStatus();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the ArrayList<String> getAlertWeek() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testGetAlertWeek_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");

		ArrayList<String> result = fixture.getAlertWeek();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Run the ArrayList<Double> getNbCases() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testGetNbCases_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");

		ArrayList<Double> result = fixture.getNbCases();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(new Double(1.0)));
	}

	/**
	 * Run the ArrayList<String> getSites() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testGetSites_1()
		throws Exception {
		Diseases fixture = new Diseases();
		fixture.addNbCases(1.0);
		fixture.addSites("");
		fixture.addAlertStatus("");
		fixture.addPastValues("");
		fixture.addWeek("");

		ArrayList<String> result = fixture.getSites();

		// add additional test code here
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains(""));
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(DiseasesTest.class);
	}
}