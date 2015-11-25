package JiuChap1_intro;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created at 6:44 PM on 11/24/15.
 */
public class CountSmallerNumber {
  public static void main(String[] args) {
    int[] A = new int[]{5, 13, 13, 13, 18}; //{55,81,56,91,35,92,10,53,27,94,64,45,19,44,52,19,79,12,16,90,97,33,73,2,20,68,19,7,17,62,45,48,62,26,85,4,63,67,56,16}; //{1,2,7,8,5};
    int[] Q = new int[]{17};
    ArrayList<Integer> ans = new CountSmallerNumber().countOfSmallerNumber(A, Q);
    Arrays.sort(A);
    System.out.println(ans);
  }
  /**
   * @param A: An integer array
   * @return: The number of element in the array that
   *          are smaller that the given integer
   */
  public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
    // write your code here
    SegmentTreeQueryII stq2 = new SegmentTreeQueryII();
    SegmentTreeQueryII.SegmentTreeNode root = stq2.build(A);
    ArrayList<Integer> result = new ArrayList<>();
    for (int i : queries) {
      result.add(stq2.query(root, 0, i-1));
    }
    return result;
  }
}
