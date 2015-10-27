package misc;

/**
 * http://www.lintcode.com/en/problem/segment-tree-build/
 * Created this class in misc at 12:40 AM, 10/26/2015.
 */
public class SegmentTreeNode {
  public int start, end, max;
  public SegmentTreeNode left, right;

  public SegmentTreeNode(int start, int end, int max) {
    this.start = start;
    this.end = end;
    this.max = max;
    this.left = this.right = null;
  }

  /*********************************************************************************
   *                                      util
   ********************************************************************************/

  public static void inOdr(SegmentTreeNode root) {
    if (root == null) {
      return;
    }

    inOdr(root.left);
    System.out.println(root.start + " " + root.end + ": " + root.max);
    inOdr(root.right);
  }
}
