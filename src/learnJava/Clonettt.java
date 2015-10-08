package learnJava;

public class Clonettt {
  public static void main(String[] args) {
    int[] arr = new int[]{2, 6, 3, 5, 1};
    int[] b = arr.clone();
    b[0] = 99;
    b[1] = -1000;
    arr = b.clone();
    p(arr);
  }
  
  public static void p(int[] arr) {
    for (int i : arr) {
      System.out.print(i+" ");
    }
  }
}
