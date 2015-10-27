package JiuChap1_intro;

import misc.Interval;
import misc.SegmentTreeNode;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/interval-minimum-number/
 * Created this class in JiuChap0_intro at 11:16 PM, 10/26/2015.
 */
public class IntervalMinNum {

  public static void main(String[] args) {
    IntervalMinNum imm = new IntervalMinNum();
    imm.test();
  }

  public void test() {
    int[] data = new int[]{1, 2, 7, 8, 5};
    ArrayList<Interval> itv = new ArrayList<>(); // new Arrays.asList(new Interval[]{{1,2}, {0,4}, {2,4}};)
    itv.add(new Interval(1, 2));
    itv.add(new Interval(0, 4));
    itv.add(new Interval(2, 4));
    ArrayList<Integer> result = new IntervalMinNum().intervalMinNumber(data, itv);
    System.out.println(result);
  }

  /**
   * @param A, queries: Given an integer array and an query list
   * @return: The result list
   */
  public ArrayList<Integer> intervalMinNumber(int[] A, ArrayList<Interval> queries) {
    // write your code here
//    for (int i : A) {
//      i *= -1;
//    }
    for (int i = 0; i < A.length; ++i) {
      A[i] *= -1;
    }
    SegmentTreeBuildII stb2 = new SegmentTreeBuildII();
    SegmentTreeNode root = stb2.build(A);
    SegmentTreeQuery stq = new SegmentTreeQuery();

    ArrayList<Integer> min = new ArrayList<>();

//    SegmentTreeNode.inOdr(root);
    for (Interval itv : queries) {
      min.add(stq.query(root, itv.start, itv.end) * (-1));
    }

    return min;
  }
}
