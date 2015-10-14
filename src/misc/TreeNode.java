package misc;

public class TreeNode {
  public int value;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(int v) {
    value = v;
  }

  public TreeNode() {} // 注意public和不写(default)不同!

  public boolean isLeaf() {
    return this.left == null && this.right == null;
  }

  public String toString() {
    int s = value;
    return Integer.toString(s);
  }

  public boolean equals(Object object) {
    if (object instanceof TreeNode && ((TreeNode) object).value == this.value) {
      return true;
    } else {
      return false;
    }
  }
}
