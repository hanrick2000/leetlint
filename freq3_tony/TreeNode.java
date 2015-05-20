package freq3_tony;

public class TreeNode {
//  private TreeNode root;
  private int N;
  int value;
  TreeNode left;
  TreeNode right;

  public TreeNode(int va) {
    value = va;
    N++;
  }
  // dummy ctor
  public TreeNode(){
    ;
  }
  
  public int getvalue() {
    return value;
  }
  
  public TreeNode getleft() {
    return left;
  }
  
  public TreeNode getright() {
    return right;
  }
  
  public void setleft(TreeNode le) {
    left = le;
  }
  
  public void setright(TreeNode ri) {
    right =ri;
  }
  
  public void setvalue(int va) {
    value = va;
  }
  
  public void printTree(TreeNode head) {
    TreeNode node = head;
    if  (node.getleft()!= null) printTree(node.getleft()); 
    System.out.print(node.value + " ");
    if (node.right!= null) printTree(node.right);
  }
//  public TreeNode right(TreeNode node) {
//    if (node != null && node.right != null)  return node.right;
//    else throw new IllegalArgumentException("Why right?");
//  }
  
//  public TreeNode getRoot() {
//    if (root != null)  return root;
//    else throw new IllegalArgumentException("What root?");
//  }
  
  // unit test
  public static void main(String[] args) {
    
    TreeNode head = new TreeNode(7);
    TreeNode l1 = new TreeNode(5);
    TreeNode r1 = new TreeNode(18);
    TreeNode ll1 = new TreeNode(3);
    TreeNode rl1 = new TreeNode(6);
    TreeNode lr1 = new TreeNode(12);
    TreeNode rr1 = new TreeNode(19);
    head.setleft(l1);
    head.right = r1;
    l1.setleft(ll1);
    l1.right = rl1;
    r1.setleft(lr1);
    r1.right = rr1;
    head.printTree(head);
  }

}
