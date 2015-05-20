package freq3_tony;

/**
 * remove duplicate items from sorted array 其实给了一个思路, 就是随便的一个数列, 可以先sort, 在O(n)的排列.
 * 所以总的是复杂度是O(nLogn). 这个和kruskal algs用Union-Find一样, 本身的algs并不是限制. 限制的是sorting部分.
 * 
 * 还有一个要注意的点是处理相邻2个item的时候, loop的结束点要处理完最后那个点. 也就是说
 * A[i]的i要处理到input.length. 所以正确的做法是比较input[i] 和 input[I-1]

 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * 
 * 所以做完一题要仔细想条件变化这里或者那里会出现什么区别. 比如这里是变化出现的次数. 到时时候变化
 * 的是输入的数据结构. 比如从array变成list. Undirect graph 变成 Directed Graph. 等等.
 * @author tzhang
 *
 */
public class RemoveDupSortArr {
  private int[] input;
  // private int count;
  private int size;

  public RemoveDupSortArr(int[] input) {
    this.input = input;// new int[] {1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 5, 6, 7, 8};
    size = input.length;
    // count = 0;
  }
  // 这样就错了. 因为i只复制到i=11, 那么input[12] = 8就不管了. 
  public int removeDupSortArrWRONGWRONGWRONG() {
    int count = 0;
    if (size <= 1)
      return size;
    for (int i = 0; i < size - 1; i++) {
      if (input[i] == input[i + 1]) {
//        count++;
        ++count;
      }
      else if (count > 0) {
        input[i-count] = input[i];
      }
    }
    size -= count;
    return size;
  }
  
  // 这样就错了. 因为i只复制到i=11, 那么input[12] = 8就不管了. 
  public int removeDupSortArr() {
    int count = 0;
    if (size <= 1)
      return size;
    for (int i = 2; i < size; i++) {
      // 这里是判断和前面2个node是否相同. n00tc0d3r说这样有问题. 不过我试了没事啊?
      if (input[i] == input[i - 1]) {// && input[i] == input[i-2]) {
        count++;
      }
      else if (count > 0) {
        input[i-count] = input[i];
      }
    }
    size -= count;
    return size;
  }
  
  private void printString(){
    for (int i = 0 ; i < size; i++)
      System.out.print(input[i] + " ");
  }
  public static void main(String[] args) {
    RemoveDupSortArr rdsa = new RemoveDupSortArr(new int[] {1,1,1,2}); //1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 6, 7, 8});
    rdsa.printString();
    System.out.println("\nafter ");
    rdsa.removeDupSortArr();
    rdsa.printString();
  }
}
