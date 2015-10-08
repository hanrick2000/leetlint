package nineChap4_DP1;

/**
 * Nine Chap's introduction in DP, the first example is the triangle Path sum problem. Here gives
 * the ideas from DFS -> DFS optmize -> Memorized Search DP -> Bottom-up DP
 */
import java.util.ArrayList;

public class Triangle {
  public static ArrayList<ArrayList<Integer>> tri; // make it as class public so as to be used in
                                                   // dfs

  public static void main(String[] args) {
    tri = new ArrayList<ArrayList<Integer>>();
    /*
     * ”√Anonymous Child Class¿¥init ArrayList
     * http://mangstacular.blogspot.com/2013/04/4-ways-to-initialize-list-in-java.html
     */
    ArrayList<Integer> level1 = new ArrayList<Integer>() {
      {
        add(2);
      }
    };
    ArrayList<Integer> level2 = new ArrayList<Integer>() {
      {
        add(3);
        add(4);
      }
    };
    ArrayList<Integer> level3 = new ArrayList<Integer>() {
      {
        add(6);
        add(5);
        add(7);
      }
    };
    ArrayList<Integer> level4 = new ArrayList<Integer>() {
      {
        add(4);
        add(1);
        add(8);
        add(3);
      }
    };
    tri.add(level1);
    tri.add(level2);
    tri.add(level3);
    tri.add(level4);

    int ans = minimumTotal(tri);
    System.out.println("I use Bottom-up DP dummy: " + ans);

    ans = minPathTD(tri);
    System.out.println("I use Top-Down DP: " + ans);

    int[] res = new int[] {Integer.MAX_VALUE};
    minPathTraverseWalkUp(0, 0, 0, res);
    System.out.println("I use DFS via traverse with walk down only: " + res[0]);

    res = new int[] {Integer.MAX_VALUE};
    minPathTraverseWalkDownUp(0, 0, 0, res);
    System.out.println("I use DFS via traverse with walk down and up: "
        + res[0]);

    ans = minPathDC(0, 0);
    System.out.println("I use DFS via Divide and Conquer: " + ans);

    int[][] hash = new int[tri.size()][tri.get(tri.size() - 1).size()];
    for (int i = 0; i < tri.size(); i++) {
      for (int j = 0; j < tri.get(tri.size() - 1).size(); ++j) {
        hash[i][j] = -1;
      }
    }
    ans = minPathDCopt(0, 0, hash);
    System.out.println("I used DFS via optimized D&C: " + ans);

  }

  /**
   * PPT's solution: DFS with traverse. Nice recursion.
   * 
   * @param x
   * @param y
   * @param sum : keep updating the current sum
   * @param min : as the output so make it an array object for call-by-value
   */
  public static void minPathTraverseWalkUp(int x, int y, int sum, int[] min) {
    // System.out.println("we are at: " + x + "," + y);
    if (x == tri.size()) {
      min[0] = Math.min(sum, min[0]);
      return; // always remember to return from recursion.
    }
    minPathTraverseWalkUp(x + 1, y, sum + tri.get(x).get(y), min);
    minPathTraverseWalkUp(x + 1, y + 1, sum + tri.get(x).get(y), min);
    return; // Don't need to walk-up since the min[0] has finished calculation in walk-down
  }

  /**
   * Modify the ppt's traverse recursion method to have both Walk down the tree and Walk up the
   * tree.
   * 
   * @param x
   * @param y
   * @param sum
   * @param min
   */
  public static void minPathTraverseWalkDownUp(int x, int y, int sum, int[] min) {
    // System.out.println("we are at: " + x + "," + y);
    if (x == tri.size()) {
      min[0] = sum;
      return;
    }
    minPathTraverseWalkDownUp(x + 1, y, sum + tri.get(x).get(y), min);
    int l = min[0];
    minPathTraverseWalkDownUp(x + 1, y + 1, sum + tri.get(x).get(y), min);
    int r = min[0];
    min[0] = Math.min(l, r);
    return;
  }

  /**
   * learn how to write in DC recursion. One key of DC is to well define the meaning of recursion
   * method.
   * 
   * @param x : current row
   * @param y : current col
   * @return : the min path sum from (x,y) to current row
   */
  public static int minPathDC(int x, int y) {
    if (x == tri.size()) {
      return 0; // very similar to the base case of DP, since state(x,y) meaning.
    }
    // Divide/Conquer
    int minPath = Math.min(minPathDC(x + 1, y), minPathDC(x + 1, y + 1));
    minPath += tri.get(x).get(y);
    return minPath;
  }

  /**
   * Optimized DFS via Divide and Conquer, here I used an array param to pass the calcuated results
   * to prevent re-do. However, it still TLE, since it is still recursion.
   * 
   * @param x
   * @param y
   * @param Hash : using a hash table to memorize the computede states.
   * @return
   */
  public static int minPathDCopt(int x, int y, int Hash[][]) {
    if (x == tri.size()) {
      return 0;
    }
    if (Hash[x][y] != -1)
      return Hash[x][y];
    Hash[x][y] =
        Math.min(minPathDCopt(x + 1, y, Hash), minPathDCopt(x + 1, y + 1, Hash))
            + tri.get(x).get(y);
    return Hash[x][y];
  }

  /**
   * Top down approach for DP. State: F[x][y] means the min path from (0,0) to (x,y). DP is to reuse
   * computed states, so Top-down is using upper level to calculate lower levels value.
   * 
   * @param triangle
   * @return
   */
  public static int minPathTD(ArrayList<ArrayList<Integer>> triangle) {
    int row = triangle.size();
    int col = triangle.get(row - 1).size();

    int[][] F = new int[row][col];
    F[0][0] = triangle.get(0).get(0);

    for (int i = 1; i < row; ++i) {
      for (int j = 0 /* 1 */; j <= i /* triangle.get(i).size()-1 */; ++j) {
        F[i][j] = Integer.MAX_VALUE;
        if (triangle.get(i - 1).size() - 1 >= j && j >= 0) {
          F[i][j] = Math.min(F[i][j], F[i - 1][j]);
        }
        if (triangle.get(i - 1).size() - 1 >= j - 1 && j - 1 >= 0) {
          F[i][j] = Math.min(F[i][j], F[i - 1][j - 1]);
        }
        F[i][j] += triangle.get(i).get(j);
        // System.out.println(i+", "+j+" : "+ F[i][j]);
      }
    }
    int ans = Integer.MAX_VALUE;
    for (int j = 0; j < col; ++j) {
      // System.out.println(F[row-1][j]);
      ans = Math.min(ans, F[row - 1][j]);
    }
    return ans;
  }

  /**
   * Lintcode solution
   * Bottom-up Dynamic Programming. State: F[x][y] means the min path from (x,y) to lowest level.
   * 
   * @param triangle
   * @return
   */
  public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
    // write your code here
    if (triangle == null || triangle.size() == 0)
      return -1;
    int row = triangle.size();
    int col = triangle.get(row - 1).size();
    // Define Opt[x][y]: is the shortest path sum from (x,y) to lowest row
    int[][] F = new int[row][col];
    // init base condition
    for (int i = 0; i < col; ++i) {
      F[row - 1][i] = triangle.get(row - 1).get(i);
    }
    // Optimality Equation
    for (int i = row - 2; i >= 0; i--) {
      for (int j = 0; j <= i/* triangle.get(i).size() */; ++j) {
        F[i][j] =
            Math.min(F[i + 1][j], F[i + 1][j + 1]) + triangle.get(i).get(j);
      }
    }
    // return ans
    return F[0][0];
  }
}
