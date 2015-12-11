package JiuChap5_DynamicProgram;

/**
 * http://www.lintcode.com/en/problem/candy/
 * Created at 3:46 PM on 11/29/15.
 */
public class Candy {
  public static void main(String[] args) {
    int[] ratings = new int[]{4,5,4,2,1,2};
    int ans = new Candy().candy(ratings);
    System.out.println(ans);
  }
  /**
   * http://hehejun.blogspot.com/2015/01/leetcodecandy.html
   * Clean and elegant DP solution learned from hehejun
   *
   * Also refer to fish's plots.
   * http://fisherlei.blogspot.com/2013/11/leetcode-candy-solution.html
   * @param ratings Children's ratings
   * @return the minimum candies you must give
   */
  public int candy(int[] ratings) {
    // Write your code here
    if (ratings.length == 0)
      return 0;
    int len = ratings.length;
    int[] candy = new int[len];
    candy[0] = 1;
    for (int i = 1; i < len; i++)
      candy[i] = ratings[i] > ratings[i - 1]? candy[i - 1] + 1: 1;
    int sum = candy[len - 1];
    for (int i = len - 2; i >= 0; i--) {
      candy[i] = ratings[i] > ratings[i + 1]? candy[i] >= candy[i + 1] + 1? candy[i]: candy[i + 1] + 1: candy[i];
      sum += candy[i];
    }
    return sum;
  }
}
