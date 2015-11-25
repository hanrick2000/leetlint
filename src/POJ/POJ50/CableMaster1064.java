package POJ.POJ50;

/**
 * http://algorithm.yuanbin.me/zh-cn/binary_search/wood_cut.html
 * http://algorithm.yuanbin.me/zh-cn/basics_algorithm/binary_search.html
 * http://poj.org/problem?id=1064
 * Created at 9:30 PM on 11/24/15.
 */
public class CableMaster1064 {
  public static void main(String[] args) {
    int[] W = new int[]{232, 124, 456};
    int res = new CableMaster1064().cableCut(W, 7);
    System.out.println(res);
  }
  /**
   * Classical Binary Search, from <>tiao zhan cheng xu jing sai 2nd</>
   *return: The maximum length of the small pieces.
   */
  public int cableCut(int[] L, int k) {
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
