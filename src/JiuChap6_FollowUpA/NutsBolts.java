package JiuChap6_FollowUpA;

/**
 * http://www.lintcode.com/en/problem/nuts-bolts-problem/ Created at 3:42 PM on 11/14/15.
 */
public class NutsBolts {
  public static void main(String[] args) {
    NutsBolts nb = new NutsBolts();
    NBComparator nbcmp = new NBComparator();
    String[] nuts = {"ab", "bc", "dd", "gg"};
    String[] bolts = {"AB", "GG", "DD", "BC"};
    int ans = nbcmp.cmp(bolts[1], nuts[1]);
    System.out.println(ans);
  }
  /**
   * @param nuts: an array of integers
   * @param bolts: an array of integers
   * @param compare: a instance of Comparator
   * @return: nothing
   */
  public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
    // write your code here
  }
}

/*
* You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
* if "a" is bigger than "b", it will return 1, else if they are equal,
* it will return 0, else if "a" is smaller than "b", it will return -1.
* When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/
class NBComparator {
  public int cmp(String a, String b) {
    //http://stackoverflow.com/questions/16127923/checking-letter-case-upper-lower-within-a-string-in-java
    if (!a.equals(a.toLowerCase()) || !b.equals(b.toUpperCase())) {
      return 2;
    }
    int a_b = a.compareToIgnoreCase(b);
    if (a_b == 0) {
      return 0;
    }
    else if (a_b < 0) {
      return -1;
    }
    else {
      return 1;
    }
  }
}