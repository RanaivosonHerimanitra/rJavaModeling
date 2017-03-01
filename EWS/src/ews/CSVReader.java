/*
 * Created by Herimanitra R.,
 * Originally taken here : 
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * read any csv file and output mean of convertible variables on the screen
 */
package ews;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import postgresconnector.Credentials;

public class CSVReader {
	 static String csvFile = "";
	 static BufferedReader br = null;
     String line = "";
     String cvsSplitBy = ",";
     boolean hasHeader;
     private final ArrayList<String[]> mydata = new ArrayList<>();
     private int nrow;
     private boolean empty=false;
    // private boolean db =false;
     ResultSet rs;
     //constructor init main vars: complete path and sep
     public CSVReader (String mycsvFile,String separator, boolean val) throws SQLException, IOException
     {
    	 String mycols = "";
    	 //if user wants to retrieve from db:
    	 if (val)
    	 {
    		 String selectTableSQL = "SELECT * from paluconf";
    		 String uri ="jdbc:postgresql://localhost/ews";
    		 Credentials creds =new Credentials("fred","secret");
    		 Properties props = creds.getLoginForDB();
    		 Connection conn = DriverManager.getConnection(uri, props);
    		 Statement statement = conn.createStatement();
    		 rs = statement.executeQuery(selectTableSQL); 
    		 ResultSetMetaData rsmd = rs.getMetaData();
    		 //prepare a buffered writer to receive data:
	    	 BufferedWriter bw = new BufferedWriter(new FileWriter(new File(mycsvFile)));
	    	 for (int k=2; k<=rsmd.getColumnCount() ;k++)
	    	 {
	    		 if (k!=rsmd.getColumnCount())
	    		 {
	    			 mycols +=rsmd.getColumnName(k) +",";
	    		 } else {
	    			 mycols +=rsmd.getColumnName(k) ;
	    		 }
	    	 }
	    	bw.write(mycols);
	        bw.newLine();
	    	String lineDest="";
    		while (rs.next()) 
    		 {
    			 for (int k=0; k<rsmd.getColumnCount()-1 ;k++)
    	    	 {
    	    		 if ( k!=(mycols.length()-1) )
    	    		 {
    	    			 
    	    			 lineDest +=rs.getString( mycols.split(",")[k] ) +",";
    	    		 } else {
    	    			 lineDest +=rs.getString( mycols.split(",")[k] ) ;
    	    		 }
    	    	 }
    			System.out.println(lineDest );
 	    		bw.write(lineDest);
 	    		bw.newLine();
 	    		lineDest="";
 			 }
    		 bw.close();
    	 } else {
    		 csvFile=mycsvFile;
        	 cvsSplitBy=separator;
        	 hasHeader=val;
        	 File f = new File(csvFile);
        	 if( !f.exists() || (csvFile.equals("")) || (cvsSplitBy.equals("")) ) 
        	 { 
        	     System.out.println("Le fichier n\'existe pas, les parametres ne doivent pas etre vides");
        	     empty=true;
        	     //System.exit(1);
        	 }
    	 } 
     }
    
     //index to monitor if the file doesnt exist:
     public boolean getEmpty()
     {
    	 return empty;
     }
     
