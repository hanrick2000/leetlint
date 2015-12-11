package Leetcode.misc;

/**
 * Created at 4:18 PM on 11/30/15.
 */
public class ExcelSheetColNum {
  public static void main(String[] args) {
    //String[] ss = new String[]{"A", "AB", "AA", "Y", "TH"};
    //for (String s : ss) {
    //  System.out.println(new ExcelSheetColNum().titleToNumber(s));
    //}
    String ans = new ExcelSheetColNum().convertToTitle(27);
    System.out.println(ans);
  }

  public String convertToTitle(int n) {
    int div = 26;
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      n--;
      int yu = n % 26;
      sb.insert(0, (char) (yu + 'A'));
      n /= div;
    }
    return sb.toString();
  }

  public String convertToTitleWRONG(int n) {
    int div = 26;
    StringBuilder sb = new StringBuilder();
    while (n != 0) {
      int yu = n % div;
      sb.insert(0, (char) (yu+'A'-1));
      n /= div;
    }
    return sb.toString();
  }

  public int titleToNumber(String s) {
    int sum = 0;
    int order = 1;
    char[] arr = s.toCharArray();
    for (int i = arr.length-1; i >= 0; i--) {
      sum += (arr[i]-'A'+1)*(order);
      order *= 26;
    }
    return sum;
  }

  public int titleToNumberWRONG(String s) {
    int sum = 0;
    for (Character ch : s.toCharArray()) {
      sum += ch-'A'+1;
    }
    return sum;
  }
}
