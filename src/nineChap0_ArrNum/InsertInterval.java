package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import misc.Interval;

/**
 * http://www.lintcode.com/en/problem/insert-interval/
 * Created at 12:37 AM on 11/20/15.
 */
public class InsertInterval {
  /**
   * http://www.jiuzhang.com/solutions/insert-interval/
   * http://www.programcreek.com/2012/12/leetcode-insert-interval/
   * Insert newInterval into intervals.
   * @param intervals: Sorted interval list.
   * @param newInterval: A new interval.
   * @return: A new sorted interval list.
   */
  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
    ArrayList<Interval> result = new ArrayList<Interval>();
    // write your code here
    if (newInterval == null || intervals == null) {
      return result;
    }
    //Collections.sort(intervals, new Comparator<Interval>() {
    //  @Override public int compare(Interval o1, Interval o2) {
    //    return o1.start - o2.start;
    //  }
    //});

    int lastPos = 0;
    for (Interval interval : intervals) {
      if (interval.end < newInterval.start) {
        result.add(interval);
        lastPos++;
      }
      else if (interval.start > newInterval.end) {
        result.add(interval);
      }
      else {
        newInterval.start = Math.min(interval.start, newInterval.start);
        newInterval.end = Math.max(interval.end, newInterval.end);
      }
    }

    result.add(lastPos, newInterval);
    return result;
  }
}
