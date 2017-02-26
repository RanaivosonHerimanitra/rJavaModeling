package postgresconnector;

import java.sql.Statement;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>TableCreationTest</code> contains tests for the class <code>{@link TableCreation}</code>.
 *
 * @generatedBy CodePro at 26/02/17 22:45
 * @author herimanitra
 * @version $Revision: 1.0 $
 */
public class TableCreationTest {
	/**
	 * Run the TableCreation(String,Statement) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 26/02/17 22:45
	 */
	@Test
	public void testTableCreation_1()
		throws Exception {
		String value1 = "";
		Statement value4 = null;

		TableCreation result = new TableCreation(value1, value4);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void createTable() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 26/02/17 22:45
	 */
	@Test
	public void testCreateTable_1()
		throws Exception {
		TableCreation fixture = new TableCreation("", (Statement) null);

		fixture.createTable();

		}

	
	/**
	 * Run the void insertRows(Statement,String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 26/02/17 22:45
	 */
	@Test
	public void testInsertRows_1()
	
		throws Exception {
		  TableCreation fixture = new TableCreation("", (Statement) null);
		  Statement stmt = null;
		  String filepath = "";
		  String sep = "";
		  fixture.insertRows(stmt, filepath, sep);
		}

	
	/**
	 * Run the boolean tableExists() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 26/02/17 22:45
	 */
	@Test
	public void testTableExists_1()
		throws Exception {
		TableCreation fixture = new TableCreation("", (Statement) null);
		boolean result = fixture.tableExists();
		System.out.println("result:" + result);

		assertFalse(result);
	}

	
	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 26/02/17 22:45
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
	 * @generatedBy CodePro at 26/02/17 22:45
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
	 * @generatedBy CodePro at 26/02/17 22:45
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TableCreationTest.class);
	}
}