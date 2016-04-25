/* 
 * Calendar Object
 * 
 * Team Quartro
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.net.InetAddress;
import java.util.Comparator;
import java.util.Collections;

/* Main Class */
public class iCalendar {
  public static void main(String[] args) 
  {
    
    if(args.length > 0)
    {
      ArrayList<File> listOfFiles = new ArrayList<File>();
      
      for(int i = 0; i < args.length; i++) 
      {
        String name = args[i];
        File filename = new File(name);
        listOfFiles.add(filename);
      }
      
      createTrueEvent(listOfFiles);
      
    }
    else 
    {

    /* Arraylist of events */
    ArrayList<Events> listOfEvents = new ArrayList<Events>();

    /* Obtains the data from user to write into event file */
    collectData(listOfEvents);
    
    /* Sort data */
    Collections.sort(listOfEvents, new Comparator <Events>()
    {
    	@Override
    	public int compare(Events e1, Events e2)
    	{
    		Long T1 = ((Events) e1).getStart();
    		Long T2 = ((Events) e2).getStart();
    		
    		return T1.compareTo(T2);
    	}
    });
    
    /* Calculate Distance between events */
    calcDist(listOfEvents);

    /* Generate the .ics file */
    createTrueEvent(createEvent(listOfEvents));
    }

  }

  public static void collectData(ArrayList<Events> event) 
  {
    boolean temp1, temp2;
    temp1 = true;

    for (int i = 0; temp1 == true; i++) 
    {
      /* Create Event object */
      Events event1 = new Events();
      event.add(event1);
      Scanner input = null;
      input = new Scanner(System.in);
      
      getBasicEventInfo(i, input, event);
      getEventDateTime(i, input, event);
      getEventGeo(i, input, event);
      getEventClass(i, input, event);

      temp2 = true;
      while (temp2) 
      {
        char addEventAnswer;
        
        System.out.println("Add another event? (Y/N)");
        addEventAnswer = input.next().trim().charAt(0);

        if (addEventAnswer == 'N' || addEventAnswer == 'n') 
        {
          temp2 = false;
          temp1 = false;
        }
        else if (addEventAnswer == 'Y' || addEventAnswer == 'y') 
        {
          temp2 = false;
          break;
        }
        else 
        {
          System.out.println("Input 'Y' or 'N'");
        }
      }
      
      input.close();
    }

  }
  
  private static void getBasicEventInfo(int i, Scanner input, ArrayList<Events> event)
  {
    String summary, descrip, location;
   
    System.out.println("Enter the name of the event:");
    summary = input.nextLine();
    event.get(i).setInfo(summary);

    System.out.println("Enter the description of the event:");
    descrip = input.nextLine();
    event.get(i).setDescrip(descrip);

    System.out.println("Enter the event location:");
    location = input.nextLine();
    event.get(i).setLocation(location);
  }
  
  private static void getEventDateTime(int i, Scanner input, ArrayList<Events> event)
  {
    String month, day, year, start, end, datetime;
    
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
    event.get(i).setDay(Integer.parseInt(day));
    event.get(i).setMonth(Integer.parseInt(month));
    event.get(i).setYear(Integer.parseInt(year));
    event.get(i).setStart(Long.parseLong(start));
    event.get(i).setEnd(Long.parseLong(end));
  }
  
  private static void getEventGeo(int i, Scanner input, ArrayList<Events> event)
  {
    char geoAnswer;
    float latitude, longitude;
    String geoPosition;
    
    System.out.println("Do you want to enter the geographic position? (Y/N)");
    geoAnswer = input.next().trim().charAt(0);
    
    if (geoAnswer == 'Y' || geoAnswer == 'y') 
    {
      do 
      {
        System.out.println("Enter the latitude position (Range: -90 to 90)");
        latitude = input.nextFloat();
      } while (latitude < -90 || latitude > 90);

      do 
      {
        System.out
            .println("Enter the longitude position (Range: -180 to 180)");
        longitude = input.nextFloat();
      } while (longitude < -180 || longitude > 180);

      geoPosition = Float.toString(latitude) + ';' + Float.toString(longitude);
      event.get(i).setGeo(geoPosition);
      event.get(i).setLongitude(longitude);
      event.get(i).setLatitude(latitude);
    }
    else
    {
      System.out.println("Geographic position information skipped.\n");
    }
  }
  
