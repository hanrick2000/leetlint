package JiuChap1_intro;

/**
 * Created this class in JiuChap0_intro at 12:40 AM, 10/26/2015.
 */
public class SegmentTreeBuild {
  public static void main(String[] args0) {
    int start = 1, end = 4;
    SegmentTreeBuild stb = new SegmentTreeBuild();
    SegmentTreeNode root = stb.build(start, end);
    stb.inOdr(root);
  }

  /**
   * @param start, end: Denote an segment / interval
   * @return: The root of Segment Tree
   */
  public SegmentTreeNode build(int start, int end) {
    // write your code here
    SegmentTreeNode root = helper(start, end);
    return root;
  }

  public SegmentTreeNode helper(int start, int end) {
    if (start > end) {
      return null;
    }
    if (start == end) {
      return new SegmentTreeNode(start, end);
    }

    int mid = start + (end - start) / 2;
//    SegmentTreeNode left = new SegmentTreeNode(start, mid);
//    SegmentTreeNode right = new SegmentTreeNode(mid+1, end);
//    root.left = left;
//    root.right = right;

    SegmentTreeNode root = new SegmentTreeNode(start, end);
    SegmentTreeNode leftRoot = helper(start, mid);
    SegmentTreeNode rightRoot = helper(mid+1, end);
    root.left = leftRoot;
    root.right = rightRoot;

    return root;
  }

  private void inOdr(SegmentTreeNode root) {
    if (root == null) {
      return;
    }

    inOdr(root.left);
    System.out.println(root.start + " " + root.end);
    inOdr(root.right);
  }

  /**
   * Definition of SegmentTreeNode for Build I.
   */
  private class SegmentTreeNode {
    public int start, end;
    public SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end) {
      this.start = start;
      this.end = end;
      this.left = this.right = null;
    }
  }
}
