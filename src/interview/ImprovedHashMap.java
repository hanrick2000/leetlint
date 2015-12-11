package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.careercup.com/question?id=5689672300756992
 * Created at 10:28 AM on 11/30/15.
 */
public class ImprovedHashMap {
  Map<Integer, TreeNode> map = new HashMap<>();

  public int get(int key, float t) {
    TreeNode root = map.get(key);
    int i = searchBST(root, t, root.value, root.t);
    return i;
  }

  public int searchBST(TreeNode root, float t, int closestMatch, float closestT) {
    if (root == null) {
      return closestMatch;
    }
    if (root.t > t) {
      return searchBST(root.left, t, closestMatch, t);
    } else if (root.t < t) {
      if (closestT > root.t) {
        closestMatch = root.value;
        closestT = root.t;
      }
      return searchBST(root.right, t, closestMatch, closestT);
    } else {
      return root.value;
    }
  }

  public void put(int key, float t, int value) {
    // TreeNode node = new TreeNode(value, key);
    TreeNode n = new TreeNode(value, t);
    TreeNode root = map.get(key);
    if (root == null) {
      map.put(key, n);
    } else {
      insert(root, n);
    }
  }

  public static void insert(TreeNode root, TreeNode child) {
    if ((root.t > child.t)) {
      if (root.left == null) {
        root.left = child;
      } else {
        insert(root.left, child);
      }
    } else if (root.t < child.t) {
      if (root.right == null) {
        root.right = child;
      } else {
        insert(root.right, child);
      }
    }
  }

  private static class TreeNode {
    float t;
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int i, float t) {
      this.value = i;
      this.t = t;
    }
  }
}