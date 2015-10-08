package interview;

public class NextPrime {
  public static void main(String[] args) {
    int x = 12;
    System.out.println(nextPrime(x));
  }
  
  public static int nextPrime(int x) {
    int ans = x+1;
    while (true) {
      if (isPrime(ans) == true) {
        break;
      }
      ans++;
    }
    
    return ans;
  }
  
  private static boolean isPrime(int x) {
    if (x <= 1)  return false;
    for (int i = 2; i < Math.sqrt(x); ++i) {
      if (x % i == 0) {
        return false;
      }
    }
    return true;
  }
}
