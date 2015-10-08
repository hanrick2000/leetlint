package learnJava;

import java.util.StringTokenizer;

public class Parse {
  public static void main(String[] args) {
    String s = "12 ";
    s = s.trim();
    int a = Integer.parseInt(s);
    System.out.println(a);

    StringTokenizer st = new StringTokenizer("0 1 5 # # # 2 3 # # 4 # # ");
    while (st.hasMoreTokens()) {
      String tk = st.nextToken();
      if (!tk.equals('#')) {
        int tkn = Integer.parseInt(tk);
        System.out.print(tkn);
      }
    }
  }
}
