package Easy;

import misc.BTtreePrinter;
import misc.TreeNode;

/**
 * Created by 11:42 PM on 10/12/2015.
 */
public class MinDepthTree {
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
        yi.right = er;
        er.left = sa;

        BTtreePrinter.printNode(yi);

        System.out.println("min Depth: " + minDepth(yi));
    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
//        return helper(root);
        return getMin(root);
    }

    /**
     * Be careful, only return 1 if leaf node, null node is not leaf node!
     * I was failed in this case: it should return 3!
     *
     *    1
     *     \
     *      \
     *      2
     *     /
     *    3
     *
     * @param root
     * @return
     */
    private static int helperWrong(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helperWrong(root.left);
        int right = helperWrong(root.right);
        return Math.min(left, right) + 1;
    }

    public static int getMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
}
