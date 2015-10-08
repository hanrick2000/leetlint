package interview;

public class Sqrt {
  public static void main(String[] args) {
    int a = 1;
    int ans = sqrt2(a);
    System.out.println(ans);
  }

  public static int sqrt1(int num) {
    if (num <= 1) {
      return 1;
    }
    for (int i = 0; i <= num; ++i) {
      if (i * i >= num) {
        return i-1;
      }
    }
    return -1;
  }
  
  public static int sqrt2(int num) {
    if (num <= 0)  return 0;
    
    int lo = 1, hi = num;
    while (lo + 1< hi) {
      int mid = lo + (hi-lo)/2;
      if (mid * mid <= num) {
        lo = mid;
      }
      else {
        hi = mid;
      }
    }
    
    if (hi * hi <= num) {
      return hi;
    }
    return lo;
  }
}
