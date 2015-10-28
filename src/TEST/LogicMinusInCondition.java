package TEST;

/**
 * I set a global var to init cnter for 1hz task to reset ADA, but the != 0 part seems not working.
 *
 * Created this class in TEST at 4:02 PM, 10/27/2015.
 */
public class LogicMinusInCondition {
  public static void main(String[] args) {
    int[] cnt = new int[]{5};

    while (true) {
      System.out.println("In loop");
      int tmp = 0;
      if ((tmp = simple(cnt)) == 99) {
        break;
      }
    }
  }

  private static int simple(int[] i) {
    System.out.println("---");
    if (i[0] != 0 && --i[0] == 0) { // it does work as it is.
      System.out.println("He");
      return 99;
    }

    return 0;
  }
}
