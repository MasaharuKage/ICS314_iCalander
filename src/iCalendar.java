/* 
 * Calendar Object
 * 
 * Team Quartro
 */

import java.util.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.net.InetAddress;

/* Main Class */
public class iCalendar {
  public static void main(String[] args) {

    /* Arraylist of events */
    ArrayList<Events> listOfEvents = new ArrayList<Events>();

    /* Get data */
    getData(listOfEvents);

    /* Generate the .ics file */
    createTrueEvent(createEvent(listOfEvents));

  }

  public static void getData(ArrayList<Events> event) {
    String summary, start, end, location, timezone, month, day, year, datetime, descrip, geoComment = null;
    boolean temp1, temp2;
    float latitude, longitude;
    String geoPosition, classif;
    char geoAnswer, classAnswer, classAnswer2, classAnswer3;
    int choice;// choice to enter for non required fields
    Scanner input = null;
    temp1 = true;

    for (int i = 0; temp1 == true; i++) {
      /* Event object */
      Events event1 = new Events();
      event.add(event1);
      input = new Scanner(System.in);
      System.out.println("Enter the name of the event:");
      summary = input.nextLine();
      event.get(i).setInfo(summary);

      System.out.println("Enter the description of the event:");
      descrip = input.nextLine();
      event.get(i).setDescrip(descrip);

      System.out.println("Enter the month (e.g. 03):");
      month = input.nextLine();
      System.out.println("Enter the day (e.g. 27):");
      day = input.nextLine();
      System.out.println("Enter the year (e.g. 2016):");
      year = input.nextLine();

      System.out.println("Enter the start time (e.g. 120000):");
      start = input.nextLine();
      event.get(i).setDTStart(start);

      System.out.println("Enter the end time (e.g. 150000:");
      end = input.nextLine();
      event.get(i).setDTEnd(end);

      datetime = year + month + day;
      event.get(i).setDTStart(datetime + 'T' + start);
      event.get(i).setDTEnd(datetime + 'T' + end);

      System.out.println("Enter the event location:");
      location = input.nextLine();
      event.get(i).setLocation(location);

      System.out.println("Do you want to enter the geographic position? (Y/N)");
      geoAnswer = input.next().trim().charAt(0);
      if (geoAnswer == 'Y' || geoAnswer == 'y') {
        do {
          System.out.println("Enter the latitude position (Range: -90 to 90)");
          latitude = input.nextFloat();
        } while (latitude < -90 || latitude > 90);

        do {
          System.out
              .println("Enter the longitude position (Range: -180 to 180)");
          longitude = input.nextFloat();
        } while (longitude < -180 || longitude > 180);

        geoPosition = Float.toString(latitude) + ';'
            + Float.toString(longitude);
        event.get(i).setGeo(geoPosition);
        event.get(i).setLongitude(longitude);
        event.get(i).setLatitude(latitude);

       if(i > 0 && event.get(i-1).getLongitude() != 0.0 && event.get(i-1).getLatitude() != 0.0 && event.get(i).getLongitude() != 0.0 && event.get(i).getLatitude() != 0.0){
        geoComment = "Distance to next event in " + event.get(i).getLocation() + " is " + GCDist.Statute_Miles(event.get(i-1).getLongitude(), event.get(i-1).getLatitude(),longitude, latitude) + " miles" 
                      + " and " + GCDist.Kilometers(event.get(i-1).getLongitude(), event.get(i-1).getLatitude(),longitude, latitude) + " kilometers.";
        event.get(i-1).setComments(geoComment);
       }
      
      }else {
        System.out.println("Geographic position information skipped.\n");
      }
      
      
      

      System.out.println("Do you want to enter the classification of the event? (Y/N)");
      classAnswer = input.next().trim().charAt(0);
      if (classAnswer == 'Y' || classAnswer == 'y') {
        System.out
            .println("Choose a number for classificaton: 1-public, 2-private, 3-confidential");
        choice = input.nextInt();
        switch (choice) {
        case 1:
          classif = "PUBLIC";
          event.get(i).setClassi(classif);
          break;
        case 2:
          classif = "PRIVATE";
          event.get(i).setClassi(classif);
          break;
        case 3:
          classif = "CONFIDENTIAL";
          event.get(i).setClassi(classif);
          break;
        default:
          System.out.println("Invalid Choice.");
          break;
        }
      }
      else {
        System.out
            .println("Classification information skipped, default to PUBLIC");
        classif = "PUBLIC";
        event.get(i).setClassi(classif);
      }

      temp2 = true;
      while (temp2) {

        System.out.println("Add another event? (Y/N)");
        classAnswer2 = input.next().trim().charAt(0);

        if (classAnswer2 == 'N' || classAnswer2 == 'n') {
          temp2 = false;
          temp1 = false;
        }
        else if (classAnswer2 == 'Y' || classAnswer2 == 'y') {
          temp2 = false;
          break;
        }
        else {
          System.out.println("Input 'Y' or 'N'");
        }

      }

    }

    // System.out.println("Enter the timezone you are in.");
    // timezone = input.nextLine();
    // event.setTZ(timezone);

    input.close();
  }


