package Easy;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * Created by 12:51 AM on 10/13/2015.
 */
public class MaxDepthTree {
    public static void main(String[] args0) {
        test();
    }

    public static void test() {
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

//        root = yi;
//        root.left = er;
//        root.right = sa;
//        er.left = si;
//        er.right = wu;
//        wu.right = ji;
//        ji.right = sh;
        yi.left = si;
        yi.right = er;
        er.left = sa;

        BTtreePrinter.printNode(yi);

        System.out.println("max Depth: " + maxDepth(yi));
    }

    /**
     * http://blog.csdn.net/linhuanmars/article/details/19660209
     * As mentioned by Ganker, minDepth need to care about null node since it should be ignored, but max depth always go
     * for the larger value, so it's simpler!
     *
     * @param node
     * @return
     */
    private static int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return getMax(node);
    }

    private static int getMax(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getMax(root.left), getMax(root.right)) + 1;
    }

    private static int helper(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = helper(root.left);
        int right = helper(root.right);
        return Math.max(left, right) + 1;
    }
}
