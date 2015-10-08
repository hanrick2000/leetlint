package freq5_tony;

import java.util.*;
/**
 * this is different the one in CC150, since ltcd requires IN PLACE! no additional 
 * data structure.
 */

public class SetMatrixZeros {

  /**
   * ideas: why not find zeros then clear the row?
   * since | 2 3 0 4 0|
   *       | 1 2 3 0 4| then first 0 will zero the first row and forgot the 2nd 0
   *       therefor cannot clear the last column.
   * Then we have to have 2 sets to store row/col info.
   * The idea is to clear zeros row/col so we can find zeros for 1st row/col then use
   * a flag to store the info and put other row/col's info into 1st row/col.
   */
  
  /**
 * solution 0: naively iterate all element in matrix and store row/col into a
 * hashset. after that, in next step, iterate again and set all i,j to 0.
 * The time complexity is O(n*m) and additional space O(n+m)
 * @param matrix
 */
  public void setMatrixZeros0(int[][] matrix){
    Set<Integer> rows = new HashSet<Integer>();
    Set<Integer> cols = new HashSet<Integer>();
    for (int i=0; i<matrix.length; i++){
      for (int j=0; j<matrix[0].length; j++){
        if (matrix[i][j] == 0){
          rows.add(i);
          cols.add(j);
        }
      }
    }
    
    for (int i = 0; i<matrix.length; i++){
      for (int j = 0; j<matrix[0].length; j++){
        if (rows.contains(i) || cols.contains(j))   
          matrix[i][j] = 0;
      }
    }
  }
  
  /**
   * solution 1: this time I will use ltcd requirement: in place. so no additional data?
   * but how?? 
   */
  public void setMatrixZeros1(int[][] matrix){
    boolean firstRow = false, firstCol= false;
    for (int j = 0; j< matrix[0].length; j++){
      if (matrix[0][j] == 0){
        firstRow = true;
        break;
      }
    }
    for (int i = 0; i< matrix.length; i++){
      if (matrix[i][0] == 0){
        firstCol = true;
        break;
      }
    }
    
    // this is to store info into the 1st row/col
    for (int i = 1; i<matrix.length; i++){
      for (int j = 1; j<matrix[1].length; j++){
        if (matrix[i][j] == 0){
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    
    // then we need 2 steps to set zeros:
    // 1. use info in 1st row/col to clear others
    // 2. then we can clear 1st row/col
    for (int i = 1; i<matrix.length; i++){
      for (int j = 1; j<matrix[0].length; j++){
        if (matrix[i][0] == 0 || matrix[0][j] == 0){
          matrix[i][j] = 0;
        }
      }
    }
    if (firstRow = true){
      for (int j = 0; j<matrix[0].length; j++){
        matrix[0][j] = 0;
      }
    }
    if (firstCol = true){
      for (int i = 0; i<matrix.length; i++){
        matrix[i][0] = 0;
      }
    }
  }
  public int testRef(int a){
    a = a+99;
    return a;
  }
  
  public static void printMat(int[][] matrix){
    for (int i = 0; i<matrix.length; i++){
      for (int j = 0; j<matrix[1].length; j++){
          System.out.print(matrix[i][j] + " ");
          if (j == matrix[1].length-1){
            System.out.println("");
            continue;
        }
      }
    }
  }
  /**
   * use CMU-ebiz's tip to store 
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[][] matrix = { {0, 0, 0, 5}, {4, 3, 1, 4}, {0, 1, 1, 4}, {1, 2, 1, 3}, {0, 0, 1, 1}};
    printMat(matrix);
    System.out.println("");
    new SetMatrixZeros().setMatrixZeros1(matrix);
    printMat(matrix);
//    System.out.println(new SetMatrixZeros().testRef(10));
  }

}
