package postgresconnector;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class table {
	
	private static String tablename;
	private static Statement stmt;
	private static String sql="";
	public table(String value1, Statement value4)
	{
		tablename=value1;
		stmt = value4;
	}
	public void createTable()
	{
//		try
//		{
//			Class.forName("org.postgresql.Driver"); 
//	        Connection conn = DriverManager.getConnection(uri, props);
//	        stmt = conn.createStatement();
//		}
//		catch(SQLException e) { e.printStackTrace(); }
//		catch(ClassNotFoundException e) { e.printStackTrace(); } 
		try
		{
		    String sql = "CREATE TABLE IF NOT EXISTS " + tablename + 
		    		" (id SERIAL PRIMARY KEY NOT NULL," + 
			        " code VARCHAR(7) NOT NULL," +
		            " deb_sem  VARCHAR(50) NOT NULL," +
	                  " abon VARCHAR(3)," +
	                  " abv  VARCHAR(3) ," +
	                  " abz VARCHAR(3) ," +
	                  " ahh  VARCHAR(3) ," +
	                  " ajb VARCHAR(3) ," +
	                  " ambl  VARCHAR(3) ," +
	                  " ants VARCHAR(3) ," +
	                  " atb  VARCHAR(3) ," +
	                  " bel VARCHAR(3) ," +
	                  " bela VARCHAR(3) ," +
	                  " bhk  VARCHAR(3) ," +
	                  " boe VARCHAR(3) ," +
	                  " bos  VARCHAR(3) ," +
	                  " cda VARCHAR(3) ," +
	                  " die  VARCHAR(3) ," +
	                  " diri VARCHAR(3) ," +
	                  " dona  VARCHAR(3) ," +
	                  " dri VARCHAR(3) ," +
	                  " ejd  VARCHAR(3) ," +
	                  " far VARCHAR(3) ," +
	                  " fito  VARCHAR(3) ," +
	                  " fns VARCHAR(3) ," +
	                  " iho  VARCHAR(3) ," +
	                  " mae VARCHAR(3) ," +
	                  " mand  VARCHAR(3) ," +
	                  " mdv VARCHAR(3) ," +
	                  " mhj  VARCHAR(3) ," +
	                  " mia VARCHAR(3) ," +
	                  " miad  VARCHAR(3) ," +
	                  " mjr VARCHAR(3) ," +
	                  " mnj  VARCHAR(3) ," +
	                  " mnld VARCHAR(3) ," +
	                  " mora  VARCHAR(3) ," +
	                  " mrb VARCHAR(3) ," +
	                  " mrg  VARCHAR(3) ," +
	                  " mrt VARCHAR(3) ," +
	                  " mtn  VARCHAR(3) ," +
	                  " nsb VARCHAR(3) ," +
	                  " samp  VARCHAR(3) ," +
	                  " sbv VARCHAR(3) ," +
	                  " snam  VARCHAR(3) ," +
	                  " stm VARCHAR(3) ," +
	                  " tanb  VARCHAR(3) ," +
	                  " tdd VARCHAR(3) ," +
	                  " tgr  VARCHAR(3) ," +
	                  " tlr VARCHAR(3) ," +
	                  " toa  VARCHAR(3) ," +
	                  " tomp VARCHAR(3) ," +
	                  " tsar  VARCHAR(3) ," +
	                  " tsim VARCHAR(3) ," +
	                  " tsl  VARCHAR(3) ," +
	                  " velo VARCHAR(3) ," +
	                  " vink  VARCHAR(3) ," +
			          " vola VARCHAR(3)  )";
		    stmt.executeUpdate(sql);
		}
		catch(SQLException e) { e.printStackTrace(); }
	}
	//must try to check from cli
	public boolean tableExists() 
	{
		boolean flag = false;
		try
		{
		    String sql = "select * from " + tablename + ";";
		    ResultSet rs = stmt.executeQuery(sql);
		    if ( rs.getRow() > 0) {flag = true;}
		}
		catch(SQLException e) { e.printStackTrace(); }
		return flag;
	}
	void insertRows(Statement stmt, String filepath, String sep) throws IOException, SQLException
	{
		BufferedReader br=null;
		String line ="";
		String[] col = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		int skipfirst =0;
		while (( line = br.readLine()) != null) 
	        {
				sql = "INSERT INTO " +  tablename + " (code,deb_sem,abon,abv,abz,ahh,ajb,ambl,ants,atb,bel,bela,bhk,boe,bos,cda,die,diri,dona,dri,ejd,far,fito,fns,iho,mae,mand,mdv,mhj,mia,miad,mjr,mnj,mnld,mora,mrb,mrg,mrt,mtn,nsb,samp,sbv,snam,stm,tanb,tdd,tgr,tlr,toa,tomp,tsar,tsim,tsl,velo,vink,vola) VALUES ('"  ;
				//data begins when skipfirst!=0
				if (skipfirst!=0)
				{
					col = line.split(sep,-1); 
					System.out.print("Line number:" + col.length);
					//loop for all 56 csv vals
					for (int k=0; k<col.length;k=k+1)
					{
						sql +=  col[k];
						//in the 56th , close bracks
						if (k ==col.length-1) 
						{
							sql +=   "');";
						} else {
							sql +=   "', '";
						}
					}
					System.out.println("SQL:" + sql);
					stmt.executeUpdate(sql);
					
				//1st line: line of column names so we skip it
				} 
				else { skipfirst +=1; }
	        }
		
	}

}
