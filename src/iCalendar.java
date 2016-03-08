
/* Package */


import java.util.*;
import java.io.*;
import java.text.*;
import java.util.Scanner;
import java.util.UUID;
import java.net.InetAddress;

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
     String summary, start, end, location, timezone, month, day, year, datetime, descrip;
     float latitude, longitude;
     String geoPosition, classif;
     char geoAnswer, classAnswer; int choice;//choice to enter for non required fields
     
     Scanner input = new Scanner(System.in);
     System.out.println("Enter the name of the event:");
     summary = input.nextLine();
     event.setInfo(summary);
     
     System.out.println("Enter the description of the event:");
     descrip = input.nextLine();
     event.setDescrip(descrip);
     
     System.out.println("Enter the month (e.g. 03):");
     month = input.nextLine();
     System.out.println("Enter the day (e.g. 27):");
     day = input.nextLine();
     System.out.println("Enter the year (e.g. 2016):");
     year = input.nextLine();
     
     System.out.println("Enter the start time (e.g. 120000):");
     start = input.nextLine();
     event.setDTStart(start);
     
     System.out.println("Enter the end time (e.g. 150000:");
     end = input.nextLine();
     event.setDTEnd(end);
     
     datetime = year + month + day;
     event.setDTStart(datetime + 'T' + start);
     event.setDTEnd(datetime + 'T' + end);
     
     System.out.println("Enter the event location:");
     location = input.nextLine();
     event.setLocation(location);
     
     System.out.println("Do you want to enter the geographic position? (Y/N)");
     geoAnswer = input.next().trim().charAt(0);
     if(geoAnswer == 'Y' || geoAnswer == 'y')
     {
       do
       {
         System.out.println("Enter the latitude position (Range: -90 to 90)");
         latitude = input.nextFloat();
       }while(latitude < -90 || latitude > 90);
       
       do
       {
         System.out.println("Enter the longitude position (Range: -180 to 180)");
         longitude = input.nextFloat();
       }while(longitude < -180 || longitude > 180);
     
       geoPosition = Float.toString(latitude) + ';' + Float.toString(longitude);
       event.setGeo(geoPosition);
     }
     else 
     {
       System.out.println("Geographic position information skipped.");
     }
     
     System.out.println("Do you want to enter the classification of the event? (Y/N)");
     classAnswer = input.next().trim().charAt(0);
     if(classAnswer == 'Y' || classAnswer == 'y')
     {
       System.out.println("Choose a number for classificaton: 1-public, 2-private, 3-confidential");
       choice = input.nextInt();
       switch(choice)
       {
         case 1:
           classif = "PUBLIC";
           event.setClassi(classif);
           break;
         case 2:
           classif = "PRIVATE";
           event.setClassi(classif);
           break;
         case 3:
           classif = "CONFIDENTIAL";
           event.setClassi(classif);
           break;
         default:
           System.out.println("Invalid Choice.");
           break;
       }
     }
     else
     {
       System.out.println("Classification information skipped, default to PUBLIC");
       classif = "PUBLIC";
       event.setClassi(classif);
     }
     
     //System.out.println("Enter the timezone you are in.");
     //timezone = input.nextLine();
     //event.setTZ(timezone);
     
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
         output.write("VERSION:" + event.getVer());
         output.write("\n");
         output.write("PRODID:" + event.getProdid());
         output.write("\n");

         /* Begin Event */
         output.write("BEGIN:VEVENT\n");
         
         /* UID */
         String uidValue = UUID.randomUUID() + "@" + InetAddress.getLocalHost().getHostName();
         output.write("UID:" + uidValue);
         output.write("\n");

         /* Start time */
         // output.write("DTSTART: " + event.getTZ() + ":" + event.getDTStart());
         // output.write("\n");
         
         /* Start time */
         output.write("DTSTART:" + event.getDTStart());
         output.write("\n");

         /* End time */
         //output.write("DTEND: " + event.getTZ() + ":" + event.getDTEnd());
         //output.write("\n");
         
         /* End time */
         output.write("DTEND:" + event.getDTEnd());
         output.write("\n");
         
         /* Summary */
         output.write("SUMMARY:" + event.getInfo());
         output.write("\n");
         
         /*Description*/
         output.write("DESCRIPTION:" + event.getDescrip());
         output.write("\n");
         
         /*Classification*/
         output.write("CLASS:" + event.getClassi());
         output.write("\n");

         /* Location */
         output.write("LOCATION:" + event.getLocation());
         output.write("\n");
         
         output.write("GEO:" + event.getGeo());
         output.write("\n");

         /* End */
         output.write("END:VEVENT\n");
         output.write("END:VCALENDAR");

         /* Close file */
         output.close();
      }
      catch (IOException ioe){
        ioe.printStackTrace();
      }
   }
}