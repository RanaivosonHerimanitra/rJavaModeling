/*
 * Created by Herimanitra R.,
 * Originally taken here : 
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */
package ews;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReader {
	 static String csvFile = "";
	 static BufferedReader br = null;
     String line = "";
     String cvsSplitBy = ",";
     boolean hasHeader;
     private final ArrayList<String[]> mydata = new ArrayList<>();
     private int nrow;
     
     //constructor init main vars: complete path and sep
     public CSVReader (String mycsvFile,String separator, boolean val)
     {
    	 csvFile=mycsvFile;
    	 cvsSplitBy=separator;
    	 hasHeader=val;
     }
     //get size of dataset:
     public int getNumberLines() throws IOException
     {
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
     public void setNumberLines(int myrow)
     {
    	 nrow=myrow;
     }
     // get # of columns in a csv file:
     public int getNumberCols() throws IOException
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
     }
     //boolean val to indicate if it has header:
     public boolean hasHeader ()
     {
    	 return hasHeader;
     }
     //get columns by index:
     public String[] getColumnValues (int index)
     {
    	 return mydata.get(index);
     }  
     public void setColumnName(int index, String val)
     {
    	 String[] mystr = getColumnValues(index);
    	 for ( int i=0;i<mystr.length;i++ )
    	 {
    		 System.out.println(mystr[i]);
    	 }
    	mydata.get(index)[0]= val;
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
    			 sum += Double.parseDouble(val[i]);
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
    				//System.out.println("Le fichier doit contenir des noms de variables et ne doivent pas contenir des caracteres speciaux");
    				//System.exit(1);
    			 }
    		 }
    	 }    	
     }
    
     // get name of column by its index:
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
             System.out.println("Nbcol: "+col+" Nb line: "+n);
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
     //Main
     public static void main(String[] args) throws IOException 
     {
    	 csvFile="/media/herimanitra/DONNEES/IPM_sentinelle/sentinel_hrmntr 291115/Sentinel/data/PaluConfTest2.csv";
    	 CSVReader csv= new CSVReader(csvFile,",",true);
    	 
    	 //checkNoHeader(csv);
    	 ArrayList<String[]> mycsv= csv.readCSV();
    	 int Ncol = csv.getNumberCols();
    	 for (int k=2; k<Ncol ; k++)
    	 {
    		 String [] mycolumn = mycsv.get(k);
        	 String colName = csv.getNameOf(mycolumn);
        	 System.out.println(colName);
        	 double mymean = csv.meanOf(mycolumn);
        	 System.out.println("La moyenne de la colonne "+ colName + " est " + mymean );
    	 }
     }
}
