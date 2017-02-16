package ews;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
/*
 * on peut mettre plusieurs test case 
 * a l'interieur de ({}) avec un virgule
 */
@SuiteClasses({ CSVReaderTest.class })
public class AllTests {

}
