package misc;

/**
 * Definition of Interval, used in segmentTreeNode
 * Created this class in misc at 11:58 PM, 10/26/2015.
 */
public class Interval {
  public int start, end;  // if defined in misc package and used in another package, need to make
                            // method/field to be public so as to be package level accessible.

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}
