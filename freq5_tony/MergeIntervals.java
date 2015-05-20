package freq5_tony;

import java.util.*;

/**
 * 先读懂题意。 1. 学会comparator要new 一个class来定义. 2. 在class中要定义toString来print.
 * 用到了reflectoin的getClass和getName. 3. 最重要的是理解学会2个pointers来循环一个arrray并保存结果. pre = curr; pre = merged.
 * 
 * @author tzhang
 * 
 */
public class MergeIntervals {
  /**
   * input: an ArrayList with unsorted intervals
   * z
   * @param intervals
   * @return
   */
  public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    Collections.sort(intervals, new IntervalComparator());
    ArrayList<Interval> result = new ArrayList<>();

    Interval pre = intervals.get(0); // 在loop之前建立一个pre。
    // for loop的目的是看情况,把后一个interval跟前一个interval合并.
    // 也就是每次update pre.
    for (int i = 1; i < intervals.size(); i++) {
      Interval cur = intervals.get(i);

      if (pre.end >= cur.start) {
        Interval merged = new Interval(pre.start, Math.max(pre.end, cur.end));
        // result.add(merged);
        // 如果pre包括了cur,就把pre放大, 然后到下一个cur.
        // 还不可以保存起来,因为有可能下一个cur也可以merge.
        pre = merged;
      } else {
        // 否则就保存pre, 以现在的cur为pre, 下一个为cur.
        // 可以看作上一次的merge成功了,可以保存起来.
        result.add(pre);
        pre = cur;
      }
    }
    // Loop结束以后讲update的pre收进来.
    result.add(pre);
    return result;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Interval i1 = new Interval(1, 4);
    Interval i2 = new Interval(0, 2);
    Interval i3 = new Interval(3, 5);
    Interval i4 = new Interval(15, 18);
    Interval i5 = new Interval(115, 118);
    ArrayList<Interval> res = new ArrayList<Interval>();
    res.add(i1);
    res.add(i2);
    res.add(i3);
    res.add(i4);
    res.add(i5);

    for (Interval i : res)
      System.out.println(i);

    res = new MergeIntervals().merge(res);
    // Collections.sort(res, new IntervalComparator());
    System.out.println("after sorting");
    for (Interval j : res)
      System.out.println(j);


    int order = 0;
    List<Interval> result = new ArrayList<>();
    int totalInt = res.size();
    System.out.println(totalInt);
    for (Interval j : res) {
      Interval tmp = new Interval();
      if (order == totalInt/2*2) {
        break;
      }
      if (order % 2 == 0) {
        System.out.print(j.end + 1 + " ");
        tmp.start = j.end+1;
      } else {
        System.out.println(j.start -1);
        tmp.end = j.start-1;
      }
      order++;
      result.add(tmp);
    }
    
//    System.out.println(result);
  }

}


class Interval {
  int start;
  int end;

  Interval() {
    start = 0;
    end = 0;
  }

  Interval(int s, int e) {
    start = s;
    end = e;
  }

  public int getS() {
    return start;
  }

  public int getE() {
    return end;
  }

  public String toString() {
    return getClass().getName() + "[start: " + getS() + ", end: " + getE()
        + "]";
  }
}


class IntervalComparator implements Comparator<Interval> {
  public int compare(Interval lhs, Interval rhs) {
    return lhs.start - rhs.start;
  }
}
