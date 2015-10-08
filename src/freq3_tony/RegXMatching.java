package freq3_tony;

public class RegXMatching {
  /**
   * N00t的第一个解法: recursion大法. 主要是分析各个情况.
   * 
   * @param s
   * @param p
   * @return
   */
  public static boolean isMatchI(String s, String p) {
    if (s.isEmpty() && !p.isEmpty() || !s.isEmpty() && p.isEmpty())
      return false;
    if (s.isEmpty() && (p.isEmpty() || p.length() == 2 && p.charAt(1) == '*'))
      return true;

    // 如果当前match的话(包含'.'的这种match
    if (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0)) {
      if (p.length() >= 2 && p.charAt(1) == '*') {
        return isMatchI(s, p.substring(2)) // none match
            || isMatchI(s.substring(1), p.substring(2)) // once
            || isMatchI(s.substring(1), p); // more
      } else
        return isMatchI(s.substring(1), p.substring(1));
    }
    // 如果现在这个char不match的话.
    else if (p.length() >= 2 && p.charAt(1) == '*') {
      return isMatchI(s, p.substring(2));
    }

    return false;
  }

  /**
   * 因为有时候像aab vs a*b. 会recur的用a*. 所以这里可以通过一直loop到没有a*的match, 这样避免recursion.
   * 
   * @param s
   * @param p
   * @return
   */
  public static boolean isMatchBackTrack(String s, String p) {
    if (s == null)
      return (p == null);
    if (p.isEmpty())
      return (s.isEmpty());
    // next char isn't *
    if (p.length() == 1 || p.charAt(1) != '*') {
      if (s.isEmpty())
        return false;
      return (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))
          && isMatchBackTrack(s.substring(1), p.substring(1));
    }
    // next char is *
    int i = 0;
    while (i < s.length() && (p.charAt(0) == '.' || s.charAt(i) == p.charAt(0))) {
      if (isMatchBackTrack(s.substring(i), p.substring(2)))  return true;
      i++;
    }
    // jump out of while loop
    return isMatchBackTrack(s.substring(i), p.substring(2));
  }

  public static void main(String[] args) {
    String s = "b", p = "a*a*a*b";
    System.out.println("Got: " + isMatchBackTrack(s, p));
  }
}
