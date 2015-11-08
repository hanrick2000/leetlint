package JiuChap3_DequeStackTrie;

import java.util.ArrayList;
import java.util.Stack;
import misc.ExpressionTreeNode;

/**
 * Created this class in JiuChap3_DequeStackTrie at 2:43 PM, 11/7/2015.
 */
public class ExprEval {
  public static void main(String[] args) {
    ExprEval ee = new ExprEval();
    //String[] test = new String[]{"2a", "-38", "abc", "190"};
    //for (String s : test) {
    //  System.out.println(s2i(s));
    //}
    String[] exp = new String[]{"(", "5", "-", "6", ")", "*", "7"}; // {"(","(",")",")"}; // {"1", "+", "99"}; //{"101"}; //
    System.out.println(ee.evaluateExpression(exp));
  }
  /**
   * @param expression: an array of strings;
   * @return: an integer
   */
  public int evaluateExpression(String[] expression) {
    // write your code here
    int val = 0;
    int base = 0;
    int ans = 0;
    if (expression == null || expression.length == 0) {
      return ans;
    }
    Stack<TreeNode> stk = new Stack<>();
    for (int i = 0; i <= expression.length; ++i) {
      if (i != expression.length) {
        if (expression[i].equals("(")) {
          base += 10;
          continue;
        } else if (expression[i].equals(")")) {
          base -= 10;
          continue;
        }
        val = getPriority(expression[i], base);
      }

      TreeNode right = i == expression.length ? new TreeNode(Integer.MIN_VALUE, "")
          : new TreeNode(val, expression[i]);
      while (!stk.isEmpty()) {
        if (stk.peek().val < right.val) {
          break;
        } else {
          TreeNode now = stk.pop();
          if (stk.isEmpty()) {
            right.root.left = now.root;
          } else {
            TreeNode left = stk.peek();
            if (left.val < right.val) {
              right.root.left = now.root;
            } else {
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

    Stack<Integer> evalStk = new Stack<>();
    for (String s : result) {
      int[] vals = new int[2];
      if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")) {
        vals[1] = evalStk.pop();
        vals[0] = evalStk.pop();
        switch (s) {
          case "+" : ans = vals[0] + vals[1];
            break;
          case "-" : ans = vals[0] - vals[1];
            break;
          case "*" : ans = vals[0] * vals[1];
            break;
          case "/" : ans = vals[0] / vals[1];
            break;
          default:
            break;
        }
        evalStk.push(ans);
      }
      else {
        evalStk.push(s2i(s));
      }
    }

    /*  Notice the corner cases */
    if (evalStk.size() == 0) {
      return ans;
    }
    return evalStk.pop();
  }

  private static int s2i(String s) {
    if (s.matches("-?\\d+")) {
      return Integer.parseInt(s);
    }
    else {
      return Integer.MAX_VALUE;
    }
  }

  private static void dfs(ExpressionTreeNode root, ArrayList<String> result) {
    if (root == null) {
      return;
    }
    dfs(root.left, result);
    dfs(root.right, result);
    result.add(root.symbol);
  }

  private static int getPriority(String ss, int base) {
    if (ss.equals("+") || ss.equals("-")) {
      return 1 + base;
    } else if (ss.equals("*") || ss.equals("/")) {
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
