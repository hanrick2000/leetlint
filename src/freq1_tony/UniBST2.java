package freq1_tony;

/**
 * unique BST的follow up: 如果要返回所有unique BST而不是unique的个数的话呢? 而且注意到Leetcode中的tag是: tree,
 * DynamicProgramming!
 * 
 */
import java.util.ArrayList;
import java.util.List;
import misc.TreeNode;

public class UniBST2 {

  public static void main(String[] args) {
    int n = 3;
    List<TreeNode> ans = generateTrees(n);
    for (TreeNode tn : ans) {
      List<String> tree = new ArrayList<String>();
      SerDesBinaryTree.writeBinTree(tn, tree); // 注意: 每个root就是保存了以他为首的整颗树的信息. 所以可以serialize来保存.
      System.out.println(tree.toString());
    }
  }

  /**
   * 简单的接口: 直接调用一次dfs就行了, 即用来设置范围.
   * 
   * @param n
   * @return
   */
  public static List<TreeNode> uniqueBSTrec(int n) {
    return helper(1, n);
  }

  /**
   * dfs遍历所有在left, right]范围内的BST的解. 是NP递归循环遍历的思想, 但并不是直接套用permutation/subset的模版. 以[1,3]为例.
   * 最终结果的list就是[1,1,2,3,3]. 每一个都对应一个BST的root.
   * 
   * @param left
   * @param right
   * @return : 保存了所有unique BST的root的list
   */
  private static List<TreeNode> helper(int left, int right) {
    List<TreeNode> res = new ArrayList<TreeNode>();
    if (left > right) {
      // return null; // 注意不要return null. 这里应该是空树而不是null.
      res.add(null);
      return res;
    }
    for (int i = left; i <= right; i++) {
      // "Divide": 分别recursion得到所有以i为root的左右子树的root的合集的left/right list.
      List<TreeNode> leftList = helper(left, i - 1);
      List<TreeNode> rightList = helper(i + 1, right);
      // "Conquer": recursion之后就相当于往上走, 而且以i为root的时候 所有的可能性是i连上左子树的所有可能 × i连上右子树的所有可能
      for (int j = 0; j < leftList.size(); j++) {
        for (int k = 0; k < rightList.size(); k++) {
          TreeNode root = new TreeNode(i);
          root.left = leftList.get(j);
          root.right = rightList.get(k);
          res.add(root);
        }
      }
    }
    return res;
  }

  /**
   * N00t的DP解法牺牲空间, 来避免recursion解法里面重复的计算同一个区间的BST. 首先设计好表, 然后是递推式. 然后简化逻辑.
   * 
   * @param n
   * @return
   */
  public static List<TreeNode> uniqueBSTdp(int n) {
    if (n < 1) {
      List<TreeNode> res = new ArrayList<TreeNode>();
      res.add(null);
      return res;
    }

    // T[i][l] : DP表记录所有从i开始, 长度为l的range: [i, i+l]的unique BST的root. 所以可以直接拿root来得到BST.
    // 且这里使用list<list<Node>>
    // List<List<TreeNode>>
    return null;
  }



  // -----------------------------------------------------------------------
  public static ArrayList<TreeNode> generateTrees(int n) {
    if (n < 1) {
      ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
      trees.add(null);
      return trees;
    }

    // T[i,l] contains a list of BSTs of [i .. i+l]. 这不是2D DP表吗? 为什么是3D的list? ans: 因为前2个list分别表示i和l.
    // 最内层一个list表示T[i,l]对应的list of TreeNode. 这个要熟练. 其实画一下图就知道了.
    ArrayList<ArrayList<ArrayList<TreeNode>>> allNumTrees =
        new ArrayList<ArrayList<ArrayList<TreeNode>>>(n);

    // init with single node trees
    for (int i = 1; i <= n; ++i) {
      ArrayList<ArrayList<TreeNode>> numTrees =
          new ArrayList<ArrayList<TreeNode>>(n - i); // 在T[n,0]的时候呢? ans: 实际上这个是init capacity提升效率而已.
                                                     // 不是size. 因为只有在[n,0]的时候是调整了一下这个init为0空间的list.
                                                     // 其实没有影响
      ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
      TreeNode root = new TreeNode(i);
      trees.add(root);
      numTrees.add(trees);
      allNumTrees.add(numTrees);
    }

    // fill up the table
    for (int l = 1; l < n; ++l) { // levels
      for (int i = 0; i < n - l; ++i) { // starting number
        ArrayList<ArrayList<TreeNode>> numTrees = allNumTrees.get(i); // 因为T[i][l]. 所以内层循环来get(i).
        ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
        for (int k = i; k <= i + l; ++k) { // root value
          if (k == i) { // case 1: root是左边界. 所以接上所有T[k+1][l-1]的root为右子树
            for (TreeNode right : allNumTrees.get(k + 1).get(l - 1)) {
              TreeNode root = new TreeNode(k + 1);
              root.right = right;
              trees.add(root);
            }
          } else if (k == i + l) { // case 2: root是右边界, 所以接上所有T[i][l-1]的root为左子树
            for (TreeNode left : allNumTrees.get(i).get(l - 1)) {
              TreeNode root = new TreeNode(k + 1);
              root.left = left;
              trees.add(root);
            }
          } else { // case 3: root不是边界. 就加上所有左右子树的组合情况
            for (TreeNode left : allNumTrees.get(i).get(k - i - 1)) { // 这个get的参数画一下就知道了
              for (TreeNode right : allNumTrees.get(k + 1).get(i + l - k - 1)) {
                TreeNode root = new TreeNode(k + 1);
                root.left = left;
                root.right = right;
                trees.add(root);
              }
            }
          }
        }
        numTrees.add(trees); // 因为是按照长度来循环. 所以numTrees在内层循环结束之后update.
      }
    }

    return allNumTrees.get(0).get(n - 1);
  }

}
