/**
 * 
 */
package freq5_tony;

/**
 * @author tzhang
 * 
 */
public class StrStr {

  /**
   *    solution 1: naive solution
   *    
   */
  public String strStr(String Haystack, String Needle) {
    int h = Haystack.length(), n = Needle.length();
    for (int i = 0; i< h-n+1; i++){
      int nptr = 0;
      for (int hptr = i; nptr < n && Haystack.charAt(hptr) == Needle.charAt(nptr); hptr++, nptr++){}
      if (nptr == n)  //return new String(Haystack.toCharArray(), i, h-i);
        return Haystack.substring(i);
    }  
    return null;  
  }
  
  /**
   * solutionSmilence mentioned KR algorithm, which is based on HASH
   * And it requires calculations.
   * @param args
   */
  
  /**
   * Solution3: KMP algo
   * @param args
   */
  
  public static void main(String[] args){
    System.out.println(new StrStr().strStr("abcdefg", "cde"));
  }
}
