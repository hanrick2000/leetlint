package JiuChap3_DequeStackTrie;

import java.util.LinkedList;

/**
 * http://www.lintcode.com/en/problem/simplify-path/
 * Created at 3:09 PM on 11/25/15.
 */
public class SimplifyPath {
  public static void main(String[] args) {
    String path = "/a/./b/../../c/home/";
    String res = new SimplifyPath().simplifyPath(path);
    System.out.println(res);
  }
  /**
   * http://blog.csdn.net/linhuanmars/article/details/23972563
   * @param path the original path
   * @return the simplified path
   */
  public String simplifyPath(String path) {
    // Write your code here
    if (path == null || path.length() == 0) {
      return path;
    }
    LinkedList<String> stack = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    int i = 0;

    while (i < path.length()) {
      int index = i;
      StringBuilder tmp = new StringBuilder();
      while (i < path.length() && path.charAt(i) != '/') {
        tmp.append(path.charAt(i));
        i++;
      }
      if (index != i) {
        String content = tmp.toString();
        if (content.equals("..")) {
          if (!stack.isEmpty()) {
            stack.pop();
          }
        }
        else if (!content.equals(".")) {
          stack.push(content);
        }
      }
      i++;
    }

    if (!stack.isEmpty()) {
      String[] strs = stack.toArray(new String[stack.size()]);
      for (int j = strs.length-1; j >= 0; j--) {
        sb.append("/" + strs[j]);
      }
    }
    if (sb.length() == 0) {
      return "/";
    }
    return sb.toString();
  }
}
