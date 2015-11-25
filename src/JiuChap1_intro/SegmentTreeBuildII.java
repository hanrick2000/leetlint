package JiuChap1_intro;

import misc.SegmentTreeNode;

/**
 * Created this class in JiuChap0_intro at 9:16 PM, 10/26/2015.
 */
public class SegmentTreeBuildII {

  public static void main(String[] args) {
    int[] test = new int[]{3,2,1,4};
    SegmentTreeBuildII stb2 = new SegmentTreeBuildII();
    SegmentTreeNode root = stb2.build(test);
    SegmentTreeNode.inOdr(root);

  }

  /**
   *@param A: a list of integer
   *@return: The root of Segment Tree
   */
  public SegmentTreeNode build(int[] A) {
    // write your code here
    if (A == null || A.length == 0) {
      return null;
    }

    int start = 0, end = A.length-1;
    return helper(A, start, end);
  }

  public SegmentTreeNode helper(int[] A, int start, int end) {
    if (start > end) {
      return null;
    }
    if (start == end) {
      return new SegmentTreeNode(start, end, A[start]);
    }

    int mid = start + (end-start)/2;
    // top-down recursion division
    SegmentTreeNode root = new SegmentTreeNode(start, end, -1);
    SegmentTreeNode left = helper(A, start, mid);
    SegmentTreeNode right = helper(A, mid +1, end);

    // bottom-up update
    root.left = left;
    root.right = right;
    root.max = Math.max(root.left.max, root.right.max);

    return root;
  }

}
