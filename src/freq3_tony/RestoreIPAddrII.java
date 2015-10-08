package freq3_tony;

import java.util.ArrayList;

/**
 * 在n00t的第一个recursion方法中, 是每次都new一个results的arrayList. 这样是没有必要的. 所以n00t的第二个recursion就重复使用同一个results
 * list. 减少了时空的浪费.
 * 
 * @author tzhang
 *
 */
public class RestoreIPAddrII {
  // helper method to detect input substring is a valid field
  private boolean isValidIPField(String s) {
    if (s.length() == 1
        || (!s.isEmpty() && s.length() < 4 && !s.startsWith("0"))) {
      int num = Integer.parseInt(s);
      if (num >= 0 && num <= 255)
        return true;
    }
    return false;
  }

  private void getAllIps(String s, int n, StringBuilder path, ArrayList<String> res) {
    if (n == 1) {
      if (isValidIPField(s))
        res.add(path.toString() + s + "\n");
      return;
    }

    for (int i = 1, len = path.length(); i <= 3 && i < s.length(); ++i) {
      String field = s.substring(0, i);
      if (isValidIPField(field)) {
        getAllIps(s.substring(i), n - 1, path.append(field).append('.'), res);
      }
      // Must do path.delete to upper tree. 因为for loop里面的recurion也是要执行完才++i的. 如果不复原path. 那么第2,3,...个for循环子的path就是错的.
      path.delete(len, path.length());
    }
  }

  public ArrayList<String> restoreIpAddresses(String s) {
    ArrayList<String> res = new ArrayList<String>();
    getAllIps(s, 4, new StringBuilder(), res);
    return res;
  }
  
  public RestoreIPAddrII(){
    String input = "2552551015";
    ArrayList<String> results = restoreIpAddresses(input);
    for (String re : results)
      System.out.println(re);
  }
  
  public static void main(String[] args) {
    RestoreIPAddrII rip2 = new RestoreIPAddrII();
  }
}
