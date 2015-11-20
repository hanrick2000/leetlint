package nineChap3_BST;

/**
 * http://www.lintcode.com/en/problem/lowest-common-ancestor-ii/ Created at 6:07 PM on 11/19/15.
 */
public class LCA2 {
  public static void main(String[] args) {
    LCA2 lca2 = new LCA2();
    lca2.test();
  }

  public void test() {
    ParentTreeNode sa = new ParentTreeNode(3);
    ParentTreeNode si = new ParentTreeNode(4);
    ParentTreeNode wu = new ParentTreeNode(5);
    ParentTreeNode li = new ParentTreeNode(6);
    ParentTreeNode qi = new ParentTreeNode(7);

    si.left = sa;
    si.right = qi;
    qi.left = wu;
    qi.right = li;

    sa.parent = si;
    qi.parent = si;
    wu.parent = qi;
    li.parent = qi;

    ParentTreeNode res = lowestCommonAncestorII(si, sa, wu);
    System.out.println(res.val);
  }

  /**
   * http://articles.leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-ii.html
   * @param root: The root of the tree
   * @param A, B: Two node in the tree
   * @return: The lowest common ancestor of A and B
   */
  public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
      ParentTreeNode A,
      ParentTreeNode B) {
    // Write your code here
    int ah = findHeight(A);
    int bh = findHeight(B);
    if (ah > bh) {
      int diff = ah - bh;
      while (diff != 0) {
        A = A.parent;
        diff--;
      }
    } else if (ah < bh) {
      int diff = bh - ah;
      while (diff != 0) {
        B = B.parent;
        diff--;
      }
    }

    while (A != B) {
      A = A.parent;
      B = B.parent;
    }
    return A;
  }

  private int findHeight(ParentTreeNode root) {
    int height = 0;
    while (root != null) {
      root = root.parent;
      height++;
    }
    return height;
  }

  class ParentTreeNode {
    public ParentTreeNode parent, left, right;
    public int val;
    ParentTreeNode(int v) {
      val = v;
    }
  }
}
