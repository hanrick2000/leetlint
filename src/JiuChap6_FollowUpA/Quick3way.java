package JiuChap6_FollowUpA;

/**
 * Created at 9:42 AM on 11/16/15.
 */
public class Quick3way {
  public static void main(String[] args) {
    int[] A = new int[] {1, 2, 3, 4, 5, 6, 8, 9, 10, 7, 5, 5, 6}; //{1,1,1}; //
    Quick3way q3 = new Quick3way();
    q3.sort(A, 0, A.length-1);
    for (int i : A) {
      System.out.print(i + " ");
    }
  }
  public void sort(int[] A, int lo, int hi) {
    if (lo >= hi)  return;
    int lt = lo, i = lo+1, gt = hi;
    int x = A[lo];
    while (i <= gt) {
      int cmp = A[i] - x;
      if (cmp < 0)  exch(A, lt++, i++);
      else if (cmp > 0)  exch(A, i, gt--);
      else  i++;
    }
    sort(A, lo, lt-1);
    sort(A, gt+1, hi);
  }

  private void exch(int[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
  }
}