     //get size of dataset:
     public int getNumberLines() throws IOException
     {
    	 if (empty)
    	 {
    		 return 0;
    	 } else {
    		 BufferedReader b1 = new BufferedReader(new FileReader(csvFile));
        	 int n=0;
             while ((line = b1.readLine()) != null) 
             {
            	 n++;
             }
             b1.close();
             nrow=n;
             return nrow;
    	 }
    	 
     }
     public void setNumberLines(int myrow)
     {
    	 nrow=myrow;
     }
     // get # of columns in a csv file:
     public int getNumberCols() throws IOException
     {
    	 if (!empty)
    	 {
    		 BufferedReader b2 = new BufferedReader(new FileReader(csvFile));
             int maxCol=0;
             String sCurrentLine="";
             while ((sCurrentLine = b2.readLine()) != null) 
             {
            	 //collect max index col where value exists
            	 //this will be the number of column
                 if (sCurrentLine.split(cvsSplitBy).length>maxCol) {
                	 maxCol= sCurrentLine.split(cvsSplitBy).length;
                 }
             }
             b2.close();
             return maxCol;
    	 } else {
    		 return 0;
    	 }
    	 
     }
     //boolean val to indicate if it has header:
     public boolean hasHeader ()
     {
    	 return hasHeader;
     }
     //get columns by index: Otran misy bug?
     public String[] getColumnValues (int index)
     {
    	 if ( empty )
    	 {
    		 return null;
    	 } else {
    		 return mydata.get(index);
    	 }
     }  
     //mutateur:
     public void setColumnName(int index, String val)
     {
    	 if (!empty)
    	 { 
    		 String[] mystr = getColumnValues(index);
        	 for ( int i=0;i<mystr.length;i++ )
             {
            	System.out.println(mystr[i]);
             }
             mydata.get(index)[0]= val; 
    	 }
     }
     //test if It can be converted to double:
     public boolean isDouble( String input ) 
     {
    	   try {
    	        Double.parseDouble( input );
    	        return true;
    	    }
    	    catch( Exception e ) {
    	        return false;
    	    }
     }
     //calculate mean of a convertible String array:
     public double meanOf ( String[] val )
     {
    	 double N= (double) val.length;
    	 double sum = 0.;
    	 //convert and add, skip 1st col which is col's name, skip null also
    	 for (int i=1 ; i< val.length ; i++)
    	 {
    		 if  ( val[i].equals("") || val[i].isEmpty() )
    		 {
    			 N -= 1;
    		 } else {
    			 if ( isDouble(val[i]) )
    			 {
    				 sum += Double.parseDouble(val[i]);
    			 } else {
    				 sum =0;
    			 }   
    		 }
    	 }
    	 return sum/N;
     }
     // check whether headers are malformed "," or dont exist:
     public void checkMalFormedHeaders() throws IOException
     {
    	 String [] malFormedHeaders ={",","#","'",";","."};
    	 BufferedReader b = new BufferedReader(new FileReader(csvFile));
    	 String line= b.readLine();
    	 b.close();
    	 //this is the first line only === columns
    	 if (line!= null) 
    	 {
    		 String[] myline = line.split(cvsSplitBy);
    		 //loop thru columns
    		 int colNull=0;
    		 for ( int col=0; col<myline.length;col++)
    		 {
    			 //check if it contains  malformed characters such ",", ""
    			 if ( Arrays.asList(malFormedHeaders).contains(myline[col]) )
    			 {
    				setColumnName(col,"col"+colNull);
    				colNull +=1;
    			 }
    		 }
    	 }    	
     }
    
     // get name of column by its index:
     public String getNameOf (String[] val)
     {
    	 if (empty)
    	 {
    		 return null;
    	 } else {
    		 return val[0]; 
    	 }
    	 
     }
     
     public ArrayList<String[]> readCSV() throws IOException
     {
             //count the number of lines:
             int n = getNumberLines();
             if (n==0)
             {
            	 return null;
             } else {
            	//count number of cols:
                 int col = getNumberCols();
                 //if no header is said , prepare to generate one
                 int colVal=0;
                 String[] column = null;
                
                 for (int c=0; c<col; c++)
            	 {
                	 br = new BufferedReader(new FileReader(csvFile));
                	 
                	 int k=0;
                	 //si la 1ere ligne = nom de colonnes:
                	 if (hasHeader)
        			 {
                		 column = new String[n]; 
        			 } else {
        				 //sinon ET si on est à la premiere ligne
        				 if (k==0) {
        					 setNumberLines(n+1);
                    		 column = new String[n+1];
            				 column[k]="col" + colVal ;
            				 colVal +=1; 
            				 k +=1;
            				 //System.out.println("Column"+c+" index"+k+" "+column[k]);
        				 } 
        			 }
                	 while ( ( line = br.readLine() ) != null)
                     {
                		//dealing with null values from c-th column: 
                		 try {
                			 String val = line.split(cvsSplitBy)[c];
                			 column[k]=val; 
                		 }
                		 catch( Exception e) {
                			 column[k] ="" ;
                		 }
                    	 k++; 
                     } 
                	 //chaque variable/colonne represente un element de l'ArrayList
                	 mydata.add(column);
            	 }
            	        
             checkMalFormedHeaders();
        	 return mydata; 
             }
             
     }
     
	
	//Main
    public static void main(String[] args) throws IOException, SQLException 
    {
    	String csvFile = "/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/data-ground-thruth/PaluConfTestX.csv";
    	CSVReader csv= new CSVReader(csvFile,",",true);
	    ArrayList<String[]> mycsv= csv.readCSV();
    	
    }
}
