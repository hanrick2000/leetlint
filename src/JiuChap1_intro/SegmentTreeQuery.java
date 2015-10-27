package JiuChap1_intro;

import misc.SegmentTreeNode;

/**
 * http://www.lintcode.com/en/problem/segment-tree-query/
 * Created this class in JiuChap0_intro at 9:56 AM, 10/26/2015.
 */
public class SegmentTreeQuery {
  public static void main(String[] args0) {
    new SegmentTreeQuery().test();
  }

  public void test() {
    SegmentTreeQuery stq = new SegmentTreeQuery();
//    misc.SegmentTreeNode root = new SegmentTreeBuild().build(0, 6);
//    System.out.println(stq.query(root, 2,3));

    // [1, 4, 2, 3].
    SegmentTreeNode root = new SegmentTreeNode(0, 3, 4);
    SegmentTreeNode rootl = new SegmentTreeNode(0, 1, 4);
    SegmentTreeNode rootr = new SegmentTreeNode(2, 3, 3);
    SegmentTreeNode rootll = new SegmentTreeNode(0, 0, 1);
    SegmentTreeNode rootlr = new SegmentTreeNode(1, 1, 4);
    SegmentTreeNode rootrl = new SegmentTreeNode(2, 2, 2);
    SegmentTreeNode rootrr = new SegmentTreeNode(3, 3, 3);
    root.left = rootl;
    root.right = rootr;
    rootl.left = rootll;
    rootl.right = rootlr;
    rootr.left = rootrl;
    rootr.right = rootrr;

    int ans03 = stq.query(root, 0, 3);
    int ans13 = stq.query(root, 2, 3);
    int ans12 = stq.query(root, 3, 3);
    int ans11 = stq.query(root, 0, 0);

    System.out.println(ans03);
    System.out.println(ans13);
    System.out.println(ans12);
    System.out.println(ans11);
  }

  /**
   * @param root, start, end: The root of segment tree and
   *              an segment / interval
   * @return: The maximum number in the interval [start, end]
   */
  public int query(SegmentTreeNode root, int start, int end) {
    if (root == null) {
      return -1;
    }
    if (root.start == start && root.end == end) {
      return root.max;
    }

    int mid = root.start + (root.end - root.start)/ 2;
    int maxL = Integer.MIN_VALUE, maxR = Integer.MIN_VALUE;
    if (start <= mid) {
      if (end <= mid) {
        maxL = query(root.left, start, end);
      }
      else {
        maxL = query(root.left, start, mid);
      }
    }
    if (end > mid) {
      if (start >= mid+1) {
        maxR = query(root.right, start, end);
      }
      else {
        maxR = query(root.right, mid+1, end);
      }
    }

    return Math.max(maxL, maxR);
  }
  public int queryMy(SegmentTreeNode root, int start, int end) {
    // write your code here
    if (start == root.start && end == root.end) {
      return root.max;
    }
//    int mid = start + (end - start) / 2;
    int maxL = Integer.MIN_VALUE, maxR = Integer.MIN_VALUE;

    if (end <= root.left.end) {
      maxL = queryMy(root.left, start, end);
//      maxR = query(root.right, start, end);
      return maxL;
    }
    if (start >= root.right.start) {
      maxR = queryMy(root.right, start, end);
      return maxR;
    }
    if (start <= root.left.end) {
      maxL = queryMy(root.left, start, root.left.end);
    }
    if (end >= root.right.start) {
      maxR = queryMy(root.right, root.right.start, end);
    }
    return Math.max(maxL, maxR);
  }



}
