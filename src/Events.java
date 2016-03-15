/*
 * Events Object class
 *
 * Team Quatro
 */

public class Events
{
   /* Information for each event */
   private String version;  //Version
   private String prodid; //Unique Product Identifier 
   private String uid; //Unique Calendar Identifier
   private String summary;  //Name of event
   private String DTStart;  //Start date & time
   private String DTEnd;    //End date & time
   private String location; //Location of event
   private String TimeZone; //Time Zone
   private String Description; //Description of event
   private String Geo; //Geographic position
   private String classification; //Classification 

   /* Constructor */
   public Events()
   {
      /* Initialize */
      setVer("2.0");
      setProdid("-//UHMANOA_ICS314_SPRING_2016//TEAM_QUATRO//ICAL_EVENT_MAKER");
      setInfo("");
      setDescrip("");
      setDTStart("");
      setDTEnd("");
      setLocation("");
      setGeo("");
      setTZ("");
      setClassi("");
   }

   /* Get functions */
   public String getVer()
   {
      return version;
   }
   
   public String getProdid()
   {
     return prodid;
   }
   
   public String getUid() 
   {
    return uid;
   }
   
   public String getInfo()
   {
      return summary;
   }
   
   public String getDescrip()
   {
     return Description;
   }
   
   public String getClassi()
   {
     return classification;
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
   
   public String getGeo()
   {
     return Geo;
   }

   /* Set functions */
   public void setVer(String version)
   {
      this.version = version;
   }
   
   public void setProdid(String prodid) 
   {
    this.prodid = prodid;
   }
   
   public void setUid(String uid) 
   {
    this.uid = uid;
   }
   
   public void setInfo(String summary)
   {
      this.summary = summary;
   }
   
   public void setDescrip(String Description)
   {
     this.Description = Description;
   }
   
   public void setClassi(String classification)
   {
     this.classification = classification;
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
   
   public void setGeo(String Geo)
   {
     this.Geo = Geo;
   }

}