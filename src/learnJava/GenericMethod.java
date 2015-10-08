package learnJava;

public class GenericMethod {
  public static void main(String[] args) {
    int[][] F1 = new int[][] {{2,3,4}, {11, 12, 13}};
    boolean[][] F2 = new boolean[][] { {true, true}, {true, false}, {false, true}};
//    printDP(F1); // http://stackoverflow.com/questions/4337187/creating-a-generic-array-instance-in-a-generic-method
  }
  
  private static <K> void printDP(K[][] DP) {
    int plen = DP.length;
    int slen = DP[0].length;
    for (int pi = 0; pi < plen; ++pi) {
      for (int si = 0; si < slen; ++si) {
        System.out.print((DP[pi][si]) + " ");
        if (si == slen - 1)
          System.out.println("");
      }
    }
  }
  
  private static <K> int bool2int(K b) {
    return 0;
//    return (b == true) ? 1 : 0;
  }
}
