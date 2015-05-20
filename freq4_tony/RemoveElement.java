package freq4_tony;

import java.util.LinkedList;
import java.util.List;

public class RemoveElement {

  /**
   * naive....
   * @param A
   * @param elem
   * @return
   */
  public int removeElement0(int[] A, int elem) {
    int cnt = 0;
    List<Integer> B = new LinkedList<>();
    for (int i = 0; i < A.length; i++) {
      if (A[i] == elem) {
        cnt++;
      } 
      else
        B.add(A[i]);
    }

    for (int i : B) {
      System.out.print(i + " ");
    }
    return cnt;
  }
  
  // WRONG!!!
  public int removeElement1(int[] A, int elem) {
    int cnt = 0;
    for (int i =0 ; i<A.length-cnt; i++){
      if (A[i] == elem){
        cnt ++;
        for (int j = i; j <A.length-1; j++){
          A[j] = A[j+1];
        }
      }
    }
    for (int i : A) {
      System.out.print(i + " ");
    }
    return cnt;
  }

  public int removeElement2(int[] A, int elem){
    int i,j;
    i = 0;
    j = 0;
    while (j< A.length){
//      if (A[i] != elem){
      if (A[j] != elem){
        A[i] = A[j];
        i++;
      }
      j++;
    }
    for (int k : A) {
      System.out.print(i + " ");
    }
    return i;
  }
  
  public int removeElement3(int[] A, int elem) {
    int cnt = 0;
    for (int i = 0; i<A.length; i++){
      if (A[i] == elem){
        ++cnt;
      }
      else
        A[i-cnt] = A[i];
    }
    for (int i = 0; i<A.length - cnt; i++){
      System.out.print(A[i] + " ");
    }
    return A.length - cnt;
  }
  
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[] A = {1, 2, 1, 3, 1};
    for (int i : A) {
      System.out.print(i + " ");
    }
    System.out.println("");
    int l = (new RemoveElement()).removeElement3(A, 1);
    System.out.println();
    System.out.print(l);
  }

}
