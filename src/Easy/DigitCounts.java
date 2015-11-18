package Easy;

/**
 * Nice problem to learn from pattern, and go for the correct direction.
 * http://www.lintcode.com/en/problem/digit-counts Created at 2:10 PM on 11/17/15.
 */
public class DigitCounts {
  public static void main(String[] args) {
    DigitCounts dc = new DigitCounts();
    int n = 19, k = 0;
    int ans = dc.digitCounts(k, n);
    //ans = dc.Countis(n, k);
    System.out.println(ans);
  }

  /**
   * http://www.meetqun.com/thread-3359-1-1.html
   * O(10n), so O(n)
   * param k : As description.
   * param n : As description. return: An integer denote the count of
   * digit k in 1..n
   */
  public int digitCountsAvatar(int k, int n) {
    // write your code here
    int result = 0;
    for (int i = 0; i < n; ++i) { // for Integer.MAX_VALUE, it's 10 weishu.
      int tmp = i;
      while (tmp != 0) {
        if (tmp % 10 == k) {
          result++;
        }
        tmp = tmp / 10;
      }
    }
    return result;
  }

  /**
   * http://www.hawstein.com/posts/20.4.html
   * Count how many digits equals 2.
   * @param n
   * @return
   */
  public int digitCounts(int k, int n) {
    int count = 0;
    int factor = 1; // wei shu : order
    int low = 0, cur = 0, high = 0;

    while (n / factor != 0) {
      low = n - (n/factor) * factor;
      //cur = n % 10;
      cur = (n/factor) % 10;
      high = n / (factor * 10);

      if (cur == k) {
        count += high * factor + low + 1;
      }
      else if (cur > k) {
        count += (high + 1) * factor;
      }
      else {
        count += high * factor;
      }

      factor *= 10;
    }

    if (k == 0) {
      count = count > 10 ? count - 10 : count;
    }
    return count;
  }

  private int order(int k) {
    int ord = 0;
    while (k != 0) {
      k = k / 10;
      ord++;
    }
    return ord;
  }
}
