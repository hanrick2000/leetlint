package freq1_tony;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 这里关键的是Stack的使用. 以及常见的处理分段的string
 * 
 * @author tzhang
 *
 */
public class SimplifyPath {
  public static void main(String[] args) {
    String path = "/../"; // "/a/./b/../../c/";
    System.out.println(simPath(path));
  }

  /**
   * http://n00tc0d3r.blogspot.com/2013/06/simplify-path.html
   * 
   * @param path
   * @return
   */
  public static String simPath(String path) {
    if (path == null || path.charAt(0) != '/')
      return "404";
    String[] p = path.split("/");
    Deque<String> stack = new ArrayDeque<String>();
    for (String s : p) {
      if (s.equals("..") && !stack.isEmpty())
        stack.pop();
      else if (!s.equals(".") && !s.equals("..") && !s.equals("")) // 记得要判断是否是leading empty string
        stack.push(s);
    }

    if (stack.isEmpty())
      return "/";
    StringBuilder str = new StringBuilder();
    while (!stack.isEmpty()) {
      // str.append(stack.pop());
      str.insert(0, "/" + stack.pop()); // 原来stack LIFO可以配合insert(0)来调整顺序!
    }
    return str.toString();
  }
}
