import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;


public class iCalendarTest {

  @Test
  public void testGetStartTime() {
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
  public void testGetEndTime() {
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
  public void testGeoLocation() {
      Events temp = new Events();
      String geo = "84.5657;134.53435";
      temp.setGeo(geo);
      assertEquals("Geographic locations do not match", "84.5657;134.53435", temp.getGeo());
  }
  
  @Test
  public void testDistance(){
    double lat1 = 43.3, lat2 = 90, long1 = 140.23, long2 = 132;
    double calc, calc2;
    
    calc = GCDist.Kilometers(long1, lat1, long2, lat2);
    calc2 = GCDist.Statute_Miles(long1, lat1, long2, lat2);
    
    assertNotNull(calc);
    assertNotNull(calc2);
    assertNotSame(calc, calc2);
  }
  
  @Test
  public void testClassification(){
    Events temp = new Events();
    String classif = "PRIVATE";
    temp.setClassi(classif);
    assertEquals("Classification does not match", "PRIVATE", temp.getClassi());
  }


}
