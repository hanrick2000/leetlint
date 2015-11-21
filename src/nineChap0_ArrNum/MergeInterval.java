package nineChap0_ArrNum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import misc.Interval;

/**
 * http://www.lintcode.com/en/problem/merge-intervals/
 * Created at 12:23 AM on 11/20/15.
 */
public class MergeInterval {
  /**
   * @param intervals: Sorted interval list.
   * @return: A new sorted interval list.
   */
  public List<Interval> merge(List<Interval> intervals) {
    // write your code here
    if (intervals == null || intervals.size() == 0) {
      return intervals;
    }
    Collections.sort(intervals, (o1, o2) -> o1.start - o2.start);
    //Collections.sort(intervals, new IntervalComparator());
    List<Interval> result = new ArrayList<>();
    Interval last = intervals.get(0);
    for (int i = 1; i < intervals.size(); ++i) {
      Interval cur = intervals.get(i);
      if (cur.start <= last.end) {
        last.end = Math.max(last.end, cur.end);  // cool
      }
      else {
        result.add(last);
        last = cur;
      }
    }
    result.add(last);
    return result;
  }

  class IntervalComparator implements Comparator<Interval> {

    @Override public int compare(Interval a, Interval b) {
      return a.start - b.start;
    }
  }
}
