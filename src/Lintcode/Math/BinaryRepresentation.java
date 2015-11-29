package Lintcode.Math;

/**
 * It's also a CTCI problem: 5.2
 * http://www.hawstein.com/posts/5.2.html
 * http://www.lintcode.com/en/problem/binary-representation/
 * Created at 5:28 PM on 11/25/15.
 */
public class BinaryRepresentation {
  public static void main(String[] args) {
    String str = "13.125";
    String ans = new BinaryRepresentation().binaryRepresentation(str);
    System.out.println(ans);
  }
  /**
   *@param n: Given a decimal number that is passed in as a string
   *@return: A string
   */
  public String binaryRepresentation(String n) {
    // write your code here
    StringBuilder sb = new StringBuilder(n);
    int dot = sb.indexOf(".");
    if (dot == -1) {
      return parseInteger(n);
    }
    String intpart = sb.substring(0, dot);
    String frapart = sb.substring(dot, sb.length());
    String intS = parseInteger(intpart);
    String fraS = parseFraction(frapart);
    if (fraS.equals("ERROR"))  return "ERROR";
    else if (fraS.equals("0"))  return intS;
    return intS + "." + fraS;
  }

  public String parseInteger(String str) {
    int n = Integer.parseInt(str);
    if (n == 0)  return "0";
    StringBuilder sb = new StringBuilder();
    while (n != 0) {
      sb.insert(0, Integer.toString(n % 2));
      n /= 2;
    }
    return sb.toString();
  }

  public String parseFraction(String str) {
    double d = Double.parseDouble(str);
    if (d == 0)  return "0";
    StringBuilder sb = new StringBuilder();
    while (d > 0) {
      if (sb.length() > 32) {
        return "ERROR";
      }
      d *= 2;
      if (d >= 1) {
        sb.append("1");
        d -= 1;
      }
      else {
        sb.append("0");
      }
    }
    return sb.toString();
  }
}
