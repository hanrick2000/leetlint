package nineChap4_DP1;

public class JumpII {
  public static void main(String[] args) {
    int[] A = new int[] {2, 3, 1, 1, 4};
    int ans = -1;
    ans = jumpDP1st(A);
    System.out.println("I use pure DP 1st trial: " + ans);
  }

  /**
   * The same DP as i did in Jump I, the only diff is that I'm looking for the min value.
   * 
   * @param A
   * @return
   */
  public static int jumpDP1st(int[] A) {
    if (A == null || A.length == 0)
      return -1;
    int len = A.length;
    int[] F = new int[len]; // the shortest jump to position i
    F[0] = 0;

    for (int i = 1; i < len; ++i) {
      F[i] = -1;
      for (int k = 0; k < i; ++k) {
        if (F[k] != -1 && A[k] >= i - k) {
          F[i] = F[i] == -1 ? F[k] + 1 : Math.min(F[i], F[k] + 1);
        }
      }
    }
    // for (int f : F)
    // System.out.print(f + " ");
    return F[len - 1];
  }

  /**
   * 9chap's solution. Smart optimize to stop at the first reachable F[k]. Because any later k will
   * be greater than this F[k]
   * 
   * @param A
   * @return
   */
  public static int jumpDP2nd(int[] A) {
    int len = A.length;
    int[] F = new int[len];
    F[0] = 0;

    for (int i = 1; i < len; ++i) {
      F[i] = -1;
      for (int k = 0; k < i; ++k) {
        if (F[k] != -1 && A[k] >= i - k) {
          F[i] = F[k] + 1;
          break;
        }
      }
    }
    return F[len - 1];
  }
  
  /**
   * How to do greedy??? don't understand Ganker's solution.
   * @param A
   * @return
   */
//  public static int jumpGreedy(int[] A) {
//    int len = A.length;
//    int[] local = new int[len];
//    int[] global = new int[len];
//    local[0] = 1;
//    global[0] = 1;
//    
//    for (int i = 1; i<A[i-1] && i < len; ++i) {
//      if (A[])
//    }
//  }
}