  private static void getEventClass(int i, Scanner input, ArrayList<Events> event)
  {
    char classAnswer;
    int choice;
    String classif;
    
    System.out.println("Do you want to enter the classification of the event? (Y/N)");
    classAnswer = input.next().trim().charAt(0);
    
    if (classAnswer == 'Y' || classAnswer == 'y') 
    {
      System.out.println("Choose a number for classificaton: "
          + "1-public, 2-private, 3-confidential");
      choice = input.nextInt();
      switch (choice) 
      {
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
    else 
    {
      System.out.println("Classification information skipped, default to PUBLIC");
      classif = "PUBLIC";
      event.get(i).setClassi(classif);
    }
  }
  
  public static void calcDist(ArrayList<Events> event) 
  {
	  String geoComment = null;
	  int i = 0;
	  int max_size = event.size();
	  for(i = 0; i < max_size; i++)
	  {
          if((i > 0) && (event.get(i).getLongitude() != -500) && (event.get(i).getLatitude() != -500) 
              && (event.get(i-1).getLongitude() != -500) && (event.get(i-1).getLatitude() != -500))
          {
               geoComment = "Distance to next event in " + event.get(i).getLocation() + " is " + 
                             GCDist.Statute_Miles(event.get(i-1).getLongitude(), event.get(i-1).getLatitude(), 
                             event.get(i).getLongitude(), event.get(i).getLatitude()) + " miles" 
                             + " and " + GCDist.Kilometers(event.get(i-1).getLongitude(), 
                             event.get(i-1).getLatitude(),event.get(i).getLongitude(), event.get(i).getLatitude()) + " kilometers.";
           
               event.get(i-1).setComments(geoComment);
          }
	  }
  }

  public static ArrayList<File> createEvent(ArrayList<Events> event) 
  {
    ArrayList<File> files = new ArrayList<File>();
    for (int i = 0; i < event.size(); i++) 
    {
      try 
      {
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
        if (event.get(i).getGeo() != "") 
        {
          output.write("GEO:" + event.get(i).getGeo() + '\n');
        }
        
        /* Comments */
        if (event.get(i).getComments() != null) 
        {
          output.write("COMMENT:" + event.get(i).getComments() + '\n');
        }

        /* End */
        output.write("END:VEVENT\n");
        output.write("END:VCALENDAR");

        /* Close file */
        output.close();
      }
      catch (IOException ioe) 
      {
        ioe.printStackTrace();
      }
    }
    return files;

  }
  
  private static void createTrueEvent(ArrayList<File> listOfFiles) 
  {
    /* Create new file */
    File file = new File("TeamQuatro.ics");
    ArrayList<BufferedReader> alBR = new ArrayList<BufferedReader>();
    String line;
    if (listOfFiles.size() == 0) 
    {
      System.out.println("No files to read.");
      return;
    }

    /* Write to file */
    try {
      BufferedWriter output = new BufferedWriter(new FileWriter(file));

      for (int i = 0; i < listOfFiles.size(); i++) 
      {

        BufferedReader br = new BufferedReader(new FileReader(
            listOfFiles.get(i)));
        alBR.add(br);

      }

      if (listOfFiles.size() == 1) 
      {
        for (int i = 0; i < listOfFiles.size(); i++) 
        {
          while ((line = alBR.get(i).readLine()) != null) 
          {
            output.write(line + '\n');
          }
        }
        output.close();
        return;
      }
      else 
      {
        for (int i = 0; i < 1; i++) 
        {
          line = alBR.get(i).readLine();
          do 
          {
            output.write(line + '\n');
            line = alBR.get(i).readLine();
          } while (!line.equals("END:VCALENDAR"));
        }

        for (int i = 1; i < listOfFiles.size(); i++) 
        {
          do 
          {
            line = alBR.get(i).readLine();
          } while (!line.equals("BEGIN:VEVENT"));
          do 
          {
            output.write(line + '\n');
            line = alBR.get(i).readLine();
          } while (!line.equals("END:VCALENDAR"));

        }
        output.write("END:VCALENDAR\n");
        output.close();

      }
      
    }
    catch (IOException e1) 
    {
      e1.printStackTrace();
    }

  }
  
}
