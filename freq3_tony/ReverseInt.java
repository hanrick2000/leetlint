package freq3_tony;

/**
 * Reverse digits of an integer. 
 * Example1: x = 123, return 321 
 * Example2: x = -123, return -321
 * 
 * @author tzhang
 *
 */
public class ReverseInt {
  // 解法1: 直接转成string, 注意负号, 然后反过来print
  public static void reversePrint(int a) {
    String original, reverse = "";
    boolean neg = false;
    if (a < 0) {
      neg = true;
      a = Math.abs(a);
    }
    original = Integer.toString(a);
    for (int i = original.length()-1; i >= 0; i--){
      reverse += original.charAt(i);
    }
    reverse = neg? "-" + reverse : reverse;
    System.out.println(reverse);
  }
  
  // 解法2: clean code
  // http://blog.csdn.net/linhuanmars/article/details/20024837
  public static int revInt(int x) {
    if(x==Integer.MIN_VALUE)
      return 0;
    int ret = 0;
    while (x!= 0) {
      if (Math.abs(ret) > Integer.MAX_VALUE/10) {
        System.out.println("Too big!");
        return 0;
      }
      ret = ret * 10 + x %10;
      x /= 10;
    }
    System.out.println("ans: " + ret);
    return ret;
  }
  
  public static void main(String[] args){
    int a = 123;
    reversePrint(a);
//    a = -123;
    a = 1000000009;
//    reversePrint(a);
    revInt(a);
    
  }
}
