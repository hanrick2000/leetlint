/**
 * 
 */
package freq5_tony;

import java.util.*;

// learn from lafore's chap 8: inorder code.

/**
 *  common class: TreeNode
 */
class TreeNode{
  int val;
  TreeNode left;
  TreeNode right;
  
  TreeNode(int x){
    val = x;
  }
}

public class ValidateBST {

  
  /**
   * Solution 1: inorder-like CMU-ebiz's answer,
   * easy to understand.
   */
  static int lastVisit = Integer.MIN_VALUE;
  public boolean isValidBST(TreeNode root){
    if (root==null)  return true;  // why true here?
    if (!isValidBST(root.left))  return false;
    if (!(root.val <= lastVisit))  return false;
    lastVisit = root.val;
    if (!isValidBST(root.right))  return false;
    return true;
  }
  
  /**
   * Solution 2: recursion by N00tc0d3r.
   * similar to CMU-ebiz, but simply combine left and root.val<=lastVisit
   */
  public boolean isValidBSTHelper(TreeNode root, TreeNode pre){
    if (root == null)  return true;
    if (!isValidBSTHelper(root.left, pre) || !(pre.val <= root.val))
      return false;
    pre.val = root.val;
    if (!isValidBSTHelper(root.right, pre))
      return false;
    return true;
  }
  
  public boolean isValidBST2(TreeNode root){
    return isValidBSTHelper(root, new TreeNode(Integer.MIN_VALUE));
  }
  
  /**
   * Solution 3: stack version by N00tc0d3r
   * 
   */
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
