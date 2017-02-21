import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>basicJDBCTest</code> contains tests for the class <code>{@link basicJDBC}</code>.
 *
 * @generatedBy CodePro at 21/02/17 04:04
 * @author herimanitra
 * @version $Revision: 1.0 $
 */
public class basicJDBCTest {
	/**
	 * Run the basicJDBC() constructor test.
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testBasicJDBC_1()
		throws Exception {
		basicJDBC result = new basicJDBC();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void main(String[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 21/02/17 04:04
	 */
	@Test
	public void testMain_1()
		throws Exception {
		String[] args = new String[] {};

		basicJDBC.main(args);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(basicJDBCTest.class);
	}
}