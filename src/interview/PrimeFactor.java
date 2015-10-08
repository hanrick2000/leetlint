package interview;

// http://n00tc0d3r.blogspot.com/2013/08/prime-numbers.html

public class PrimeFactor {
  public static void main(String[] args) {
    int x = 12;
    factor(x);
  }


  public static void factor(int x) {
    if (x < 2)
      return;
    while ((x & 1) == 0) {
      System.out.print(2 + " ");
      x = x >> 1;
    }

    int maxFactor = (int) Math.sqrt(x);
    for (int i = 3; i <= maxFactor; ++i) {
      while ((x % i) == 0) {
        System.out.print(i + " ");
        x /= i;
      }
    }

    if (x > 2) { // there is at most one prime factor > sqrt(n)
      System.out.println(x);
    }

  }

}
