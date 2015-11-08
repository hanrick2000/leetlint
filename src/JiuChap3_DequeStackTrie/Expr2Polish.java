package JiuChap3_DequeStackTrie;

import java.util.ArrayList;
import java.util.Stack;
import misc.ExpressionTreeNode;

/**
 * http://www.lintcode.com/en/problem/convert-expression-to-polish-notation/
 * Created this class in JiuChap3_DequeStackTrie at 2:07 PM, 11/7/2015.
 */
public class Expr2Polish {
  public static void main(String[] args) {
    Expr2Polish etp = new Expr2Polish();
    String[] exp = new String[]{"(", "5", "-", "6", ")", "*", "7"}; // {"2", "*", "6", "-", "(", "23", "+", "7", ")", "/", "(", "1", "+", "2", ")"};
    ArrayList<String> ans = etp.convertToPN(exp);
    System.out.println(ans);
  }
  /**
   * @param expression: A string array
   * @return: The Polish notation of this expression
   */
  public ArrayList<String> convertToPN(String[] expression) {
    // write your code here
    int val = 0;
    int base = 0;
    Stack<TreeNode> stk = new Stack<>();
    for (int i = 0; i <= expression.length; ++i) {
      if (i != expression.length) {
        if (expression[i].equals("(")) {
          base += 10;
          continue;
        }
        else if (expression[i].equals(")")) {
          base -= 10;
          continue;
        }
        val = getPriority(expression[i], base);
      }

      TreeNode right = i == expression.length ? new TreeNode(Integer.MIN_VALUE, "") : new TreeNode(val, expression[i]);
      while (!stk.isEmpty()) {
        if (stk.peek().val < right.val) {
          break;
        }
        else {
          TreeNode now = stk.pop();
          if (stk.isEmpty()) {
            right.root.left = now.root;
          }
          else {
            TreeNode left = stk.peek();
            if (left.val < right.val) {
              right.root.left = now.root;
            }
            else {
              left.root.right = now.root;
            }
          }
        }
      }
      stk.push(right);
    }

    ExpressionTreeNode root = stk.peek().root.left;

    ArrayList<String> result = new ArrayList<>();
    dfs(root, result);
    return result;
  }

  private static void dfs(ExpressionTreeNode root, ArrayList<String> result) {
    if (root == null) {
      return;
    }
    result.add(root.symbol);
    dfs(root.left, result);
    dfs(root.right, result);
  }

  private static int getPriority(String ss, int base) {
    if (ss.equals("+") || ss.equals("-")) {
      return 1 + base;
    }
    else if (ss.equals("*") || ss.equals("/")) {
      return 2 + base;
    }
    return Integer.MAX_VALUE;
  }

  private class TreeNode {
    int val;
    ExpressionTreeNode root;

    TreeNode(int value, String ss) {
      this.val = value;
      this.root = new ExpressionTreeNode(ss);
    }
  }
}
