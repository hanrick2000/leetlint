package freq5_tony;

import java.util.*;

/**
 * Google常见leetcode题目, 参考了Ganker的switch, 压栈做法.
 * @author tzhang
 *
 */
public class ValidParentheses {
  private boolean isValid(String s) {
    if (s == null || s.length()==0) 
      return true;
    LinkedList<Character> stack = new LinkedList<>();
    for (int i = 0; i < s.length(); ++i) {
      switch(s.charAt(i)) {
        case '(':
        case '{':
        case '[':
          stack.push(s.charAt(i));
          break;
        case ')':
          if (stack.isEmpty() || stack.pop()!='(')
            return false;
          break;
        case '}':
          if (stack.isEmpty() || stack.pop()!='{')
            return false;
          break;
        case ']': 
          if (stack.isEmpty() || stack.pop()!='[')
            return false;
          break;
        default:
          break;
      }
    }
    if (stack.isEmpty())
      return true;
    return false;
  }
}
