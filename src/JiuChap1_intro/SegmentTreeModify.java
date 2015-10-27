package JiuChap1_intro;

import misc.SegmentTreeNode;

/**
 * http://www.lintcode.com/en/problem/segment-tree-modify/#
 * Created this class in JiuChap0_intro at 10:40 PM, 10/26/2015.
 */
public class SegmentTreeModify {

  public static void main(String[] args) {
    SegmentTreeModify stm = new SegmentTreeModify();
    int[] data = new int[]{1,4,2,3};
    SegmentTreeNode root = new SegmentTreeBuildII().build(data);
    SegmentTreeNode.inOdr(root);

    stm.modify(root, 1, 5);
    System.out.println();
    SegmentTreeNode.inOdr(root);
  }
  /**
   *@param root, index, value: The root of segment tree and
   *@ change the node's value with [index, index] to the new given value
   *@return: void
   */
  public void modify(SegmentTreeNode root, int index, int value) {
    // write your code here
//    if (root == null) {
//      return;
//    }

    if (index == root.start && index == root.end) {
      root.max = value;
      return;  // I forgot return at first time, so it keeps going down, therefore NullpointerException
    }

    int mid = root.start + (root.end-root.start)/2;
    if (index <= mid) {
//    if (root.start <= index && index <= mid) {  // why this? for safety.
      modify(root.left, index, value);
    }
    else { // index >= mid+1
//    else if (mid < index && index <= root.end){
      modify(root.right, index, value);
    }

    root.max = Math.max(root.left.max, root.right.max);
  }

  // public void modify(SegmentTreeQuery.SegmentTreeNode root, int index, int value)
}
