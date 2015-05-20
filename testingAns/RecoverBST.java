package testingAns;

public class RecoverBST {
  private class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) {
      this.val = v;
    }
  }
  
  private void swap(TreeNode a, TreeNode b) {  
//    if (n1 == null || n2 == null)  return;
    int tmp = a.val;  
    a.val = b.val;  
    b.val = tmp;  
  }  
  
  public void recoverTree(TreeNode root) {  
    TreeNode[] nodes = new TreeNode[2];  
    inorder(root, nodes, null);  
    swap(nodes[0], nodes[1]);
  }  
    
  // in-order traversal and return the last visited node in the traversal  
  private TreeNode inorder(TreeNode root, TreeNode[] nodes, TreeNode pre) {  
    if (root == null) return pre;  
    
    // left subtree  
    TreeNode last = inorder(root.left, nodes, pre);  
    // visit  
    if (last != null && root.val < last.val) {  
      nodes[1] = root;  
      if (nodes[0] == null) { // found first node  
        nodes[0] = last;  
      } else {  
        return root;  
      }  
    }  
    // right subtree  
    return inorder(root.right, nodes, root);  
  } 
  
  public RecoverBST() {
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    TreeNode wu = new TreeNode(5);
    TreeNode li = new TreeNode(6);
    TreeNode qi = new TreeNode(7);
    si.left = li;
    si.right = er;
    li.left = yi;
    li.right = sa;
    er.left = wu;
    er.right = qi;
    TreeNode[] nodes = new TreeNode[2];  
    TreeNode res = inorder(si, nodes, null);
    System.out.println(nodes[0].val + " " + nodes[1].val);
    swap(nodes[0], nodes[1]);
  }
  
  public static void main(String[] args) {
    RecoverBST rbst = new RecoverBST();
  }
}
