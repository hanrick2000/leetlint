package misc;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int v) {
    val = v;
  }

  public TreeNode() {} // 注意public和不写(default)不同!

  public boolean isLeaf() {
    return this.left == null && this.right == null;
  }

  public String toString() {
    int s = val;
    return Integer.toString(s);
  }

  public boolean equals(Object object) {
    if (object instanceof TreeNode && ((TreeNode) object).val == this.val) {
      return true;
    } else {
      return false;
    }
  }
}
