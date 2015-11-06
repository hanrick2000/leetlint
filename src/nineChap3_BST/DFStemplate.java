package nineChap3_BST;

import misc.TreeNode;

public class DFStemplate {
  
  /*
   *  DFS template 1: traversal
   *  
   */
  public void dfsTraverse(TreeNode root) {
    if (root == null) {
      return;
    }

    // process(root);  /* pre-oder */
    dfsTraverse(root.left);

    // process(root);  /* in-oder */
    dfsTraverse(root.right);

    // process(root);  /* post-oder */
  }

  /*-
   *  DFS template 2: Divide & Conquer
   *  simple example 1: return sum of Binary tree , also check LCA_BU
   */
  public int dfsDC(TreeNode root) {
    if (root == null) {  // must do this earlier than the next if, otherwise nullpointerexception
      return 0;
    }
    
    if (root.left == null && root.right == null) {
      return root.val;
    }
    
    // divide
    int left = dfsDC(root.left);
    int right = dfsDC(root.right);
    
    // conquer
    System.out.println("Left and right are: " + left + " " + right);
    
    return left+right + root.val;
  }
  
  /*
   * DFS template 2: Divide and Conquer
   * example 2: LCA's buttom-up solution: elegant
   */
  public TreeNode lcaDC(TreeNode root, TreeNode P, TreeNode Q) {
    if (root == null) {
      return root;
    }
    if (root.equals(P) || root.equals(Q)) {
      return root;
    }
    
    TreeNode left = lcaDC(root.left, P, Q);
    TreeNode right = lcaDC(root.right, P, Q);
    
    if (left != null && right != null) {
      return root;
    }
    else {
      return left != null ? left : right;
    }
  }
  
  private static void process(TreeNode root) {
    System.out.println("Processing " + root.val);
  }

  public void testdfsTraverse(TreeNode root) {
    dfsTraverse(root);
  }
  
  public void testdfsDC(TreeNode root) {
    int ans = dfsDC(root);
    System.out.println("DC ans: " + ans);
  }
  
  public void testlcaDC(TreeNode root, TreeNode A, TreeNode B) {
    TreeNode lcaANS = lcaDC(root, A, B);
    System.out.println("The LCA of " + A.val + " and " + B.val + " is: " + lcaANS.val);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode li = new TreeNode(6);
    TreeNode qi = new TreeNode(7);
    TreeNode ba = new TreeNode(8);
    TreeNode ji = new TreeNode(9);
    TreeNode sh = new TreeNode(10);

    root = yi;
    root.left = er;
    root.right = sa;
    er.left = si;
    er.right = wu;
    wu.right = ji;
    sa.right = sh;

    DFStemplate dfst = new DFStemplate();
//    dfst.testTraversal(root);
//    dfst.testdfsDC(root);
    dfst.testlcaDC(root, ji, er);
  }
}
