package ews;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>CSVReaderTest</code> contains tests for the class <code>{@link CSVReader}</code>.
 *
 * @generatedBy CodePro at 20/02/17 22:29
 * @author herimanitra
 * @version $Revision: 1.0 $
 */
public class CSVReaderTest {
	/**
	 * Run the CSVReader(String,String,boolean) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testCSVReader_1()
		throws Exception {
		String mycsvFile = "";
		String separator = "";
		boolean val = true;
		boolean remote = false;
		boolean empty = false;
		CSVReader result = new CSVReader(mycsvFile, separator, val,remote);
		empty= result.getEmpty();
		assertTrue(empty==val);
	}
    /**
	 * Run the String[] getColumnValues(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testGetColumnValues_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		int index = 1;
		String[] result = fixture.getColumnValues(index);
		assertTrue(result==null);
	}

	/**
	 * Run the boolean getEmpty() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testGetEmpty_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";

		boolean result = fixture.getEmpty();
		assertTrue(result==true);
	}

	/**
	 * Run the String getNameOf(String[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testGetNameOf_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		String[] val = new String[] {};
		String result = fixture.getNameOf(val);
		assertNull(result);
	}

	/**
	 * Run the int getNumberCols() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testGetNumberCols_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		int result = fixture.getNumberCols();
		assertEquals(0, result);
	}

	/**
	 * Run the int getNumberLines() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testGetNumberLines_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		int result = fixture.getNumberLines();
		assertEquals(0, result);
	}

	
	/**
	 * Run the boolean hasHeader() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testHasHeader_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		boolean result = fixture.hasHeader();
		assertTrue(result);
	}

	
	/**
	 * Run the boolean isDouble(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testIsDouble_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		String input = "";
		boolean result = fixture.isDouble(input);
		assertFalse(result);
	}

	
	

	/**
	 * Run the double meanOf(String[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testMeanOf_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		String[] val = new String[] {null, ""};

		double result = fixture.meanOf(val);
		assertEquals(0.0, result, 0.1);
	}


	/**
	 * Run the ArrayList<String[]> readCSV() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testReadCSV_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		ArrayList<String[]> result = fixture.readCSV();
		assertNull(result);
	}

	/**
	 * Run the void setColumnName(int,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testSetColumnName_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		int index = 1;
		String val = "";
		fixture.setColumnName(index, val);
	}

	
	/**
	 * Run the void setNumberLines(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	@Test
	public void testSetNumberLines_1()
		throws Exception {
		CSVReader fixture = new CSVReader("", "", true,false);
		fixture.setNumberLines(1);
		fixture.line = "";
		int myrow = 1;
		fixture.setNumberLines(myrow);
		assertTrue(fixture.getNumberLines()==0 ) ;
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 20/02/17 22:29
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
	 * @generatedBy CodePro at 20/02/17 22:29
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
	 * @generatedBy CodePro at 20/02/17 22:29
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CSVReaderTest.class);
	}
}