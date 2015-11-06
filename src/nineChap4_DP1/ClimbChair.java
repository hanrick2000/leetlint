package nineChap4_DP1;

public class ClimbChair {
  public static void main(String[] args) {
    int n = 5;
    int ans = climbChairIteration(n);
    System.out.println("If I do Iteration: " + ans);

    ans = climbChairDP(n);
    System.out.println("If I do recursion: " + ans);

    ans = climbChairDP(n);
    System.out.println("If I do recursion dummy: " + ans);

    ans = climbChairDProlling(n);
    System.out.println("If I do recursion rolling: " + ans);
  }

  /**
   * Divide and Conquer, recursion with return val
   * 
   * @param n
   * @return
   */
  public static int climbChairRecDummy(int n) {
    if (n < 0)
      return -1;
    if (n < 3) {
      return n;
    }
    int n1 = climbChairDP(n - 1);
    int n2 = climbChairDP(n - 2);
    return n1 + n2;
  }

  /**
   * Iteration way to solve, I first thinking in recursion way, wrong!
   * 
   * @param n
   * @return
   */
  public static int climbChairIteration(int n) {
    int f1 = 1;
    int f2 = 2;
    int ans = 0;
    if (n == 1) {
      return f1;
    }
    if (n == 2) {
      return f2;
    }
    for (int i = 3; i < n + 1; ++i) {
      ans = f1 + f2;
      f1 = f2;
      f2 = ans;
    }
    return ans;

  }

  /**
   * Simple dynmaic programming with O(n) space.
   * 
   * @param n
   * @return
   */
  public static int climbChairDP(int n) {
    int[] F = new int[n + 1];
    F[0] = 0;
    F[1] = 1;
    F[2] = 2;
    // F[3] = 3;
    // if (n <= 3) Actually, we don't need to early return, it will be checked in the loop
    // return F[n];
    for (int i = 3; i < n + 1; ++i) {
      F[i] = F[i - 1] + F[i - 2];
    }
    return F[n];
  }

  /**
   * Super simple way to optimize the space from O(n) to O(1).
   * 
   * @param n
   * @return
   */
  public static int climbChairDProlling(int n) {
    int[] F = new int[3];
    F[0] = 0;
    F[1] = 1;
    F[2] = 2;
    // F[3] = 3;
    // if (n < 3) same reason as dummy recursion
    // return F[n];
    for (int i = 3; i < n + 1; ++i) {
      F[i % 3] = F[(i - 1) % 3] + F[(i - 2) % 3]; // awesome
    }
    return F[n % 3];
  }

  /**
   * A much better way to do it, similar to Fibonacci with pow(x,n) by doing binary way-->O(lgn)
   * http://blog.csdn.net/linhuanmars/article/details/23976963
   */
}
