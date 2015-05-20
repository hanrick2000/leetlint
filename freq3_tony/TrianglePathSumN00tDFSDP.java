package freq3_tony;

import java.util.*;

public class TrianglePathSumN00tDFSDP {
  /**
   * 段公子+N00t! 美妙.
   * @param trian
   * @param row
   * @param column
   * @param rowMin
   * @return
   */
  private int DFS(List<List<Integer>> trian, int row, int column,  
      HashMap<Integer, Integer> rowMin) {  
    int min = trian.get(row).get(column);  
    if (row == trian.size() - 1) { // last row, return itself  
      return min;  
    }  
    /**
     * 漂亮到极致的DP+DFS. DP不用array来保存, 而是HashMap来存, 更加generic"段公子"
     * 而且HashMap是object. 就不需要再特意返回DP的结果, 至返回min就行(额, 还是要return value的", 
     */
    if (!rowMin.containsKey(row+1)) { // calculate for first column  
      min += Math.min(DFS(trian, row+1, column, rowMin),  
              DFS(trian, row+1, column+1, rowMin));  
    } else {  
      min += Math.min(rowMin.get(row+1),  
              DFS(trian, row+1, column+1, rowMin));  
    }  
    
    rowMin.put(row, min);  
    
    return min;  
  }  
  
  private int minimumTotal(List<List<Integer>> trian) {  
    return DFS(trian, 0, 0, new HashMap<Integer, Integer>());  
  }  
  
  public TrianglePathSumN00tDFSDP(){
    List<List<Integer>> trian = (new TrianglePathSumN00tDFSDummy()).buildTriangle();
    int result = minimumTotal(trian);
    System.out.println(result);
  }
  
  public static void main(String[] args) {
    TrianglePathSumN00tDFSDP tpsdd = new TrianglePathSumN00tDFSDP();
  }
}