  public static ArrayList<File> createEvent(ArrayList<Events> event) {
    ArrayList<File> files = new ArrayList<File>();
    for (int i = 0; i < event.size(); i++) {
      try {
        /* Create new file */
        File file = new File(event.get(i).getInfo() + ".ics");

        files.add(file);

        /* Write to file */
        BufferedWriter output = new BufferedWriter(new FileWriter(file));

        /* Write information to file */
        output.write("BEGIN:VCALENDAR\n");
        output.write("VERSION:" + event.get(i).getVer() + '\n');
        output.write("PRODID:" + event.get(i).getProdid() + '\n');

        /* Begin Event */
        output.write("BEGIN:VEVENT\n");

        /* UID */
        String uidValue = UUID.randomUUID() + "@"
            + InetAddress.getLocalHost().getHostName();
        output.write("UID:" + uidValue + '\n');

        /* Start time */
        // output.write("DTSTART: " + event.getTZ() + ":" + event.getDTStart());
        // output.write("\n");

        /* Start time */
        output.write("DTSTART:" + event.get(i).getDTStart() + '\n');

        /* End time */
        // output.write("DTEND: " + event.getTZ() + ":" + event.getDTEnd());
        // output.write("\n");

        /* End time */
        output.write("DTEND:" + event.get(i).getDTEnd() + '\n');

        /* Summary */
        output.write("SUMMARY:" + event.get(i).getInfo() + '\n');

        /* Description */
        output.write("DESCRIPTION:" + event.get(i).getDescrip() + '\n');

        /* Classification */
        output.write("CLASS:" + event.get(i).getClassi() + '\n');

        /* Location */
        output.write("LOCATION:" + event.get(i).getLocation() + '\n');
        if (event.get(i).getGeo() != "") {
          output.write("GEO:" + event.get(i).getGeo() + '\n');
        }
        
        /* Comments */
        if (event.get(i).getComments() != null) {
          output.write("COMMENT:" + event.get(i).getComments() + '\n');
        }

        /* End */
        output.write("END:VEVENT\n");
        output.write("END:VCALENDAR");

        /* Close file */
        output.close();
      }
      catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
    return files;

  }
  
  private static void createTrueEvent(ArrayList<File> listOfFiles) {
    /* Create new file */
    File file = new File("TeamQuatro.ics");
    ArrayList<BufferedReader> alBR = new ArrayList<BufferedReader>();
    String line;
    if (listOfFiles.size() == 0) {
      System.out.println("No files to read.");
      return;
    }

    /* Write to file */
    try {
      BufferedWriter output = new BufferedWriter(new FileWriter(file));

      for (int i = 0; i < listOfFiles.size(); i++) {

        BufferedReader br = new BufferedReader(new FileReader(
            listOfFiles.get(i)));
        alBR.add(br);

      }

      if (listOfFiles.size() == 1) {
        for (int i = 0; i < listOfFiles.size(); i++) {
          while ((line = alBR.get(i).readLine()) != null) {
            output.write(line + '\n');
          }
        }
        output.close();
        return;
      }
      else {
        for (int i = 0; i < 1; i++) {
          line = alBR.get(i).readLine();
          do {
            output.write(line + '\n');
            line = alBR.get(i).readLine();
          } while (!line.equals("END:VCALENDAR"));
        }

        for (int i = 1; i < listOfFiles.size(); i++) {
          do {
            line = alBR.get(i).readLine();
          } while (!line.equals("BEGIN:VEVENT"));
          do {
            output.write(line + '\n');
            line = alBR.get(i).readLine();
          } while (!line.equals("END:VCALENDAR"));

        }
        output.write("END:VCALENDAR\n");
        output.close();

      }
      
    }
    catch (IOException e1) {
      e1.printStackTrace();
    }

  }
  
}