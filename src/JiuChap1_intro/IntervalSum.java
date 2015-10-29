package JiuChap1_intro;

import misc.Interval;

import java.util.ArrayList;

/**
 * http://www.lintcode.com/en/problem/interval-sum/
 * Created this class in JiuChap1_intro at 11:51 PM, 10/26/2015.
 */
public class IntervalSum {

  public static ArrayList<Long> simple(int[] A, ArrayList<Interval> queries) {
    int[] preSum = new int[A.length];
    ArrayList<Long> result = new ArrayList<>();
    for (int i = 0; i < A.length; ++i) {
      if (i == 0) {
        preSum[i] = A[0];
        continue;
      }
      preSum[i] = preSum[i-1] + A[i];
    }

    for (Interval itv : queries) {
      if (itv.start == 0) {
        result.add((long) preSum[itv.end]);
      } else {
        result.add((long) (preSum[itv.end] - preSum[itv.start-1]));
      }
    }
    result.stream().forEach(System.out::println);
    return result;
  }


  public static void main(String[] args) {
    IntervalSum is = new IntervalSum();
    int[] data= new int[]{1,2,7,8,5};
    ArrayList<Interval> queries = new ArrayList<>();
    queries.add(new Interval(0,4));
    queries.add(new Interval(1,2));
    queries.add(new Interval(2,4));
//    is.intervalSum(data, queries);
    simple(data, queries);
  }
  /**
   *@param A, queries: Given an integer array and an query list
   *@return: The result list
   */
  public ArrayList<Long> intervalSum(int[] A,
                                     ArrayList<Interval> queries) {
    // write your code here
    SegmentTreeNode dummy = new SegmentTreeNode(0,0,0);
    SegmentTreeNode root = dummy.build(A, 0, A.length - 1);

    ArrayList<Integer> result = new ArrayList<>();
    for (Interval itv : queries) {
      result.add(dummy.query(root, itv.start, itv.end));
    }
    System.out.println(result);
    return null;
  }

  public class SegmentTreeNode {
    int start, end, sum;
    SegmentTreeNode left, right;

    SegmentTreeNode(int start, int end, int sum) {
      start = start;
      end = end;
      sum = sum;
      left = right = null;
    }

    SegmentTreeNode build(int[] data, int start, int end) {
      if (start > end) {
        return null;
      }

      return bHelper(data, start, end);
    }

    SegmentTreeNode bHelper(int[] data, int start, int end) {
      if (start > end) {
        return null;
      }
      if (start == end) {
        return new SegmentTreeNode(start, end, data[start]);
      }

      int mid = start + (end - start)/ 2;
      SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
      SegmentTreeNode left = bHelper(data, start, mid);
      SegmentTreeNode right = bHelper(data, mid+1, end);
      root.left = left;
      root.right = right;

      root.sum = root.left.sum + root.right.sum;
      return root;
    }

    int query(SegmentTreeNode root, int start, int end) {
      if (root.start == start && root.end == end) {
        return root.sum;
      }

      int mid = root.start + (root.end - root.start) / 2;
      int sumL = 0, sumR = 0;
      if (start <= mid) {
        if (end <= mid) {
          sumL = query(root.left, start, end);
        }
        else {
          sumL = query(root.left, start, mid);
        }
      }
      if (end > mid) {
        if (start > mid) {
          sumR = query(root.right, start, end);
        }
        else {
          sumR = query(root.right, mid+1, end);
        }
      }

      return sumR + sumL;
    }

    void modify(SegmentTreeNode root, int index, int value) {

    }
  }


}
