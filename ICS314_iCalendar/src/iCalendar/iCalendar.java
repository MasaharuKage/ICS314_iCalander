/* 
 * Driver iCalendar project
 * ICS 314
 * Team Quartro
 */

/* Package */
package iCalendar;

import java.util.*;
import java.io.*;
import java.text.*;

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

   }

   public static void createEvent(Events event)
   {
      try
      {
         /* Create new file */
         File file = new File("TeamQuartro.ics");

         /* Write to file */
         BufferedWriter output = new BufferedWriter(new FileWriter(file));

         /* Write information to file */
         output.write("BEGIN:VCALENDAR\n");
         output.write("VERSION:" + event.getVer());

         /* Event */
         output.write("BEGIN:VEVENT\n");

         /* Summary */
         output.write("SUMMARY:" + event.getInfo());

         /* Start time */
         output.write("DTSTART:" + event.getTZ() + ":" + event.getDTStart());

         /* End time */
         output.write("DTEND:" + event.getTZ() + ":" + event.getDTEnd());

         /* Location */
         output.write("LOCATION:" + event.getLocation());

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
