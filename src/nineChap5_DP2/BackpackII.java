package nineChap5_DP2;

public class BackpackII {
  public static void main(String[] args) {
    int ans = 0;
    int[] data = {2, 3, 5, 7};
    int[] val = {1, 5, 2, 4};
    int size = 13;
    ans = backpackII1(size, data, val);
    System.out.println("I use simple DP: " + ans);
  }

  /**
   * 
   * @param m: An integer m denotes the size of a backpack
   * @param A: Given n items with size A[i]
   * @return: the maximum value
   */
  public static int backpackII1(int m, int[] A, int[] V) {
    if (A == null || A.length == 0 || V == null || A.length != V.length) {
      return -1;
    }
    if (m < A[0]) {
      return -1;
    }

    int aLen = A.length;

    int[][] F = new int[aLen + 1][m + 1];

    for (int i = 0; i < aLen + 1; ++i) {
      F[i][0] = 0;
    }
    for (int j = 1; j < m + 1; ++j) { // notice the range starts from 1! NOTICE: no overwritten
      F[0][j] = Integer.MIN_VALUE; // why
    }

    int ans = -1; // global optimal
    for (int i = 1; i < aLen + 1; ++i) {
      for (int j = 1; j < m + 1; ++j) {
        if (j < A[i - 1]) {
          continue;
        }
        F[i][j] = Math.max(F[i - 1][j], F[i - 1][j - A[i - 1]] + V[i - 1]);
        ans = Math.max(ans, F[i][j]);
      }
    }

    return ans;
  }
  
  /**
   * @param m: An integer m denotes the size of a backpack
   * @param A & V: Given n items with size A[i] and value V[i]
   * @return: The maximum value
   */
  public static int backPackII2(int m, int[] A, int V[]) {
      // write your code here
    int len = A.length;
    int[][] F = new int[len+1][m+1];
    //TODO
    
    return 0; // ttt
  }
}
