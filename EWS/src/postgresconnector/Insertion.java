package postgresconnector;
import java.sql.DriverManager;     
import java.io.IOException;
import java.sql.Connection;       
import java.sql.Statement;              
import java.sql.SQLException;      
import java.util.Properties;  
/*
 * to visualize db:
 * psql -d ews
 * SELECT * FROM paluconf LIMIT 10;
 */

public class Insertion  {
 //run
public static void main(String[ ] args) throws IOException, SQLException
{
	 String uri ="jdbc:postgresql://localhost/ews";
	 String filepath="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConf.csv";
	 Credentials creds =new Credentials("fred","secret");
	 Properties props = creds.getLoginForDB();
	 Connection conn = DriverManager.getConnection(uri, props);
	 conn.setAutoCommit(true);
	 Statement stmt = conn.createStatement();
	 TableCreation mytable = new TableCreation("paluconf",stmt); 
	 mytable.createTable();
	 mytable.insertRows(stmt,filepath,",");
	 
	 
	
}
}
