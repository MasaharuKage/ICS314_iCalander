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
   private String comments; //comments 
   private double longitude; //longitude
   private double latitude; //latitude
   
   private int day;
   private int month;
   private int year;
   private long STime;
   private long ETime;
   
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
      setComments("");
      day = 0;
      month = 0;
      year = 0;
      STime = 0;
      ETime = 0;
      longitude = -500;
      latitude = -500;
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
   
   public String getComments() 
   {
      return comments;
   }
   
   public int getDay() 
   {
	  return day;
   }
   
   public int getMonth() 
   {
	  return month;
   }

   public int getYear()
   {
	  return year;
   }
   
   public long getStart()
   {
	  return STime;
   }
   
   public long getEnd()
   {
	  return ETime;
   }

   public double getLatitude() 
   {
      return latitude;
   }
   
   public double getLongitude() 
   {
      return longitude;
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

  public void setComments(String comments) {
    this.comments = comments;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setDay(int day) 
  {
	  this.day = day;
  }
  
  public void setMonth(int month) 
  {
	  this.month = month;
  }

  public void setYear(int year)
  {
	  this.year = year;
  }
  
  public void setStart(long STime)
  {
	  this.STime = STime;
  }
  
  public void setEnd(long ETime)
  {
	  this.ETime = ETime;
  }
  
}