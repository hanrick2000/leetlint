package nineChap5_DP2;

public class WildMatch {
  public static void main(String[] args) {
    String S = "mississippi";
    String P = "m*iss*p?";
    boolean ans = false;
    ans = isMatch(S, P);
    System.out.println("I use N00t's DP: " + ans);

    ans = isMatch2D(S, P);
    System.out.println("I use 2D DP: " + ans);
  }

  /**
   * 
   * @param S
   * @param P
   * @return
   */
  public static boolean isMatch(String S, String P) {
    int plenNoStar = 0;
    int plen = P.length();
    int slen = S.length();
    for (int i = 0; i < plen; ++i) {
      if (P.charAt(i) != '*') {
        plenNoStar++;
      }
    }
    if (plenNoStar > slen)
      return false;

    boolean[] F = new boolean[slen + 1];
    F[0] = true;
    int pi = 0, firstMatch = 0;
    while (pi < plen) {
      // skip duplicate '*'
      if (pi > 0 && P.charAt(pi) == '*' && P.charAt(pi - 1) == '*') {
        pi++;
        continue;
      }

      // if '*', fill up the rest of row
      if (P.charAt(pi) == '*') {
        for (int i = firstMatch + 1; i <= slen; ++i) {
          F[i] = true;
        }
      } else {
        // fill up backwards:
        int match = -1;
        for (int i = slen; i > firstMatch; --i) {
          F[i] =
              (P.charAt(pi) == S.charAt(i - 1) || P.charAt(pi) == '?')
                  && F[i - 1];
          if (F[i])
            match = i;
        }
        if (match < 0)
          return false;
        firstMatch = match;
      }

      ++pi;
    }

    return F[slen];
  }

  /**
   * What's the algs? I should have written down the idea so I can review it easier later, eg, now...
   * Took me an hour to debug!
   * @param s
   * @param p
   * @return
   */
  public static boolean isMatch2D(String s, String p) {
    int plenNoStar = 0;
    int plen = p.length();
    int slen = s.length();
    while (plenNoStar < plen) {
      if (p.charAt(plenNoStar) != '*') {
        break;
      }
    }
    if (plenNoStar > slen) {
      System.out.println("your bad");
      return false;
    }

    boolean[][] F = new boolean[plen + 1][slen + 1];
    F[0][0] = true;

    int firstMatch = 0;
//    outerloop: // label for break
    for (int pi = 1; pi < plen + 1; ++pi) {
      int match = -1;
      for (int si = firstMatch + 1; si < slen + 1; ++si) {
        // if dup '*', simply copy last row to current row. skip duplicate '*'
        if (pi > 1 && p.charAt(pi - 1) == '*'
            && p.charAt(pi - 1) == p.charAt(pi - 2)) {
          copyRow(F, pi);
          break;
        }

        // set row to true
        if (p.charAt(pi - 1) == '*') {
          F[pi][si] = true;
        } else {
          F[pi][si] =
              (p.charAt(pi - 1) == s.charAt(si - 1) || p.charAt(pi - 1) == '?')
                  && F[pi - 1][si - 1];
          if (F[pi][si]) {
            boolean flg = false;
            if (match < 0) {
              flg = true;
            }
            match = si - 1;
            
            if (flg == true)  firstMatch = match;
          }
        }
      }
//      if (p.charAt(pi - 1) != '*') {
//        if (match < 0) {
//          System.out.println("my bad" + pi);
//          // return false;
//          break outerloop;
//        }
//        firstMatch = match;
//      }
//      System.out.println(firstMatch + " " + pi);
    }

    printDP(F);

    return F[plen][slen];
  }

  private static void copyRow(boolean[][] mat, int r) {
    for (int c = 0; c < mat[0].length; ++c) {
      mat[r][c] = mat[r - 1][c];
    }
  }

  private static void printDP(boolean[][] DP) {
    int plen = DP.length;
    int slen = DP[0].length;
    for (int pi = 0; pi < plen; ++pi) {
      for (int si = 0; si < slen; ++si) {
        System.out.print(bool2int(DP[pi][si]) + " ");
        if (si == slen - 1)
          System.out.println("");
      }
    }
  }

  private static int bool2int(boolean b) {
    return (b == true) ? 1 : 0;
  }
}
