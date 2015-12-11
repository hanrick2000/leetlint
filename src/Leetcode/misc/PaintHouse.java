package Leetcode.misc;

/**
 * Created at 10:09 AM on 11/30/15.
 */
public class PaintHouse {
  public static void main(String[] args) {
    int posts = 5, colors = 2;
    int ans = new PaintHouse().numWays(posts, colors);
    System.out.println(ans);
  }
  public int numWays(int n, int k) {
    if (n == 0 || k == 0) {
      return 0;
    }
    if (n == 1) {
      return k;
    }
    int last2S = k;
    int last2D = k * (k-1);
    for (int i = 2; i < n; ++i) {
      int tmp = last2D;
      last2D = (k-1) * last2S + (k-1) * last2D;
      last2S = tmp;
    }
    return last2D + last2S;
  }
}
