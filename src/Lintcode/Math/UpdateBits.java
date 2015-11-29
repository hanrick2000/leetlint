package Lintcode.Math;

/**
 * http://www.lintcode.com/en/problem/update-bits/
 * Created at 5:15 PM on 11/21/15.
 */
public class UpdateBits {
  /**
   *@param n, m: Two integer
   *@param i, j: Two bit positions
   *return: An integer
   */
  int updateBits(int n, int m, int i, int j) {
    int mask;
    if (j < 31) {
      mask = ~((1 << (j + 1)) - (1 << i));
    } else {
      mask = (1 << i) - 1;
    }
    return (m << i) + (mask & n);
  }
}
