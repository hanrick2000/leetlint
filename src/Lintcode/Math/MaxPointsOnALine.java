package Lintcode.Math;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.lintcode.com/en/problem/max-points-on-a-line/ Created at 11:00 AM on 11/28/15.
 */
public class MaxPointsOnALine {
  public static void main(String[] args) {
    new MaxPointsOnALine().test();
  }

  public void test() {
    Point p1 = new Point(1,2);
    Point p2 = new Point(3,6);
    Point p3 = new Point(0,0);
    Point p3p = new Point(9,0);
    Point p4 = new Point(1,3);
    Point[] pArr = new Point[]{p1,p2,p3,p3p, p4};
    int ans = maxPoints(pArr);
    System.out.println(ans);
  }
  /**
   * @param points an array of point
   * @return an integer
   */
  public int maxPoints(Point[] points) {
    // Write your code here
    if (points == null || points.length == 0) {
      return 0;
    }

    int globalMax = 1;
    double ratio = 0.0;
    Map<Double, Integer> map = new HashMap<>();
    for (int i = 0; i < points.length; ++i) {
      int localmax = 1;
      int numofSam = 0;
      for (int j = i+1; j < points.length; ++j) {
        if (points[j].x == points[i].x && points[j].y == points[i].y) {
          numofSam++;
          continue;
        }
        else if (points[j].x == points[i].x) {
          ratio = (double)Integer.MAX_VALUE;
        }
        else if (points[j].y == points[i].y) {
          ratio = 0.0;
        }
        else {  // Must do casting, otherwise wrong in some cases
          ratio = (double)(points[j].y-points[i].y) / (double)(points[j].x - points[i].x);
        }
        if (map.containsKey(ratio)) {
          map.put(ratio, map.get(ratio) + 1);
        }
        else {
          map.put(ratio, 2);
        }
      }
      for (Integer cnt : map.values()) {
        localmax = Math.max(localmax, cnt);
      }
      localmax += numofSam;
      globalMax = Math.max(localmax, globalMax);
      map.clear();
    }
    return globalMax;
  }

  class Point {
    int x;
    int y;

    Point() {
      x = 0;
      y = 0;
    }

    Point(int a, int b) {
      x = a;
      y = b;
    }
  }
}
