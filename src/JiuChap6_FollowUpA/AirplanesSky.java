package JiuChap6_FollowUpA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * http://www.lintcode.com/en/problem/number-of-airplanes-in-the-sky/
 * Created at 5:52 PM on 11/16/15.
 */
public class AirplanesSky {
  public static void main(String[] args) {
    AirplanesSky as = new AirplanesSky();
    as.test();
  }

  public void test() {
    Interval t1 = new Interval(1,10);
    Interval t2 = new Interval(2,3);
    Interval t3 = new Interval(5,8);
    Interval t4 = new Interval(4,7);
    List<Interval> list = new ArrayList<>();
    list.add(t1);
    list.add(t2);
    list.add(t3);
    list.add(t4);

    int ans = countOfAirplanes(list);
    System.out.println(ans);
  }
  /**
   * @param airplanes: An interval array
   * @return: Count of airplanes are in the sky.
   */
  public int countOfAirplanes(List<Interval> airplanes) {
    // write your code here
    if (airplanes == null || airplanes.size() == 0) {
      return 0;
    }
    List<Edge> timestamps = new ArrayList<>();
    for (Interval airplane : airplanes) {
      timestamps.add(new Edge(airplane.start, 1));
      timestamps.add(new Edge(airplane.end, 0));
    }
    Collections.sort(timestamps);
    int local = 0, global = 0;
    for (Edge timestamp : timestamps) {
      if (timestamp.flag == 1) {
        local++;
      }
      else {
        local--;
      }
      global = Math.max(local, global);
    }
    return global;
  }

  class Interval {
    int start, end;
    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }

  class Edge implements Comparable<Edge> {
    int time;
    int flag; // start is larger than end, for the purpose of sorting
    Edge(int t, int f) {
      this.time = t;
      this.flag = f;
    }

    @Override public int compareTo(Edge other) {
      if (this.time == other.time) {
        return this.flag - other.flag;
      }
      else {
        //return this.flag - other.flag;
        return this.time - other.time;
      }
    }
  }
}
