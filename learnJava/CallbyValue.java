package learnJava;

import java.util.*;

public class CallbyValue {
  private static int hello;
  private int world;
  // private static class pair {
  private class pair {
    int a, b;

    pair(int aa, int bb) {
      a = aa;
      b = bb;
    }

    // @Override
    public String toString() {
      String s = "(" + a + ", " + b + ")";
      return s;
    }
  }

  public CallbyValue() {
    List<pair> elem = new ArrayList<pair>();
    pair yi = new pair(3, 4);
    pair er = new pair(1, 2);
    // System.out.println(yi);
    elem.add(yi);
    elem.add(er);
    for (pair p : elem)
      System.out.println(p);
    yi.a = 23;
    yi.b = -88;
    for (pair p : elem)
      System.out.println(p);
    
    CallbyValue.hello = 3;
    this.world = 4;
  }

  private static void dummy() {
    System.out.println("Hello" + hello);
  }

  public static void main(String[] args) {
//    dummy();
    CallbyValue cv = new CallbyValue();
  }
}
