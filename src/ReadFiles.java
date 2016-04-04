import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Calendar Object
 * Team Quatro
 */

/**
 * Class to read in arbitrary number of .ics files
 *
 */
public class ReadFiles {

  public static void main(String args[]) throws IOException{
    
    String file, file2;
    
    System.out.println("Enter the first .ics filename.");
    Scanner input = new Scanner(System.in);
    file = input.nextLine();
    
    System.out.println("Enter the second .ics filename.");
    file2 = input.nextLine();
    
    readFiles(file, file2);
  }
  
  /**
   * Read multiple files to parse start time and Geo information
   */
  private static void readFiles(String path, String path2) throws IOException{
    
    String startline, startline2;
    
    File filename = new File(path);
    File filename2 = new File(path2);
    BufferedReader br = new BufferedReader(new FileReader(filename));
    BufferedReader br2 = new BufferedReader(new FileReader(filename2));
    
    while((startline = br.readLine()) != null && (startline2 = br2.readLine()) != null){
      
      if(startline.contains("DTSTART:") && startline2.contains("DTSTART:")){
        startline = br.readLine();
        startline2 = br2.readLine();
        int time, time2;
        
        for(String retval: startline.split("T")){
          for(String retval2: startline2.split("T")){
           
              time = Integer.parseInt(retval);
              time2 = Integer.parseInt(retval2);
              if(eventComp(time, time2) == false){
                File file = new File("temp.ics");
                swap(path, "temp.ics");
                swap(path2, path);
                swap("temp.ics", path2);
              }
          }
        }
      }
    }
    
    br.close();
    br2.close();
  }
  
  
  /**
   * Compare 2 event files by start time
   */
  private static boolean eventComp(int start, int start2){
    if(start > start2){
      return false;
    }
    return true;
  }
  
  /**
   * Switch two events
   */
  private static void swap(String a, String b){
    try{
      String temp = "";
      BufferedReader br = new BufferedReader(new FileReader(a));
      BufferedWriter bw = new BufferedWriter(new FileWriter(b));
      while((temp = br.readLine()) != null){
        bw.write(temp);
        bw.newLine();
        bw.flush();
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }
  
}
