package misc;

/**
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * 
 * @author tzhang
 *
 */
public class ValidPalindrome {
  /**
   * @param s A string
   * @return Whether the string is a valid palindrome
   */
  public static boolean isPalindromeHenng(String s) {
    // Write your code here
    if (s == null || s.length() == 0) {
      return true;
    }

    int lhs = 0, rhs = s.length() - 1;
    s = s.toLowerCase();
    
    boolean ans = true;
    while (lhs <= rhs) {
      if (lhs <= rhs) {
//        if (checkAlph(s.charAt(lhs)) == false ||
//            checkDigit(s.charAt(lhs)) == false) {
//          lhs++;
//        }
//        if (checkAlph(s.charAt(rhs)) == false ||
//          checkDigit(s.charAt(rhs)) == false) {
//          rhs--;
//        }
        if (isvalid(s.charAt(lhs)) == false) {
          lhs++;
        }
        if (isvalid(s.charAt(rhs)) == false) {
          rhs--;
        }
      }
      
      if (lhs > rhs) {
//        ans = true;
        break;
      }
      
      if (s.charAt(lhs) != s.charAt(rhs)) {
        ans = false;
        break;
      }
      lhs++;
      rhs--;
    }

    return ans;
  }
  
  /**
   * ???
   * @param s
   * @return
   */
  public static boolean isPalindromeJiuZhang(String s) {
    if (s == null || s.length() == 0) {
        return true;
    }

    int front = 0;
    int end = s.length() - 1;
    while (front < end) {
        while (front < s.length() && !isvalid(s.charAt(front))){ // nead to check range of a/b
            front++;
        }

        if (front == s.length()) { // for emtpy string ¡°.,,,¡±     
            return true; 
        }           

        while (end >= 0 && ! isvalid(s.charAt(end))) { // same here, need to check border of a,b
            end--;
        }

        if (Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end))) {
            break;
        } else {
            front++;
            end--;
        }
    }

    return end <= front; 
}
  
  private static boolean checkAlph(char ch) {
    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
      return true;
    }
    return false;
  }
  
  private static boolean checkDigit(char ch) {
    if (ch >= '0' && ch <= '9') {
      return true;
    }
    return false;
  }
  
  private static boolean isvalid(char ch) {
    return Character.isLetter(ch) || Character.isDigit(ch);
  }

  public static void main(String[] args) {
    System.out.println(isPalindromeJiuZhang("aA"));
  }
}
