package JiuChap3_DequeStackTrie;

import java.util.Stack;
import misc.ExpressionTreeNode;

/**
 * Nice question!
 * Created this class in JiuChap3_DequeStackTrie at 4:00 PM, 11/6/2015.
 */
public class ExpTreeBuild {

  public static void main(String[] args) {
    ExpTreeBuild etp = new ExpTreeBuild();
    String[] exp = new String[]{"2", "*", "6", "-", "(", "23", "+", "7", ")", "/", "(", "1", "+", "2", ")"};
    ExpressionTreeNode root = etp.build(exp);
    System.out.println(root.symbol);
  }

  /**
   * JiuChap solution, the only difference is that it uses equals, not ==!!!
   * @param expression
   * @return
   */
  public ExpressionTreeNode build(String[] expression) {
    // write your code here
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode root = null;
    int val = 0;
    Integer base = 0;
    for (int i = 0; i <= expression.length; i++) {
      if(i != expression.length)
      {

        if (expression[i].equals("(")) {
          base += 10;
          continue;
        }
        if (expression[i].equals(")")) {
          base -= 10;
          continue;
        }
        val = get(expression[i], base);

      }
      TreeNode right = i == expression.length ? new TreeNode(
          Integer.MIN_VALUE, "") : new TreeNode(val,
          expression[i]);
      while (!stack.isEmpty()) {
        if (right.val <= stack.peek().val) {
          TreeNode nodeNow = stack.pop();

          if (stack.isEmpty()) {
            right.root.left = nodeNow.root;

          } else {
            TreeNode left = stack.peek();
            if (left.val < right.val) {
              right.root.left = nodeNow.root;
            } else {
              left.root.right = nodeNow.root;
            }
          }
        } else {
          break;
        }
      }
      stack.push(right);
    }



    return stack.peek().root.left;
  }

  public ExpressionTreeNode buildT(String[] expression) {
    int base = 0;
    int val = 0;
    Stack<TreeNode> decStk = new Stack<>();
    for (int i = 0; i <= expression.length; ++i) {
      if (i != expression.length) { // Nice job: set val on the fly!
        if (expression[i] == "(") {
          base += 10;
          continue;
        }
        else if (expression[i] == ")") {
          base -= 10;
          continue;
        }
        val = get(expression[i], base);
      }
      TreeNode right = i == expression.length ? new TreeNode(Integer.MIN_VALUE, "-oo") : new TreeNode(val, expression[i]);

      while (!decStk.isEmpty()) {
        if (decStk.peek().val < right.val) {
          break;
        }
        else {
          TreeNode now = decStk.pop();
          if (decStk.isEmpty()) {
            right.root.left = now.root;
          }
          else {
            TreeNode left = decStk.peek();
            if (left.val < right.val) {
              right.root.left = now.root;
            }
            else {
              left.root.right = now.root;
            }
          }
        }
        //decStk.push(right);  // dman, the push only happened after I finished processing elements in the stack!
      }
      decStk.push(right);
    }
    return decStk.peek().root.left;
  }


  /**
   * Need an extra field to save each unit's priority value (based on base) in the stack.
   * @param expression: A string array
   * @return: The root of expression tree
   */
  public ExpressionTreeNode buildWRONG(String[] expression) {
    // write your code here
    int base = 0;
    int val = 0;
    Stack<ExpressionTreeNode> decStk = new Stack<>();
    for (int i = 0; i <= expression.length; ++i) {
      if (i != expression.length) {
        if (expression[i] == "(") {
          base += 10;
          continue;
        }
        else if (expression[i] == ")") {
          base -= 10;
          continue;
        }
        val = get(expression[i], base);
      }
      else {
        val = Integer.MIN_VALUE;
      }

      ExpressionTreeNode right = i == expression.length ? new ExpressionTreeNode("@") : new ExpressionTreeNode(expression[i]);
      while (!decStk.isEmpty()) {
        if (get(decStk.peek().symbol, base) < val ) {
          break;
        }
        else {
          ExpressionTreeNode now = decStk.pop();
          if (decStk.isEmpty()) {
            right.left = now;
          }
          else {
            ExpressionTreeNode left = decStk.peek();
            if (get(left.symbol, base) < val ) { // if (get(left.symbol, base) < val )
              right.left = now;
            }
            else {
              left.right = now;
            }
          }
        }
      }
      decStk.push(right);
    }

    return decStk.peek().left;
  }

  /**
   * A helper function to get the priority of each non-"(, )" element.
   *
   * @param symbol
   * @param base
   * @return
   */
  private static int get(String symbol, int base) {
    int val = 0;
    if (symbol == "+" || symbol == "-") {
      val = 1 + base;
    }
    else if (symbol == "*" || symbol == "/") {
      val = 2 + base;
    }
    else {
      val = Integer.MAX_VALUE;
    }
    return val;
  }

  /**
   * this private node to composite ExpressionTreeNode with its priority value. It's the same idea of another array of priority.
   */
  private class TreeNode {
    int val;
    ExpressionTreeNode root;

    TreeNode(int v, String ss) {
      this.val = v;
      this.root = new ExpressionTreeNode(ss);
    }
  }

}
