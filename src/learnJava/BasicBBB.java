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

  /**
   * http://stackoverflow.com/questions/16567622/what-is-the-result-of-an-assignment-expression-in-c
   * The result of assignment expression is the value of assigned variable!!! not true or false!
   * @param x
   */
  public static void resultOfAssignStemnt(int x) {
    System.out.println(x = x+1);
  }

  public static void main(String[] args) {
    int pre = 3;
//    System.out.println(addd(pre));
//    System.out.println(pre);
    resultOfAssignStemnt(pre);
  }
}
