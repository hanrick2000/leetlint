package learnJava;

import java.util.*;

/**
 * 在做wordbreak的时候要判断啥时候处理掉不要的result
 * @author tzhang
 *
 */
public class EmptyForeach {
  private static void test(){
    List<String> src = new ArrayList<String>();
    for (String s : src) {
      System.out.println("Hello");
    }
  }
  
  public static void main(String[] args) {
    test();
    System.out.println("end");
  }
}
