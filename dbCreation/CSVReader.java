/*
 * Created by Herimanitra R.,
 * Ideas taken here : https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */
package ews;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	 static String csvFile = "";
	 static BufferedReader br = null;
     String line = "";
     String cvsSplitBy = ",";
     private final ArrayList<String[]> mydata = new ArrayList<>();
     
     
     //constructor init main vars: complete path and sep
     public CSVReader (String mycsvFile,String separator)
     {
    	 csvFile=mycsvFile;
    	 cvsSplitBy=separator;
     }
     
     public int getNumberLines() throws IOException
     {
    	 BufferedReader b1 = new BufferedReader(new FileReader(csvFile));
    	 int n=0;
         while ((line = b1.readLine()) != null) 
         {
        	 n++;
         }
         b1.close();
         return n;
     }
     
     public int getNumberCols() throws IOException
     {
    	 BufferedReader b2 = new BufferedReader(new FileReader(csvFile));
         String myline = b2.readLine();
         int ncol= myline.split(cvsSplitBy).length;
         b2.close();
         return ncol;
     }
     //get columns by index:
     public String[] getColumnValues (int index)
     {
    	 return this.mydata.get(index);
     }  
     //calculate mean of a convertible String array:
     public double meanOf ( String[] val )
     {
    	 double N= (double) val.length;
    	 double sum = 0.;
    	 //convert and add, skip 1st col which is col's name, skip null also
    	 for (int i=1 ; i< val.length ; i++)
    	 {
    		 if  ( val[i].equals("") )
    		 {
    			 N -= 1;
    		 } else {
    			 sum += Double.parseDouble(val[i]);
    		 }
    	 }
    	 return sum/N;
     }
     
     //
     public String getNameOf (String[] val)
     {
    	 return val[0];
    	 
     }
     
     public ArrayList<String[]> readCSV() throws IOException
     {
             //count the number of lines:
             int n = getNumberLines();
             //count number of cols:
             int col = getNumberCols();
             //System.out.println("Nb var:" + col);
             for (int c=0; c<col; c++)
        	 {
            	 br = new BufferedReader(new FileReader(csvFile));
            	 String[] column = new String[n];
            	 int k=0;
            	 
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
        	        
         
    	 return mydata; 
     }
     //Main
     public static void main(String[] args) throws IOException 
     {
    	 csvFile="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConf.csv";
    	 CSVReader csv= new CSVReader(csvFile,",");
    	 ArrayList<String[]> mycsv= csv.readCSV();
    	 int Ncol = csv.getNumberCols();
    	 for (int k=2; k<Ncol ; k++)
    	 {
    		 String [] mycolumn = mycsv.get(k);
        	 String colName = csv.getNameOf(mycolumn);
        	 double mymean = csv.meanOf(mycolumn);
        	 System.out.println("La moyenne de la colonne "+ colName + " est " + mymean );
    	 }
     }
}
