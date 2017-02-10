import CSVReader;

/*
0. In a normal install of PostgreSQL, PostgreSQL becomes a 'service' that
   starts up when the machine boots. The installer also starts PostgreSQL
   after the installation is completed.
1. Open the 'psql' interface to PostgreSQL (from GUI or command-line).
   The 'psql' utility is included in the normal installation.
2. Enter this command at the 'psql' prompt:
       create role fred superuser login;        ## 'role' is effectively 'user'
3. Enter this command at the prompt:
       alter role fred with password 'secret';  ## set the password for fred
4. Enter this command at the prompt:
       create database EWS;                ## create an empty database
4. \q
*/

import java.sql.DriverManager;     // handles communication with the DB
import java.sql.Connection;        // a connection to the DB
import java.sql.Statement;         // an SQL statement for the DB to execute
import java.sql.ResultSet;         // a table of rows generated from an SQL query
import java.sql.SQLException;      // what's thrown when the JDBC operations fail
import java.util.Properties;       // key/value pairs

/* Compilation and execution from the command-line, with '%' as the command-line prompt:
% javac jdbcHandler.java   ## core Java only is enough
% java -cp .:postgresql-jdbc.jar jdbcHandler  ## the JAR contains the PostgreSQL 'driver'
## On Windows: % java -cp .;postgresql-jdbc.jar BasicJDBC
*/

public class jdbcHandler {
 private String dbname;
 private String tablename;
public jdbcHandler ()
{
  dbname ="EWS";
  tablename="PaluConf"

}
private Properties setLoginForDB()
{
	Properties props = new Properties();
	props.setProperty("user", "fred");
	props.setProperty("password", "secret");
	return props;
}

private boolean tableExistsAlready(Connection conn, String name) {
boolean flag = false;
try
{
    Statement stmt = conn.createStatement();
    String sql = "select count(*) as total from " + name + ";";
    ResultSet rs = stmt.executeQuery(sql);
    rs.next();
    if (rs.getInt("total") > 0) {flag = true;}
}
catch(SQLException e) { e.printStackTrace(); }
return flag;
}

private void setUp() {
	String uri = "jdbc:postgresql://localhost/"+ dbname;
	Properties props = setLoginForDB();
	try
	{
		Class.forName("org.postgresql.Driver"); //*** load the PostreSQL driver
        Connection conn = DriverManager.getConnection(uri, props);
    // Check whether SkiStuff table exists already.
    if (tableExistsAlready(conn, "PaluConf"))
    {
	// do nothing
    } else {
	    // otherwise, create the table and insert data
	    Statement stmt = conn.createStatement();
	    createDB(stmt);
	    conn.setAutoCommit(false); // We'll explicitly commit all of the rows after the insertions.
	    insertRows(stmt);
	    conn.commit();
	    stmt.close();
    }
    conn.close(); // in either case, close the connection
}
catch(SQLException e) { e.printStackTrace(); }
catch(ClassNotFoundException e) { e.printStackTrace(); } //*** Class.forName(...)

testCreationInsertion(uri, props);
}

private void createDB(Statement stmt)
{
	try
	{
	    String sql = "CREATE TABLE skisEtc " +
		              "(id SERIAL PRIMARY KEY NOT NULL," +
		              " code VARCHAR(50) NOT NULL," +
	                " deb_sem  VARCHAR(50) NOT NULL," +
                  " abon INTEGER," +
                  " abv  INTEGER," +
                  " abz INTEGER," +
                  " ahh  INTEGER," +
                  " ajb INTEGER," +
                  " ambl  INTEGER," +
                  " ants INTEGER," +
                  " atb  INTEGER," +
                  " bel INTEGER," +
                  " bhk  INTEGER," +
                  " boe INTEGER," +
                  " bos  INTEGER," +
                  " cda INTEGER," +
                  " die  INTEGER," +
                  " diri INTEGER," +
                  " dona  INTEGER," +
                  " dri INTEGER," +
                  " ejd  INTEGER," +
                  " far INTEGER," +
                  " fito  INTEGER," +
                  " fns INTEGER," +
                  " iho  INTEGER," +
                  " mae INTEGER," +
                  " mand  INTEGER," +
                  " mdv INTEGER," +
                  " mhj  INTEGER," +
                  " mia INTEGER," +
                  " miad  INTEGER," +
                  " mjr INTEGER," +
                  " mnj  INTEGER," +
                  " mnld INTEGER," +
                  " mora  INTEGER," +
                  " mrb INTEGER," +
                  " mrg  INTEGER," +
                  " mrt INTEGER," +
                  " mtn  INTEGER," +
                  " nsb INTEGER," +
                  " samp  INTEGER," +
                  " sbv INTEGER," +
                  " snam  INTEGER," +
                  " stm INTEGER," +
                  " tanb  INTEGER," +
                  " tdd INTEGER," +
                  " tgr  INTEGER," +
                  " tlr INTEGER," +
                  " toa  INTEGER," +
                  " tomp INTEGER," +
                  " tsar  INTEGER," +
                  " tsim INTEGER," +
                  " tsl  INTEGER," +
                  " velo INTEGER," +
                  " vink  INTEGER," +
		              " vola INTEGER )";
	    stmt.executeUpdate(sql);
	}
	catch(SQLException e) { e.printStackTrace(); }
}

private void insertRows(Statement stmt)
{
	try
	{
	    for (String str : data)
	    {
	    	String[ ] columns = str.split("!"); // get the 3 pieces
			  String sql = "INSERT INTO skisEtc (product, category, price) " +
			    "VALUES ('" + columns[0] +"', '" + columns[1] + "', " + columns[2] + ");";
			stmt.executeUpdate(sql);
		  }
	}
    catch(SQLException e) { e.printStackTrace(); }
}

private void testCreationInsertion(String uri, Properties props)
{
	try {
	    Connection conn = DriverManager.getConnection(uri, props);
	    Statement stmt = conn.createStatement();

	    ResultSet rs = stmt.executeQuery("SELECT * FROM skisEtc;");
	    while (rs.next()) {
		int id = rs.getInt("id");
		String product = rs.getString("product");
		String category = rs.getString("category");
		float price = rs.getFloat("price");
		System.out.format("%d %s %s %.2f\n", id, product, category, price);
	    }
	    rs.close();
	    stmt.close();
	    conn.close();
	}
	catch(SQLException e) { e.printStackTrace(); }
}
public static void main(String[ ] args)
{
	new jdbcHandler().setUp();
}
}
