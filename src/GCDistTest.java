import static org.junit.Assert.*;

import org.junit.Test;


public class GCDistTest {
  
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
  public void testDistanceMiles() {
    double temp = GCDist.Statute_Miles(21.278095, -157.704884, 21.278095, -157.704884);
    assertTrue(temp == 0.0);
   
  }

  @Test
  public void testDistanceKilometers() {
    double temp = GCDist.Kilometers(21.278095, -157.704884, 21.278095, -157.704884);
    assertTrue(temp == 0.0);
   
  }
  
  @Test
  public void testDistanceMiles2() {
    double temp = GCDist.Statute_Miles(0, 180, 0, 0);
    assertTrue(temp == 12428.46);
  }
  
  @Test
  public void testDistanceKilometers2() {
    double temp = GCDist.Kilometers(0, 180, 0, 0);
    System.out.println(temp);
    assertTrue(temp == 20001.600000000002);
  }
  
}