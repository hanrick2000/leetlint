package freq3_tony;

import java.util.ArrayList;

/**
 * 其实题目并没有很详细的说出各个边界条件, 要自己分析好. 
 * 注意N00t的思维. 什么时候是merge sort那样对半parse, 什么时候parse前1~3个, parse后面所有.
 * 
 * 这是典型的DP问题. 因为有opt substructure. 1~3: 0~255.
 * http://n00tc0d3r.blogspot.com/2013/05/restore-ip-addresses.html
 * @author tzhang
 *
 */
public class RestoreIP {
  // helper method to detect input substring is a valid field
  private boolean isValidField(String s) {
    if (s.length() == 1 || (!s.isEmpty() && s.length() < 4 && !s.startsWith("0"))) {
      int num = Integer.parseInt(s);
      if (num >= 0 && num <= 255)  return true;
    }
    return false;
  }
  
  /**
   * 在for loop里面的recursion, 就是分别从parse 1,2,3个prefix之后来遍历剩下的.
   * recursion后面的for()来results.add()是可以当作up the tree. 即解树到了leaf之后向上走的过程.
   * @param s : 当前的string. 因为并不需要改变. 所以可以直接用substring()这个不会产生new copy的method
   * @param fields : 当前的解树的层数. 初始为4. 最后是1. 
   * @return
   */
  private ArrayList<String> restoreIPAddrHelper(String s, int fields) {
    ArrayList<String> results = new ArrayList<>();
    
    // last field
    if (fields == 1) {
      if (isValidField(s))  results.add(s);
      return results;
    }
    
    // 1-3 digits for a field, 这才叫parsing, 这才叫recursion. 看好了.
    for (int i = 1; i < 4 && i <=s.length(); ++i) {  // for里面的代码可以看作是平行/等价的. 共用同一个results
      String num = s.substring(0, i);
      if (!isValidField(num))  return results;
      if (s.substring(i).length() > fields*3)  return results;
      ArrayList<String> substrings = restoreIPAddrHelper(s.substring(i), fields-1);  // 注意这里的substrings就是解树下一层的results list.
      for (String substr : substrings)  results.add(num+"."+substr);  // 注意, 这样就可以保证是按顺序的, 而不会反过来. eg: 255.111.35而不是35.111.255
    }
    // for loop是共用同一个results list. 所以是可以的.
    return results;
  }
  
  public ArrayList<String> restoreIPAddresses(String s) {
    return restoreIPAddrHelper(s, 4);
  }
  
  public RestoreIP(){
    String input = "2552551015";
    ArrayList<String> res = restoreIPAddresses(input);
    for (String re : res)
      System.out.println(re);
  }
  
  public static void main(String[] args) {
    RestoreIP rip = new RestoreIP();
  }
}
