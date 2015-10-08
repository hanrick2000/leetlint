package nineChap5_DP2;

public class BackpackI {
  public static void main(String[] args) {
    int ans = 0;
    int[] data = {2,3,5,7};
    int size = 12;
    ans = backpackI1(size, data);
    System.out.println("I use simple DP: " + ans);
    
    ans = backPackI2(size, data);
    System.out.println("Second trial: " + ans);
  }
  
  /**
   * 
   * @param m: An integer m denotes the size of a backpack
   * @param A: Given n items with size A[i]
   * @return: the maximum size
   */
  public static int backpackI1(int m, int[] A) {
    if (A == null || A.length == 0) {
      return -1;
    }
    if (m < A[0]) {
      return -1;
    }
    int aLen = A.length;
    boolean[][] F= new boolean[aLen+1][m+1];  // if I can get size of m with first i items
    
    for (int i = 0; i < aLen+1; ++i) {
      F[i][0] = true;
    }
    
    for (int n = 1; n < m+1; ++n) {
      F[0][n] = false;
    }
    
    int ans = -1;
    for (int i = 1; i < aLen+1; ++i) {
      for (int n = 1; n < m+1; ++n) {
        if (n < A[i-1]) {
          continue;
        }
        F[i][n] = F[i-1][n-A[i-1]] || F[i-1][n];
        if (F[i][n] == true) {
          ans = n;
        }
      }
    }
    
    return ans;
  }
  
  public static int backPackI2(int m, int[] A) {
    // write your code here
    int len = A.length;
    boolean[][] F = new boolean[len+1][m+1];
    for (int j = 1; j < m+1; ++j) {
        F[0][j] = false;
    }
    for (int i = 0; i < len+1; ++i) {
        F[i][0] = true;
    }
    int mx = -1;
    for (int i = 1; i < len+1; ++i) {
        for (int j = 0; j < m+1; ++j) {
            F[i][j] = F[i-1][j] | ((j<A[i-1])? false : F[i-1][j-A[i-1]]);
            if (F[i][j] == true)  mx = Math.max(mx, j);
        }
    }
    
    return mx;
}
}
