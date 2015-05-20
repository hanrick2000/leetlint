package freq1_tony;

/**
 * 简单题主要就是速度和edge case
 * @author tzhang
 *
 */
public class LenLastWord {
  public static void main(String[] args) {
    String s1 = "hello      world  ";
    int ans = lenLastWord(s1);
    System.out.println(ans);
  }
  
  /**
   * N00t
   * @param s
   * @return
   */
  private static int lenLastWord(String s) {
    if (s==null || s.length() ==0)
      return 0;
    int len = 0, last = s.length()-1;
    while (last >= 0 && s.charAt(last) == ' ')  last--;
    while (last >= 0 && s.charAt(last--) != ' ')  len++;
    return len;
  }
}
