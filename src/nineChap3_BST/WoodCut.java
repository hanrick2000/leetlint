package nineChap3_BST;

/**
 * Created at 9:48 PM on 11/24/15.
 */
public class WoodCut {
  public static void main(String[] args) {
    int[] W = new int[]{232, 124, 456};
    int res = new WoodCut().woodCut(W, 7);
    System.out.println(res);
  }
  /**
   *@param L: Given n pieces of wood with length L[i]
   *@param k: An integer
   *return: The maximum length of the small pieces.
   */
  public int woodCut(int[] L, int k) {
    // write your code here
    if (L == null || L.length == 0) {
      return 0;
    }
    int lb = 0, ub = Integer.MIN_VALUE;
    for (int l : L) {
      if (l > ub) {
        ub = l+1;
      }
    }

    while (lb + 1 < ub) {
      int mid = lb + (ub-lb)/2;
      if (C(L, k, mid)) {
        lb = mid;
      }
      else {
        ub = mid;
      }
    }

    return lb;
  }

  private boolean C(int[] L, int k, int len) {
    int sum = 0;
    for (int i = 0; i < L.length; ++i) {
      sum += L[i]/len;
    }
    return sum >= k;
  }
}
