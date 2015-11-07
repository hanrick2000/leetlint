package JiuChap3_DequeStackTrie;

import java.util.Stack;
import misc.ExpressionTreeNode;

/**
 * Created this class in JiuChap3_DequeStackTrie at 1:38 PM, 11/7/2015.
 */
public class ExpTreeBuildLint {
  public static void main(String[] args) {
    ExpTreeBuildLint etp = new ExpTreeBuildLint();
    String[] exp =
        new String[] { "2", "*", "6", "-", "(", "23", "+", "7", ")", "/", "(", "1", "+", "2", ")" };
    ExpressionTreeNode root = etp.build(exp);
    System.out.println(root.symbol);
  }

  /**
   * @param expression: A string array
   * @return: The root of expression tree
   */
  public ExpressionTreeNode build(String[] expression) {
    // write your code here
    int val = 0;
    int base = 0;
    Stack<Node> stk = new Stack<>();

    for (int i = 0; i <= expression.length; ++i) {
      if (i != expression.length) {
        if (expression[i] == "(") {
          base += 10;
          continue;
        } else if (expression[i] == ")") {
          base -= 10;
          continue;
        }
        val = getPriority(expression[i], base);
      }

      Node right =
          i == expression.length ? new Node(Integer.MIN_VALUE, "") : new Node(val, expression[i]);

      while (!stk.isEmpty()) {
        if (stk.peek().val < right.val) {
          break;
        } else {
          Node now = stk.pop();
          if (stk.isEmpty()) {
            right.root.left = now.root;
          } else {
            Node left = stk.peek();
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
    return stk.peek().root.left;
  }

  private static int getPriority(String s, int base) {
    if (s == "+" || s == "-") {
      return 1 + base;
    } else if (s == "*" || s == "/") {
      return 2 + base;
    } else {
      return Integer.MAX_VALUE;
    }
  }

  private class Node {
    int val;
    ExpressionTreeNode root;

    Node(int value, String ss) {
      this.val = value;
      this.root = new ExpressionTreeNode(ss);
    }
  }
}
