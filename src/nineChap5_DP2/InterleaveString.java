package nineChap5_DP2;

public class InterleaveString {
  public static void main(String[] args) {
    String S1 = "ct";
    String S2 = "rabbib";
    String S3 = "rabbibct";

    S1 =
        "sdfjas;dfjoisdufzjkndfasdkfja;sdfa;dfa;dfaskdjhfasdhjdfakhdgfkajdfasdjfgajksdfgaksdhfasdkbfjkdsfbajksdfhakjsdfbajkdfbakdjsfgaksdhgfjkdsghfkdsfgadsjfgkajsdgfkjasdfh";

    S2 =
        "dfnakdjnfjkzghdufguweygfasjkdfgb2gf8asf7tgbgasjkdfgasodf7asdgfajksdfguayfgaogfsdkagfsdhfajksdvfbgkadsghfakdsfgasduyfgajsdkfgajkdghfaksdgfuyadgfasjkdvfjsdkvfakfgauyksgfajkefgjkdasgfdjksfgadjkghfajksdfgaskdjfgasjkdgfuyaegfasdjkfgajkdfygadjskfgjkadfg";

    S3 =
        "sdfjas;dfjoisdfnakdjnfjkzghdufguwdufzjkeygfasjkdfgb2gf8asf7ndtgbgasjkdfgasodf7asdfgfajkasdksdfguayfgaogfsdkagfsfjadhfajksdvfbgkadsghfa;sdkdsfgasduyfgajsdkfgafajkdghfaksdgfuyadgfas;dfjkdvfjsdkvfakfgauyksa;dgfajkefgjkdasgfdjksffaskdjhfasdhjdfakhdgadjkghfajgfkajdfksdfgaskdjfgasjkdgfuasdjfgajksdfgaksdhfasdkbfjkdsfbajksdfyaegfasdjkfgajkdfygadjskfgjkadfghakjsdfbajkdfbakdjsfgaksdhgfjkdsghfkdsfgadsjfgkajsdgfkjasdfh";

//    S1 = "ra";
//    S2 = "bac";
//    S3 = "rbaa";

    boolean ans = isInterleave1(S1, S2, S3);
    System.out.println("I use simple 2 sequence DP: " + ans);

    ans = isInterleave2(S1, S2, S3);
    System.out.println("I use 1d rolling DP: " + ans);
  }

  public static boolean isInterleave1(String s1, String s2, String s3) {
    if (s3 == null || s1.length() + s2.length() != s3.length()) {
      System.out.println("Break early");
      return false;
    }
    int m = s1.length();
    int n = s2.length();
    boolean[][] F = new boolean[m + 1][n + 1];
    F[0][0] = true;

    for (int i = 1; i < m + 1; ++i) {
      F[i][0] = true;
      if (s1.charAt(i - 1) != s3.charAt(i - 1)) {
        F[i][0] = false;
        break;
      }
    }

    for (int i = 1; i < n + 1; ++i) {
      F[0][i] = true;
      if (s2.charAt(i - 1) != s3.charAt(i - 1)) {
        F[0][i] = false;
        break;
      }
    }

    for (int i = 1; i < m + 1; ++i) {
      for (int j = 1; j < n + 1; ++j) {
        // if (s3.charAt(i + j - 1) == s1.charAt(i - 1)) {
        // F[i][j] = F[i - 1][j];
        // } else if (s3.charAt(i + j - 1) == s2.charAt(j - 1)) {
        // F[i][j] = F[i][j - 1];
        // } else {
        // F[i][j] = false;
        // }
        F[i][j] =
            F[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1)
                || F[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1);
//        System.out.print(bool2int(F[i][j]) + " ");
//        if (j == n)
//          System.out.println("");
      }
    }

    return F[m][n];
  }

  /**
   * F[j]: the last row i's F[i][j].
   * @param s1
   * @param s2
   * @param s3
   * @return
   */
  public static boolean isInterleave2(String s1, String s2, String s3) {
    if (s3 == null || s1.length() + s2.length() != s3.length()) {
      System.out.println("Break early");
      return false;
    }
    int m = s1.length();
    int n = s2.length();
    boolean[] F = new boolean[n + 1]; 
    F[0] = true;
    
    for (int j = 1; j < n+1; ++j) {
      F[j] = true;
      if (s2.charAt(j-1) != s3.charAt(j-1)) {
        F[j] = false;
        break;
      }
    }
    
    for (int i = 1; i < m+1; ++i) {
      for (int j = 0; j < n+1; ++j) {// (int j = n; j > 0; j--) {
        if (j == 0) {
          F[j] = (s1.charAt(i-1) == s3.charAt(i+j-1))? true && F[j] : false;
        }
        else {
          F[j] = s1.charAt(i-1) == s3.charAt(i+j-1) && F[j] ||
              s2.charAt(j-1) == s3.charAt(i+j-1) && F[j-1];
        }
      }
    }
    
    return F[n];
  }



  private static int bool2int(boolean b) {
    return (b == true) ? 1 : 0;
  }
}
