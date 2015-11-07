package freq5_tony;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 和merge Intervals一种类型.
 */
public class InsertIntervals {
/**
 * 这个和上一题目有点类似. 不过可以直接用吗?
 * 可以的.看左耳朵耗子
 * @param intervals
 * @param newInterval
 * @return
 */
  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
    intervals.add(newInterval);
    Collections.sort(intervals, new IntervalComparator());
    
    ArrayList<Interval> result = new ArrayList<Interval>();
    Interval pre = intervals.get(0);
    for (int i = 1; i<intervals.size(); i++){
      Interval cur = intervals.get(i);
      
      if (pre.end >= cur.start){
        Interval merged = new Interval(pre.start, Math.max(pre.end, cur.end));
        pre = merged;
      }
      else {
        result.add(pre);
        pre = cur;
      }
    }
    result.add(pre);
    return result;
  }
  public static void main(String[] args) {
    Interval i1 = new Interval(4, 5);
    Interval i2 = new Interval(0, 2);
    Interval i3 = new Interval(7, 9);
    Interval i4 = new Interval(15, 18);
    
    Interval i5 = new Interval(1,8);
    ArrayList<Interval> res = new ArrayList<Interval>();
    res.add(i1);
    res.add(i2);
    res.add(i3);
    res.add(i4);

    for (Interval i : res)
      System.out.println(i);

    res = new InsertIntervals().insert(res, i5);
    // Collections.sort(res, new IntervalComparator());
    System.out.println("after sorting");
    for (Interval j : res)
      System.out.println(j);

  }

}

/* if public class, then visible in all packets.
class Interval{
  int start ;
  int end;
  
  Interval(){
    start = 0;
    end = 0;
  }
  
  Interval(int s, int e){
    start = s;
    end = e;
  }

  public String toString(){
    return getClass().getName() + "[start: " + start + ", end: " + end+"]";
  }
}

class IntervalComparator implements Comparator<Interval>{
  public int compare(Interval lhs, Interval rhs){
    return (lhs.start)-(rhs.start);
  }
}*/
