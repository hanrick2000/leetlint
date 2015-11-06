package Easy;

import misc.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/flatten-binary-tree-to-linked-list/
 * Created by 12:07 AM on 10/21/2015.
 */
public class flattenTreeLL {
    public static void main(String[] args) {
        TreeNode root = helper();
        new flattenTreeLL().flattenDummy(root);
    }

    public void flatten(TreeNode root) {
        // TODO
    }

    public static TreeNode helper() {
        TreeNode yi = new TreeNode(1);
        TreeNode er = new TreeNode(2);
        TreeNode sa = new TreeNode(3);
        TreeNode si = new TreeNode(4);
        TreeNode wu = new TreeNode(5);
        TreeNode li = new TreeNode(6);

        yi.left = er;
        yi.right = wu;
        er.left = sa;
        er.right = si;
        wu.right = li;

        return yi;
    }

    public void flattenDummy(TreeNode root) {
        // write your code here
        if (root == null) {
            return;
        }

        List<Integer> temp = new ArrayList<>();
        pre(root, temp);
        System.out.println(temp);
    }

    private void pre(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        pre(root.left, list);
        pre(root.right, list);
    }
}
