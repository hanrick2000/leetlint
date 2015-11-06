package learnJava;

/**
 * For Java param, it ALWAYS pass-by-val
 * @author tzhang
 *
 */

public class ParamPrimitive {
  private static class Int {
    private int i;
    private String j;
    
    public Int(int iV, String jV) {
      i = iV;
      j = jV;
    }
    
    public String toString() {
      String output = i + " " + j;
      return output;
    }
  }
  
  public static void main(String[] args) {
    int i = 5;
    modify(5);
    System.out.println(i);
    
    Int intV = new Int(10, "Jerry");
    modify(intV);
    System.out.println(intV);
  }
  
  public static void modify(int i) {
    i = i+10;
  }
  
  public static void modify(Int inte) {
    inte.i += 5;
    inte.j = "Tony:";
  }
}
