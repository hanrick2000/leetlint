package freq1_tony;

public class UniqueBST1 {
  public static void main(String[] args) {
    int n = 3;
    int ans = numTreesYu(n);
    System.out.println(ans);
  }

  /**
   * http://blog.unieagle.net/2012/11/01/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Aunique-binary-search-
   * trees%EF%BC%8C%E4%B8%80%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/ 理解了就很容易懂了.
   * 特别是lway/rway的含义. 而且这里的1d dp的update也和以往的直接赋值不一样, 这里是for loop里面+=. 其实就是sigma. 不过第一次看到, 所以不熟悉.
   * 
   * @param n
   * @return
   */
  public static int numTreesYu(int n) {
    if (n <= 0)
      return 0;
    int[] opt = new int[n + 1];
    opt[0] = 1;
    opt[1] = 1;
    for (int i = 2; i <= n; ++i) {
      for (int j = 0; j < i; ++j) {
        int lway = opt[j];
        int rway = opt[i - j - 1];
        opt[i] += lway * rway;
      }
    }
    return opt[n];
  }
}
