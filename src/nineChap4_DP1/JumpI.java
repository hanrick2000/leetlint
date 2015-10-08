package nineChap4_DP1;

public class JumpI {
  public static void main(String[] args) {
    int[] A = new int[] {0, 8, 2, 0, 9}; // {2, 3, 1, 1, 4};
    boolean ans = false;

    ans = jumpDP1st(A);
    System.out.println("\nI use Dp without optimize: " + ans);

    ans = jumpDPlint(A);
    System.out.println("\nI use DP: " + ans);

    ans = jumpLocalGlobalDP(A);
    System.out.println("\nI use Greedy + DP: " + ans);

    ans = canJump(A);
    System.out.println("\nI use Greedy + DP by Code Ganker: " + ans);
  }

  /**
   * Here my optimaly equation is not quite fulfill the meaning of given condition, since A[i] means
   * the max place it can jump from ith position, then it has no reason to jump to far and get back
   * to jump to the final position. The real problem is here I didn't break after I found k can be
   * reached but keep on looping k. so F[i] may be overwritten to false! It's easy to solve, simply
   * OR the result
   * 
   * @param A
   * @return
   */
  public static boolean jumpDP1st(int[] A) {
    boolean[] F = new boolean[A.length]; // F[i] means if ith position can be reached from 1st
                                         // position
    F[0] = true;
    for (int i = 1; i < A.length; ++i) {
      F[i] = false;
      for (int k = 0; k < i; ++k) {
        F[i] |= F[k] && A[k] >= i - k;
        // for (int k = 0; k < A.length && k != i; ++k) {
        // F[i] = F[k] && A[k] >= Math.abs(k - i); // should not check un-reached k (aka: k > i)
      }
    }
    // for (boolean f : F)
    // System.out.print(f + " ");
    return F[A.length - 1];
  }

  /**
   * 9chap's solution, need to well define the state and its optimally equation! This is a DP
   * problem for existence, so must break once I found true, otherwise it maybe overwritten. General
   * DP solution, but O(n**2) time and O(n) space.
   * 
   * @param A
   * @return
   */
  public static boolean jumpDPlint(int[] A) {
    boolean[] F = new boolean[A.length];
    F[0] = true;
    for (int i = 1; i < A.length; ++i) {
      F[i] = false;
      for (int k = 0; k < i; ++k) {
        if (F[k] && A[k] >= i - k) {
          F[i] = true;
          break; // As soon as I found this node is reachable, I break the loop
        }
      }
    }
    // for (boolean f : F)
    // System.out.print(f + " ");
    return F[A.length - 1];
  }

  /**
   * local+global DP, and the idea is Greedy. Super nice, since it is only O(n) for time, O(1) for
   * space. Learn from Ganker
   * 
   * @param A
   * @return
   */
  public static boolean jumpLocalGlobalDP(int[] A) {
    if (A == null || A.length == 0)
      return false;
    // int local = 0, global = 0;
    int[] local = new int[A.length]; // the farthest position can I jump from position i
    int[] global = new int[A.length]; // the farthest position I can reach from position 0 to i.

    // init base condition
    local[0] = A[0];
    global[0] = A[0];
    // build the local/global DP table
    for (int i = 1; i <= global[i - 1] && i < A.length; ++i) { // notice the boundary must change!
      local[i] = A[i] + i;
      global[i] = Math.max(global[i - 1], local[i]);
    }
    // ans
    if (global[A.length - 1] >= A.length - 1) {
      return true;
    }
    return false;
  }

  /**
   * Ganker's Greedy DP solution
   * 
   * @param A
   * @return
   */
  public static boolean canJump(int[] A) {
    if (A == null || A.length == 0)
      return false;
    int reach = 0;
    for (int i = 0; i <= reach && i < A.length; i++) {
      reach = Math.max(A[i] + i, reach);
    }
    if (reach < A.length - 1)
      return false;
    return true;
  }
}
