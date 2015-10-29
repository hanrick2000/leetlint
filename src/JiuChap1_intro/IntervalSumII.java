package JiuChap1_intro;

/**
 * Created this class in JiuChap1_intro at 8:11 PM, 10/28/2015.
 */
public class IntervalSumII {
  SegmentTreeNode ROOT = null;  // holder

  public static void main(String[] args) {
    IntervalSumII is2 = new IntervalSumII();
    is2.build(new int[]{1,2,7,8,5});
    System.out.println(is2.query(0, 2));
    is2.modify(0, 4);
    System.out.println(is2.query(0, 1));
    is2.modify(2, 1);
    System.out.println(is2.query(2, 2));
    System.out.println(is2.query(3, 4));
    System.out.println(is2.query(2, 4));

  }

  private class SegmentTreeNode {
    int start;
    int end;
    long sum;
    SegmentTreeNode left, right;

    SegmentTreeNode(int s, int e, int sum) {
      this.start = s;
      this.end = e;
      this.sum = sum;
      this.left = null;
      this.right = null;
    }
  }

  private void build(int[] A) {
    if (A == null || A.length == 0) {
      ROOT = null;
      return;
    }
    ROOT = bHelper(A, 0, A.length-1);
  }

  private SegmentTreeNode bHelper(int[] A, int start, int end) {
//    if (start > end) {
//      return null;
//    }
    if (start == end) {
      SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
      return node;
    }

    int mid = start + (end - start) / 2;
    SegmentTreeNode root = new SegmentTreeNode(start, end, -1); // don't know sum yet.
    SegmentTreeNode lnode = bHelper(A, start, mid);
    SegmentTreeNode rnode = bHelper(A, mid+1, end);
    root.left= lnode;
    root.right = rnode;
    root.sum = root.left.sum + root.right.sum;

    return root;
  }

  /**
   * @param start, end: Indices
   * @return: The sum from start to end
   */
  public long query(int start, int end) {
    // write your code here
    return qHelper(ROOT, start, end);
  }

  private long qHelper(SegmentTreeNode root, int start, int end) {
    if (start == root.start && end == root.end) {
      return root.sum;
    }

    int mid = root.start + (root.end - root.start) / 2;
    long sumL = 0, sumR = 0;
    if (start <= mid) {
      if (end <= mid) {
        sumL = qHelper(root.left, start, end);
      }
      else {
        sumL = qHelper(root.left, start, mid);
      }
    }
//    else { //Fxxk! I always wrong in using else here!!!!!!!!!!!!!
    if (end > mid) {
      if (start > mid) {
        sumR = qHelper(root.right, start, end);
      }
      else {
        sumR = qHelper(root.right, mid + 1, end);
      }
    }

    return sumL + sumR;
  }
  /**
   * @param index, value: modify A[index] to value.
   */
  public void modify(int index, int value) {
    // write your code here
    mHelper(ROOT, index, value);
  }

  private void mHelper(SegmentTreeNode root, int index, int value) {

    if (root.start == index && root.end == index) {
      root.sum = value;
      return;  // always FORGOT!!!!!!!!!!!!!
//      return root.sum;
    }

    int mid = root.start + (root.end - root.start) / 2;
//    long lsum = 0, rsum = 0;
    if (index <= mid) {
      mHelper(root.left, index, value);
    }
    else {
      mHelper(root.right, index, value);
    }

    root.sum = root.left.sum + root.right.sum;
//    return root.sum;
  }
}
