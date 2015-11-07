/**
 * 
 */
package freq5_tony;

/**
 * @author tzhang
 *
 */
public class ValidatePalindrome {

  /**
   * I think this is indeed using 1 var to represent 2 pointers.
   * @param input
   * @return
   */
  public static boolean validatePalindrome(String s){
    if (s==null || s.length() == 0)  return false;
    s = s.replaceAll("[^a-zA-z0-9]", "").toLowerCase();
    for (int i = 0; i<s.length(); i++){
      if (s.charAt(i) != s.charAt(s.length()-i-1))
        return false;
    }
    return true;
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    String str = "A man, a Plan, a CANal: PanaMA";
    System.out.println(validatePalindrome(str));
  }

}
