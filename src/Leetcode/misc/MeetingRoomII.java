package Leetcode.misc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import misc.Interval;

/**
 * Created at 5:30 PM on 11/30/15.
 */
public class MeetingRoomII {

  /**
   * https://aquahillcf.wordpress.com/2015/08/30/leetcode-meeting-rooms-ii/
   *
   * @param intervals
   * @return
   */
  public int minMeetingRooms(Interval[] intervals) {
    Arrays.sort(intervals, new Comparator<Interval>() {
      @Override
      public int compare(Interval i1, Interval i2) {
        int cmp = i1.start - i2.start;
        return cmp == 0 ? i1.end - i2.end : cmp;
      }
    });

    PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    minPQ.add(intervals[0].end);

    for (int i = 1; i < intervals.length; ++i) {
      int minEnd = minPQ.peek();
      if (intervals[i].start > minEnd) {
        minPQ.poll();  // still need to figure why doing this.
      }
      minPQ.add(intervals[i].end);
    }
    return minPQ.size();
  }
}
