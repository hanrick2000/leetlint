/**
 * 
 */
package freq5_tony;

/**
 * @author tzhang
 * 
 */
public class Pow {

  /**
   * Solution 1: pow(x,n)
   * 
   * @param
   * 
   */
  public double pow(double x, int n) {
    double ret = 1.0;

    if (n < 0) {
      for (int i = 0; i < -n; i++) {
        ret = ret * x;
      }
      return 1 / ret;
    }

    for (int i = 0; i < n; i++) {
      ret = ret * x;
    }
    return ret;
  }

  /**
   * Solution 2: pow(x,n) = pow(x,n/2) * pow(x,n/2) * pow(x,n%2) so simple recursion??! Need to have
   * a clear mind to divide into cases.
   */

  public double pow2(double x, int n) {
    int half = n / 2;
    // int half = n>>1;
    int remainder = n % 2;
    // int exp = Math.abs(n);
    // int remainder = n&1; ???
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return x;
    }
    if (n < 0 && remainder == 1 && x < 0) {
      return -1 / (pow2(-x, half) * pow2(-x, half) * pow2(-x, remainder));
    } else if (n < 0) {
      return 1 / (pow2(x, -half) * pow2(x, -half) * pow2(x, -remainder));
    } else
      return (pow2(x, half) * pow2(x, half) * pow2(x, remainder));
  }

  /**
   * Solution 3: change pow2's '/' and '%' to >>1 and '&1' Because bit operation is much faster than
   * / or %. Got it from n00tc0d3r.
   * 
   * @param x
   * @param n
   * @return
   */
  public double pow3(double x, int n) {
    if (n == 0) {
      return 1;
    } else if (n < 0) {
      return pow3(0, 0);
    }
    return 1.0;
  }


  public double pow4(double x, int n) {
    if (n == 0)
      return 1;
    int exp = Math.abs(n);
    double half = pow4(x, exp >> 1);
    double res = half * half;
    if ((n & 1) == 1)
      res *= x;
    return (n > 0) ? res : 1.0 / res;
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(new Pow().pow(-2, -3));

    System.out.println(new Pow().pow(2, -2));

    System.out.println(new Pow().pow4(-2, -3));

    System.out.println(new Pow().pow4(2, -2));
  }

}
