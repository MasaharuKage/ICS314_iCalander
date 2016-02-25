/* 
 * Driver iCalendar project
 * ICS 314
 * Team Quatro
 */

/* Package */
package iCalendar;

import java.util.*;
import java.io.*;
import java.text.*;
import java.util.Scanner;

/* Main Class */
public class iCalendar
{
   public static void main(String[] args)
   {
      /* Event object */
      Events event = new Events();
      
      /* Get data */
      getData(event);

      /* Generate the .ics file */
      createEvent(event);    
   }

   public static void getData(Events event)
   {
     String summary, start, end, location, timezone;
     Scanner input = new Scanner(System.in);
     System.out.println("Enter the name of the event.");
     summary = input.nextLine();
     event.setInfo(summary);
     
     System.out.println("Enter the start time.");
     start = input.nextLine();
     event.setDTStart(start);
     
     System.out.println("Enter the end time.");
     end = input.nextLine();
     event.setDTEnd(end);
     
     System.out.println("Enter the event location.");
     location = input.nextLine();
     event.setLocation(location);
     
     System.out.println("Enter the timezone you are in.");
     timezone = input.nextLine();
     event.setTZ(timezone);
     
     input.close();
   }

   public static void createEvent(Events event)
   {
      try
      {
         /* Create new file */
         File file = new File("TeamQuatro.ics");

         /* Write to file */
         BufferedWriter output = new BufferedWriter(new FileWriter(file));

         /* Write information to file */
         output.write("BEGIN:VCALENDAR\n");
         output.write("VERSION: " + event.getVer());
         output.write("\n");

         /* Event */
         output.write("BEGIN:VEVENT\n");

         /* Summary */
         output.write("SUMMARY: " + event.getInfo());
         output.write("\n");

         /* Start time */
         output.write("DTSTART: " + event.getTZ() + ":" + event.getDTStart());
         output.write("\n");

         /* End time */
         output.write("DTEND: " + event.getTZ() + ":" + event.getDTEnd());
         output.write("\n");

         /* Location */
         output.write("LOCATION: " + event.getLocation());
         output.write("\n");

         /* End */
         output.write("END:VEVENT\n");
         output.write("END:VCALENDAR\n");

         /* Close file */
         output.close();
      }
      catch (IOException ioe){
        ioe.printStackTrace();
      }
   }
}