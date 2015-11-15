package JiuChap6_FollowUpA;

/**
 * Geek or 9chap are using MIT's partition.
 * Created at 10:07 AM on 11/15/15.
 */
public class QuickSortMIT {
  public static void main(String[] args) {
    int[] A = new int[]{1,1,1}; //{5,3,7,1,2};
    QuickSortMIT qs = new QuickSortMIT();
    qs.quickSort(A, 0, A.length-1);
    int par = qs.partition(A, 0, A.length-1);
    System.out.println(par);
    qs.quickSort(A, 0, A.length-1);
    for (int i = 0; i < A.length; ++i) {
      System.out.print(A[i] + " ");
    }
  }

  public void quickSort(int[] A, int p, int r) {
    if (p < r) {
      int q = partition(A, p, r);
      quickSort(A, p, q-1);
      quickSort(A, q+1, r);
    }
  }

  public int partition(int[] A, int p, int r) {
    int i = p-1;
    int pivot = A[r];
    for (int j = p; j < r; ++j) {
      if (A[j] <= pivot) {
        i++;
        exch(A, i, j);
      }
    }
    exch(A, i+1, r);
    return i+1;
  }

  private void exch(int[] A, int i, int j) {
    int tmp = A[i];
    A[i] = A[j];
    A[j] = tmp;
  }
}
