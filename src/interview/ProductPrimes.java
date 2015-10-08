package interview;

// http://www.fgdsb.com/2015/01/17/factors-of-product-of-distinct-primes/

/*-
 Print all factors of the product of a given list of distinct primes.
 Example:
 Input: 2 3 7
 Output: 1 2 3 6 7 14 21 42
 */
public class ProductPrimes {
  public static void main(String[] args) {
    int[] primes = new int[] {2, 3, 7};
    factoring(primes, 0, 1);
  }

  public static void factoring(int[] primes, int id, int cur) {
    if (id == primes.length) {
      System.out.print(cur + " ");
      return;
    }
    factoring(primes, id + 1, cur);
    factoring(primes, id + 1, cur * primes[id]);
  }
}
