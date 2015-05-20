package learnJava;

import java.util.HashMap;
import java.util.Map;

public class Hashstudy {
  static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public String toString() {
      String res = this.x + " " + this.y;
      return res;
    }
  }
  static class Node {
    int value;
    String endp;

    Node(int v, String s) {
      this.value = v;
      this.endp = s;
    }
  }

  public static void main(String[] args) {
    // HashMap<Integer, String> children = new HashMap<>();
    // children.put(3, "helo");
    // children.put(5, "hellllo");
    // System.out.println(children.keySet());

    Map<Point, Boolean> mat = new HashMap<Point, Boolean>();
    Point eryi = new Point(2,1);
    Point yili = new Point(1,0);
    mat.put(eryi, true);
    mat.put(yili, true);
    if (mat.containsKey(eryi)) {
      System.out.println(1);
    }
    System.out.println(2);
  }
}
