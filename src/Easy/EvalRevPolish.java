package Easy;

import java.util.Stack;

/**
 * Created at 6:15 PM on 11/17/15.
 */
public class EvalRevPolish {
  public static void main(String[] args) {
    EvalRevPolish erp = new EvalRevPolish();
    String[] tokens = {"2"};
    int ans =erp.evalRPN(tokens);
    System.out.println(ans);
  }


  public int evalRPN(String[] tokens) {
    // Write your code here
    if (tokens == null || tokens.length == 0) {
      return -1;
    }

    int ans = 0;
    Stack<Integer> evalStk = new Stack<>();
    for (String s : tokens) {
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
    return evalStk.peek();
  }

  private static int s2i(String s) {
    if (s.matches("-?\\d+")) {
      return Integer.parseInt(s);
    }
    else {
      return Integer.MAX_VALUE;
    }
  }
}
