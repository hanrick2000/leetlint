package learnJava;

/**
 * Created at 6:32 PM on 11/20/15.
 */
public class RecursionPlay {
  public static void main(String[] args) {
    int[] A = new int[]{4,7,2};
    rec(A, 0);
  }

  private static void rec(int[] A, int step) {
    if (step == A.length) {
      System.out.println("Last");
      return;
    }

    System.out.println("before " + A[step]);

    step++;
    rec(A, step);
    step--;

    System.out.println("after " + A[step]);
  }
}
