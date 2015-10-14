package Easy;

/*-
 * Lintcode shows it as an easy problem. 
 * But evil comes with detail!
 * 
 * 1. hash function has a lot multiplication and int32 is +/- so max is 0x7FFF_FFFF, very quick to overflow
 * 2. I can solve with long type, AC.
 * 3. use this method: http://blog.csdn.net/nicaishibiantai/article/details/43340911
 * 
 * @author tzhang
 *
 */
public class Hash33 {
  public static int hashCode(char[] key, int HASH_SIZE) {
    // write your code here
    if (key == null || key.length == 0) {
      return 0;
    }
    long sum = 0;
    int i = 0;
    for (; i < key.length - 1; ++i) {
      sum = (sum + key[i]) * 33;
      sum = sum % HASH_SIZE;
    }
    sum += key[i];
    sum = sum % HASH_SIZE;
    return (int) sum;
  }
  
  public static void main(String[] args) {
    /**
     * Good cases: 
     * 1. ubuntu, 1007 -> 549: I should do mod in each iteration
     * 2. 0x3fffffff, 128 -> 5: The last sum need to do mod
     * 3. "Wrong answer or accepted?", 1000000007: I got overflow?
     */
    String str = "Wrong answer or accepted?";
    int ans = hashCode(str.toCharArray(), 1_000_000_007);
    System.out.println(ans);
  }
}
