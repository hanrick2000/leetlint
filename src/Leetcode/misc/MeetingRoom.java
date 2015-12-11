package Leetcode.misc;

import java.util.Arrays;
import java.util.Comparator;
import misc.Interval;

/**
 * Created at 5:04 PM on 11/30/15.
 */
public class MeetingRoom {
  public static void main(String[] args) {
    new MeetingRoom().test();
  }

  public void test() {
    Interval[] intvervals = new Interval[] {
        new Interval(0, 4),
        new Interval(5, 10),
        new Interval(15, 20)
    };
    boolean ans = canAttendMeetings(intvervals);
    System.out.println(ans);
  }

  /**
   * http://sbzhouhao.net/LeetCode/LeetCode-Meeting-Rooms.html
   * @param intervals
   * @return
   */
  public boolean canAttendMeetings(Interval[] intervals) {
    // Now I understand the comparator.
    Arrays.sort(intervals, new Comparator<Interval>() {
      @Override public int compare(Interval o1, Interval o2) {
        int cmp = o1.start - o2.start;
        return cmp == 0 ? o1.end - o2.end : cmp;
      }
    });

    for (int i = 1; i < intervals.length; ++i) {
      Interval i1 = intervals[i - 1];
      Interval i2 = intervals[i];
      if (i1.end > i2.start) {
        return false;
      }
    }

    return true;
  }
}
