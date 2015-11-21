package JiuChap5_DynamicProgram;

/**
 * http://www.lintcode.com/en/problem/coins-in-a-line/
 * Created at 11:47 AM on 11/12/15.
 */
public class CoinsInLintI {
  public static void main(String[] args) {
    CoinsInLintI cl1= new CoinsInLintI();
    for (int i = 0; i < 16; ++i) {
      System.out.println(cl1.firstWillWin(i));
    }
  }

  public boolean firstWillWin(int n) {
    int[] P = new int[n+1];
    P[0] = 2;
    return dfs(n, P);
  }

  /**
   * 9 chap's memorization DP: so the recursion direction is from n to smallest states, so dfs(n-k).
   * If iteration DP, then it's normally smallest state => last state
   * @param n
   * @param P
   * @return
   */
  private boolean dfs(int n, int[] P) {  // 0 is empty, 1 is false, 2 is true
    if (P[n] != 0) {
      if (P[n] == 1) {
        return false;
      }
      else {
        return true;
      }
    }

    if (n == 1) {
      P[1] = 2;
      //return true;
    }
    else if (n == 2) {
      P[2] = 2;
      //return true;
    }
    else if (n == 3) {
      P[3] = 1;
      //return false;
    }
    else {
      boolean res = dfs(n-2, P) && dfs(n-3, P) || dfs(n-3, P) && dfs(n-4, P);
      P[n] = res ? 2 : 1;
    }

    return P[n] == 2;

    //P[n] = P[n-2] + P[n-3] == 4 || P[n-3] + P[n-4] == 4 ? 2 : 1;
    //boolean res = dfs(n-2, P) && dfs(n-3, P) || dfs(n-3, P) && dfs(n-4, P);
    //P[n] = res ? 2 : 1;
    //return res;
  }
}
