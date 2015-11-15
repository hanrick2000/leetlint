package JiuChap6_FollowUpA;

import java.util.Arrays;

/**
 * http://www.jiuzhang.com/solutions/nuts-bolts-problem/
 * http://www.lintcode.com/en/problem/nuts-bolts-problem/ Created at 3:42 PM on 11/14/15.
 */
public class NutsBolts {
  public static void main(String[] args) {
    NutsBolts nb = new NutsBolts();
    NBComparator nbcmp = new NBComparator();
    String[] nuts = {"ab", "bc", "dd", "gg"};
    String[] bolts = {"AB", "GG", "DD", "BC"};
    //int ans = nbcmp.cmp(bolts[1], nuts[1]);
    //System.out.println(ans);

    nb.sortNutsAndBolts(nuts, bolts, nbcmp);
    System.out.println(Arrays.toString(nuts));
    System.out.println(Arrays.toString(bolts));
  }
  /**
   * @param nuts: an array of integers
   * @param bolts: an array of integers
   * @param compare: a instance of Comparator
   * @return: nothing
   */
  public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
    // write your code here
    if (nuts == null || bolts == null)  return;
    if (nuts.length != bolts.length)  return;
    qsort(nuts, bolts, compare, 0, nuts.length-1);
  }

  public void qsort(String[] nuts, String[] bolts, NBComparator compt, int p, int r) {
    if (p >= r)  return;
    int par = partition(nuts, bolts[p], compt, p, r);
    partition(bolts, nuts[par], compt, p, r);
    qsort(nuts, bolts, compt, p, par-1);
    qsort(nuts, bolts, compt, par+1, r);
  }

  /**
   * http://www.jiuzhang.com/solutions/nuts-bolts-problem/
   * So beautiful design, also refer to MIT loop invariant.
   * @param str
   * @param pivot
   * @param compt
   * @param p
   * @param r
   * @return
   */
  public int partition(String[] str, String pivot, NBComparator compt, int p, int r) {
    int i = p;
    for (int j = p+1; j <= r; ++j) {
      // for '<', it's same as MIT
      if (compt.cmp(str[j], pivot) == -1 || compt.cmp(pivot, str[j]) == 1) {
        ++i;
        exch(str, i, j);
      }
      // for '=', it's well designed to maintain invariant
      else if (compt.cmp(str[j], pivot) == 0 || compt.cmp(pivot, str[j]) == 0) {
        exch(str, p, j);
        j--;
      }
    }
    exch(str, i, p);
    return i;
  }

  private void exch(String[] str, int i, int j) {
    String tmp = str[i];
    str[i] = str[j];
    str[j] = tmp;
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