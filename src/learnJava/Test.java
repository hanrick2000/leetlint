package learnJava;

public class Test {
  public static void main(String[] args) {
    String s = "ni123";
    String sub = s.substring(0, 0);
    String l = "nk";
//    System.out.println(s.length());
    for (int i = 0; i < s.length() + 1; ++i) {
      System.out.println(i + " " + s.substring(0, i));

    }
    System.out.println();
    for (int i = 0; i < l.length() + 1; ++i) {
      System.out.println(i + " " + l.substring(0, i));

    }
    System.out.println(s.substring(0,0) == l.substring(0,0));
    System.out.println(s.substring(0,1) == l.substring(0,1));
    System.out.println("n" == "n");
  }
}
