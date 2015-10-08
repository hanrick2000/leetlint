package freq5_tony;

public class MergeSortedArray {

  /**
   * test cases are from haoel github.
   * @param A
   * @param m
   * @param B
   * @param n
   */
  public static void mergeSortedArray(int[] A, int m, int[] B, int n) {
    int tail = n + m - 1;
    n--;
    m--;
    while (n >= 0) {         // good logic design
      if (m < 0 || A[m] <= B[n]) {
        A[tail--] = B[n--];
      } else {
        A[tail--] = A[m--];
      }
    }
  }

  public static void printArray(int[] A) {
    for (int i : A) {
      System.out.print(i + " ");
    }
    System.out.println(" ");
  }

  public static void main(String[] args) {
    // NOTICE: test case should follow the format and requirement
    int a[]={2,4,6,8,10,11, 12, 19, 23, 24, 0,0,0,0,0,0,0,0};
    int b[]={1,3,5,9,10,11};
    mergeSortedArray(a, a.length-8, b, b.length ); 
    printArray(a);
    
    //
    int c[]={2,4,6,8,10,0,0,0};
    int d[]={1,3,5};
    mergeSortedArray(c, 5, d, 3 );
    printArray(c);
  }

}
