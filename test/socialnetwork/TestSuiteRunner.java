package socialnetwork;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*LABTS DOES NOT SUPPORT OUR TESTING THIS WEEKEND AND TIMES OUT UNDER HEAVY CODE,
KONSTANTINOS HAS REQUESTED THAT WE ASK OUR PPTS TO CHECK TESTS INDIVIDUALLY
ON THEIR OWN MACHINES
 */

public class TestSuiteRunner {
  public static void main(String[] args) {
    Result result =
        JUnitCore.runClasses(BasicTestsFine.class, StressTestsFine.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }
}
