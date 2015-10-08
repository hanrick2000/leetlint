package freq3_tony;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 类比pre/in/post, 想到了用FIFO, 但是一下从右, 一下从左怎么实现? 
 * ANS: 先定义什么事一下从右, 一下从左: 分别是odd level跟even level. 这就简单了, 
 * 加一个if/else 判断level的odd/even即可.
 * @author tzhang
 *
 */
public class BinaryTreeZigzag {
  private int N;
  private TreeNode root;
  
  private class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;
    public TreeNode(int va) {
      this.value = va;
      N++;
    }
  }
  
  public BinaryTreeZigzag(){
    this.root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);
    root.left.right.left = new TreeNode(7);
    root.right.left.right = new TreeNode(8);
    System.out.print(zigzagLevelOrder(root));
  }
  
  /**
   * 方法: 就是用一个flag标志是odd/even level. 然后当前层和下一层分别用2个stack表示. 
   *        因为是沿着路线走, 所以是LIFO, 所以用pushin stack是LIFO.
   * @param root
   * @return
   */
  private List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    int evenodd = 0;
    List<List<Integer>> resSets = new ArrayList<List<Integer>>();
    if (root == null)  return resSets;
    Stack<TreeNode> currLevel = new Stack<>();
    currLevel.push(root);
    TreeNode curr = root;
    // 注意这里有意思的2个while loop的条件是一样的, 为什么呢?
    //  因为out-loop是创建res list跟temp stack
    while (!currLevel.isEmpty()) {
      List<Integer> res = new ArrayList<Integer>();
      Stack<TreeNode> childLevel = new Stack<>();
      // 这里的in-loop是负责填充out-loop创建的res list和childLevel的
      while (!currLevel.isEmpty()) {
        curr = currLevel.pop();
        res.add(curr.value);
        if (evenodd % 2 == 1) {
          // if odd order, first pushin left
          if (curr.left != null)  childLevel.push(curr.left);
          if (curr.right != null)  childLevel.push(curr.right);
        }
        else if (evenodd % 2 == 0) {
          // if even order, first pushin right
          if (curr.right != null)  childLevel.push(curr.right);
          if (curr.left != null)  childLevel.push(curr.left);
        }
      }
      // switch order
      evenodd++;
      // add curr res into resSets
      resSets.add(res);
      // update to the next level
      currLevel = childLevel;
    }
    return resSets;
  }
  
  public static void main(String[] args) {
    BinaryTreeZigzag btzz = new BinaryTreeZigzag();
  }
}
