import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;


public class iCalendarTest 
{

  @Test
  public void testGetStartTime() 
  {
      Events temp = new Events();
      String year, month, day, time, datetime;
      time = "120000";
      year = "2016";
      month = "03";
      day = "11";
      datetime = year + month + day;
      temp.setDTStart(datetime + 'T' + time);
      assertEquals("20160311T120000", temp.getDTStart());
  }
  
  @Test
  public void testGetEndTime() 
  {
      Events temp = new Events();
      String year, month, day, time, datetime;
      time = "130000";
      year = "2016";
      month = "03";
      day = "11";
      datetime = year + month + day;
      temp.setDTEnd(datetime + 'T' + time);
      assertEquals("20160311T130000", temp.getDTEnd());
  }
  
  @Test
  public void testGeoLocation() 
  {
      Events temp = new Events();
      String geo = "84.5657;134.53435";
      temp.setGeo(geo);
      assertEquals("Geographic locations do not match", "84.5657;134.53435", temp.getGeo());
  }
  
  @Test
  public void testClassification()
  {
    Events temp = new Events();
    String classif = "PRIVATE";
    temp.setClassi(classif);
    assertEquals("Classification does not match", "PRIVATE", temp.getClassi());
  }

  
}
