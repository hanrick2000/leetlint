package learnJava;

import java.util.*;

/**
 * ����wordbreak��ʱ��Ҫ�ж�ɶʱ�������Ҫ��result
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
