package learnJava;
/**
 * playground
 * @author tzhang
 *
 */
public class BasicBBB {

  public static int addd(int x) {
    x = x + 10;
    return  x;
  }
  public static void main(String[] args) {
    int pre = 3;
    System.out.println(addd(pre));
    System.out.println(pre);
  }
}
