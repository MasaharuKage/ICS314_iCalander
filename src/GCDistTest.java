import static org.junit.Assert.*;

import org.junit.Test;


public class GCDistTest {

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
  
}
