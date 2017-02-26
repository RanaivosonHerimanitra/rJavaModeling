package postgresconnector;

import java.util.Properties;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>credentialsTest</code> contains tests for the class <code>{@link Credentials}</code>.
 *
 * @generatedBy CodePro at 21/02/17 04:04
 * @author herimanitra
 * @version $Revision: 1.0 $
 */
public class credentialsTest {
	/**
	 * Run the credentials(String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testCredentials_1()
		throws Exception {
		String user = "";
		String pass = "";

		Credentials result = new Credentials(user, pass);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Properties getLoginForDB() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testGetLoginForDB_1()
		throws Exception {
		Credentials fixture = new Credentials("", "");

		Properties result = fixture.getLoginForDB();

		// add additional test code here
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("", result.get("user"));
		assertEquals("", result.get("password"));
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
		new org.junit.runner.JUnitCore().run(credentialsTest.class);
	}
}