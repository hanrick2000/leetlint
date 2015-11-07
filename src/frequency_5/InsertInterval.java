package frequency_5;

import java.util.ArrayList;
import java.util.Arrays;

// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if
// necessary).
//
// You may assume that the intervals were initially sorted according to their start times.
//
// Example 1:
// Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
//
// Example 2:
// Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
//
// This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10]
//
// ��ʵ������ �������Ҫmerge ��ֱ�Ӳ��룬�����Ҫ�ںϾ��ںϵ��ף� ����һ���е����ơ�
public class InsertInterval {

  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
    intervals.add(newInterval);
    Interval[] i = (Interval[]) intervals.toArray(new Interval[intervals.size()]);
    Arrays.sort(i, new IntervalComparator());

    for (int j = 1; j < i.length; j++) {
      // ����ҵ���߼�
      // ע�⣬��Ϊ���ܴ��� [[1,4],[2,3]] ���� ���� endҪȡ���ڴ���Ǹ�
      if (i[j].start <= i[j - 1].end) {
        Interval updated =
            new Interval(i[j - 1].start, i[j].end > i[j - 1].end ? i[j].end : i[j - 1].end);
        Interval zero = null;
        i[j - 1] = zero;
        i[j] = updated;
      }
    }
    ArrayList<Interval> result = new ArrayList<Interval>();
    for (int k = 0; k < i.length; k++) {
      if (!(i[k] == null)) {
        result.add(i[k]);
      }
    }
    return result;


  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
