package misc;

/**
 * http://www.lintcode.com/en/problem/segment-tree-build/
 * Created this class in misc at 12:40 AM, 10/26/2015.
 */
public class SegmentTreeNode {
  public int start, end;
  public SegmentTreeNode left, right;

  public SegmentTreeNode(int start, int end) {
    this.start = start;
    this.end = end;
    this.left = this.right = null;
  }

  /*********************************************************************************
   *                                      util
   ********************************************************************************/

}
