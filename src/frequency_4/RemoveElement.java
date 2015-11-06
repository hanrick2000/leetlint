package frequency_4;

// Given an array and a val, remove all instances of that val in place and return the new
// length.
//
// The order of elements can be changed. It doesn't matter what you leave beyond the new length.
// ɾ�������ڸ���ֵ��Ԫ�� Ȼ�󷵻�ɾ����ĳ���
// ������������ ����ָ�붼��0��ʼ������������++�����������elem ��i j��++ A[i]=A[J]
// �������elem��ֻj++ i����.��һ���ٵ���ͬ��ʱ������ A[i]=A[j];
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

public class RemoveElement {
  public int removeElement(int[] A, int elem) {
    int i = 0;
    int j = 0;
    while (j < A.length) {
      if (A[j] != elem) {
        A[i] = A[j];
        i++;
      }
      j++;
    }
    return i;
  }

  public static void main(String[] args) {
    int[] A = {1,2,1,2,1};
    int l = (new RemoveElement()).removeElement(A, 1);
    System.out.print(l);
  }
}
// ��д������ ̫��׸
// if(A.length==0){return 0;}
// LinkedList<Integer> temp=new LinkedList<Integer>();
// for(int i=0;i<A.length;i++){
// temp.add(A[i]);
// }
// while(temp.contains(elem)){
// temp.remove((Integer) elem);
// }
// Integer[] a=(Integer[]) temp.toArray(new Integer[temp.size()]);
// A=new int[a.length];
// for (int i=0;i<a.length;i++) {
// A[i]=a[i];
//
// }
// //System.err.print(Arrays.toString(a));
// return a.length;
