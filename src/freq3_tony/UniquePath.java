package freq3_tony;

/**
 * 使用1337分析的第一个方法来解决简单的unique path: backtracking
 * 
 * @author tzhang
 *
 */
public class UniquePath {
//  static final int M_MAX = 100;
//  static final int N_MAX = 100;
  int M_MAX;
  int N_MAX;

  /**
   * backtracking的计算在(r,c)时候的sum(UniquePath)
   * 
   * @param r : 此时的坐标
   * @param c
   * @param m : 终点
   * @param n
   * @return : 次坐标的sum(unique path)
   */
  public int backtracking(int r, int c, int m, int n) {
    if (r == m && c == n) {
      return 1;
    }
    if (r > m || c > n) {
      return 0;
    }

    return backtracking(r + 1, c, m, n) + backtracking(r, c + 1, m, n);
  }

  /**
   * top-down DP (memorization) 来解决Backtracking中重复计算的问题
   * 
   * @param r
   * @param c
   * @param m
   * @param n
   * @return
   */
  public int dynamicProgrammingTopDown(int r, int c, int m, int n, int opt[][]) {
    if (r == m && c == n)
      return 1;
    if (r > m || c > n)
      return 0;

    if (opt[r + 1][c] == -1)
      opt[r + 1][c] = dynamicProgrammingTopDown(r + 1, c, m, n, opt);
    if (opt[r][c + 1] == -1)
      opt[r][c + 1] = dynamicProgrammingTopDown(r, c + 1, m, n, opt);

    return opt[r + 1][c] + opt[r][c + 1];
  }

  /**
   * 通过找规律: 做题之前都是要先观察题目, 找到规律. 不要一上来就想着直接解决问题.
   * 这里可以注意到m行和n列的path都是只有1条. 所以DP之前要初始化. 因为1337很聪明.
   * 只要设定: opt[m][n+1] = 1. 就OK了. 然后直接loop就行了.
   * 
   * 注意 DP 和 recursion是2个概念. 没有直接关系. 例如这里的DP是loop, 不用recursion.
   * 而Top-down的DP则是用到了recursion. 其实recursion只是组成代码布局的一种方法.
   * 
   * @param r
   * @param c
   * @param m
   * @param n
   * @return
   */
  public int dynamicProgrammingButtomUp(int r, int c, int m, int n) {
    int[][] opt = new int[m+2][n+2];
    opt[m][n+1] = 1;    // 好聪明啊!
    for (int i = m; i > 0; i--) {
      for (int j = n; j > 0; j--) {
        opt[i][j] = opt[i+1][j] + opt[i][j+1];
      }
    }
    return opt[1][1];
  }
  /**
   * the client method to init opt and calling the DP methods
   */
  public int DP_client(int m, int n) {
//    M_MAX = m;
//    N_MAX = n;
//    int[][] opt = new int[M_MAX + 2][N_MAX + 2];
//    for (int i = 0; i < M_MAX + 2; i++) {
//      for (int j = 0; j < N_MAX + 2; j++) {
//        opt[i][j] = -1;
//      }
//    }
//    int result = dynamicProgrammingTopDown(1, 1, m, n, opt);
    int result = dynamicProgrammingButtomUp(1,1,m,n);
    return result;
  }

  /**
   * 这种path问题其实都可以当作排列组合问题. 类似的有fibonacci step, random walk, etc. 
   * @return
   */
  public int CombinatorialSolution(int m, int n) {
    if (m == 0 || n == 0)  return 0;
    int x = Math.min(m, n);
    double count = 1;
//    for (int i = 0; i < x-1; i++) {
//      count *= (m+n-2 - i);
//      count /= (x-1-i);
//    }
    int y = Math.max(m, n);  
    for (int i=1; i<x; ++i) {  
      count *= (y + i - 1);  
      count /= i;  
    } 
    return (int)count;
  }
  /**
   * ctor to call the client
   */
  public UniquePath() {
    // System.out.println("Result: " + backtracking(1,1,7,3));
//    System.out.println("result : " + DP_client(7,3));
    System.out.println(CombinatorialSolution(7,3));
  }

  public static void main(String[] args) {
    UniquePath upb = new UniquePath();
  }
}
