package JiuChap1_intro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/count-of-smaller-number-before-itself/ Created at 6:30 PM on
 * 11/24/15.
 */
public class CountSmallerNumberBeforeIt {
  public static void main(String[] args) {
    int[] A = new int[] {1,2,7,8,5}; //{7, 4, 5, 7, 5}; //{5, 1, 4, 6, 13, 13, 13, 18};
    ArrayList<Integer> ans = new CountSmallerNumberBeforeIt().countOfSmallerNumberII(A);
    Arrays.sort(A);
    System.out.println(ans);
  }

  /**
   * @param A: An integer array
   * @return: Count the number of element before this element 'ai' is smaller than it and return
   * count number array
   */
  public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
    // write your code here
    ArrayList<Integer> result = new ArrayList<>();
    if (A == null || A.length == 0) {
      return result;
    }
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    Map<Integer, Integer> map = new HashMap<>();
    for (int a : A) {
      min = Math.min(a, min);
      max = Math.max(a, max);
      map.put(a, 0);
    }
    SegmentTreeQueryII stq2 = new SegmentTreeQueryII();
    SegmentTreeQueryII.SegmentTreeNode root = stq2.build(A);
    for (int i : A) {
      setTree(root, i, 0);
    }

    for (int i = 0; i < A.length; ++i) {
      int val = A[i];
      int cnt = map.get(val) + 1;
      map.put(val, cnt);
      setTree(root, val, cnt);
      result.add(stq2.query(root, min, val - 1));
    }
    return result;
  }

  private int setTree(SegmentTreeQueryII.SegmentTreeNode root, int val, int cnt) {
    if (root.start == val && root.end == val) {
      int oriCnt = root.count;
      root.count = cnt;
      return oriCnt - root.count;  // diff of cnt
    }
    int mid = root.start + (root.end - root.start) / 2;
    int lcnt = 0, rcnt = 0;
    if (val <= mid) {
      lcnt = setTree(root.left, val, cnt);
    } else {
      rcnt = setTree(root.right, val, cnt);
    }
    root.count -= (lcnt + rcnt);  // update upto root
    return lcnt + rcnt;
  }
}
