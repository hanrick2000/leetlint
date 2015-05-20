package freq3_tony;
/**
 * 都是一类: 从1d数据结构转到2d数据结构
 * 
 * @author tzhang
 *
 */
public class SortArrayToBST {
  private TreeNode root;
  
  private TreeNode SortArrayToBST(int[] arr, int lo, int hi) {
    if (lo > hi)  return null;
    int mid = lo + (hi-lo)/2;
    int nodeValue = arr[mid];
    TreeNode node = new TreeNode(nodeValue);
//    System.err.print(node.getvalue() + " ");
    node.setleft(SortArrayToBST(arr, lo, mid-1));
    node.setright(SortArrayToBST(arr, mid+1, hi));
    return node ;
  }
  /**
   * ctor to debug
   */
  private SortArrayToBST() {
    int[] arr = new int[] {1,3,5,7,8,9};
    int low = 0;
    int high = arr.length-1;
    root = SortArrayToBST(arr, low, high);
    Levelorder levelor = new Levelorder(root);
    System.out.print(levelor.levelorderFlag(root));
//    System.out.println("Heello??");
  }
  
  public static void main(String[] args){
    SortArrayToBST atb = new SortArrayToBST();
    
  }
}
