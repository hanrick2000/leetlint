package JiuChap5_DynamicProgram;

/**
 * http://www.lintcode.com/en/problem/house-robber/ Created by Administrator on 11/10/15.
 */
public class HouseRobber {
  public static void main(String[] args) {
    int[] House =
        new int[] {828, 125, 740, 724, 983, 321, 773, 678, 841, 842, 875, 377, 674, 144, 340, 467,
            625, 916, 463, 922, 255, 662, 692, 123, 778, 766, 254, 559, 480, 483, 904, 60, 305, 966,
            872, 935, 626, 691, 832, 998, 508, 657, 215, 162, 858, 179, 869, 674, 452, 158, 520,
            138, 847, 452, 764, 995, 600, 568, 92, 496, 533, 404, 186, 345, 304, 420, 181, 73, 547,
            281, 374, 376, 454, 438, 553, 929, 140, 298, 451, 674, 91, 531, 685, 862, 446, 262, 477,
            573, 627, 624, 814, 103, 294, 388}; //{3,8,4};
    HouseRobber hr = new HouseRobber();
    long ans = hr.houseRobber(House);
    System.out.println(ans);
  }

  /**
   * Different from 9chap's solution since my function is defined differently. I'm using Stanford's
   * DP algs.
   *
   * @param A: An array of non-negative integers. return: The maximum amount of money you can rob
   * tonight
   */
  public long houseRobber(int[] A) {
    // write your code here
    if (A == null || A.length == 0) {
      return 0;
    }
    if (A.length == 1) {
      return A[0];
    }

    // P[i] means the optima at ith house. (note: house is 1-base)
    long[] P = new long[3]; //new long[A.length + 1];  // using rolling array
    P[0] = 0;
    P[1] = A[0];
    for (int i = 2; i <= A.length; ++i) {
      P[i%3] = Math.max(P[(i-2)%3] + A[i - 1],
          P[(i - 1)%3]);  // therefore the solution is using house i or not.
    }
    //long ans = Math.max(Math.max(P[0], P[1]), P[2]);
    //return ans;
    return P[(A.length) % 3];
  }

  /**
   * This is to show how to use rolling array: how to decide mod? By the related states in
   * recurrence function Notice the diff of mine/9chap's function definition leads to different
   * meaning.
   */
  public long houseRobber9chap1(int[] A) {
    if (A == null || A.length == 0) {
      return 0;
    }
    if (A.length == 1) {
      return A[0];
    } else if (A.length == 2) {
      return A[1];
    } else if (A.length == 3) {
      return Math.max(A[1], A[0] + A[2]);
    }

    //how to decide mod?
    //Ans by Tian Laoshi: if P[i] = f(P[i-a], P[i-b], P[i-c]), then mod = 4. So: mod = # of related states.
    int mod = 3; // 4, 100 are also ok.
    long[] P = new long[mod]; //new long[A.length];
    P[0] = A[0];
    P[1] = A[1];
    P[2] = A[0] + A[2];

    long ans = 0;
    for (int i = 3; i < A.length; ++i) {
      P[i % mod] = Math.max(P[(i - 3) % mod], P[(i - 2) % mod]) + A[i];
      ans = Math.max(P[i%mod], ans);
    }
    return ans;
  }

  public long houseRobber9chap2(int[] A) {
    if (A == null || A.length == 0) {
      return 0;
    }
    if (A.length == 1) {
      return A[0];
    } else if (A.length == 2) {
      return A[1];
    } else if (A.length == 3) {
      return Math.max(A[1], A[0] + A[2]);
    }

    long[] P = new long[A.length]; //new long[A.length];
    P[0] = A[0];
    P[1] = A[1];
    P[2] = A[0] + A[2];

    long ans = 0;
    for (int i = 3; i < A.length; ++i) {
      P[i] = Math.max(P[i - 3], P[i - 2]) + A[i];
      //ans = Math.max(P[i%mod], ans);
    }

    ans = Math.max(P[A.length - 2], P[A.length - 1]);
    return ans;
  }
}
