package freq3_tony;
/**
 * A series of problem of remove duplicate elements from array, list,...
 * 
 * @author tzhang
 *
 */
public class RemoveDuplicate {
  private int[] input;
  private int dupItem;
  private int realLength;
  
  public RemoveDuplicate(int dupItem) {
    input = new int[] { 1 , 3, 5, 7, 5, 8, 9, 9, 10, 1, 4, 5, 5, 7};
    this.dupItem = dupItem;
    this.realLength = input.length;
  }
  private int removeDuArr() {
    int count = 0;
//    int[] output = copyArr();
    for (int i = 0 ; i < input.length; i++ ) {
      if (input[i] == dupItem) {
        count++;
      }
      else {
        input[i-count] = input[i];
      }
    }
    realLength -= count;
    return count;
  }
  
  private int[] copyArr(){
    int[] copy = new int[] {};
    for (int i = 0; i < input.length; i++) {
      copy[i] = input[i];
    }
    return copy;
  }
  
  private void printString(){
    for (int i = 0 ; i < realLength; i++)
      System.out.print(input[i] + " ");
  }
  
  public static void main(String[] args) {
    RemoveDuplicate rd = new RemoveDuplicate(5);
    rd.printString();
    System.out.println("\n there are #: " + rd.removeDuArr());
    rd.printString();
  }
}
