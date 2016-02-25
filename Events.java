/*
 * Events Object class
 *
 * Team Quatro
 */

/* Package */
package iCalendar;

public class Events
{
   /* Information for each event */
   private String version;  //Version
   private String summary;  //Name of event
   private String DTStart;  //Start date & time
   private String DTEnd;    //End date & time
   private String location; //Location of event
   private String TimeZone; //Time Zone


   /* Constructor */
   public Events()
   {
      /* Initialize */
      setVer("2.0");
      setInfo("");
      setDTStart("");
      setDTEnd("");
      setLocation("");
      setTZ("");
   }

   /* Get functions */
   public String getVer()
   {
      return version;
   }
   public String getInfo()
   {
      return summary;
   }

   public String getTZ()
   {
      return TimeZone;
   }

   public String getDTStart()
   {
      return DTStart;
   }

   public String getDTEnd()
   {
      return DTEnd;
   }

   public String getLocation()
   {
      return location;
   }

   /* Set functions */
   public void setVer(String version)
   {
      this.version = version;
   }
   
   public void setInfo(String summary)
   {
      this.summary = summary;
   }

   public void setTZ(String TimeZone)
   {
      this.TimeZone = TimeZone;
   }

   public void setDTStart(String DTStart)
   {
      this.DTStart = DTStart;
   }

   public void setDTEnd(String DTEnd)
   {
      this.DTEnd = DTEnd;
   }

   public void setLocation(String Location)
   {
      this.location = Location;
   }
}