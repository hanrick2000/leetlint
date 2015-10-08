package freq5_tony;

public class IsNumber {

  private enum Type {
    Space(0), Sign(1), Digit(2), Dot(3), Exp(4), Null(-1);
    private int type;

    private Type(int t) { // constructor
      type = t;
    }

    public int getType() {
      return type;
    }
  }

  /**
   * build the FSM: state table, state transition, state begin/end
   * 
   * @param s
   * @return
   */
  public boolean isNumber(String s){
    int[][] states = {
        {0, 8, -1, -1, 8, -1, -1, 8, 8},
        {2, -1, -1, -1, -1, 6, -1, -1, -1},
        {1, 1, 1, 4, 4, 7, 7, 7, -1},
        {3, 4, 3, -1, -1, -1, -1, -1, -1},
        {-1, 5, -1, -1, 5, -1, -1, -1, -1}
    };         // remember this ';'
    
    int state = 0;
    for (Character c:s.toCharArray()) {
      Type inputtype = Type.Null;
      switch (c){
        case ' ':
          inputtype = Type.Space;
          break;
        case '+' : case '-':
          inputtype = Type.Sign;
          break;
        case '0' : case '1' : case '2' : case '3' : case '4' : case '5':
        case '6' : case '7' : case '8' : case '9' :
          inputtype = Type.Digit;
          break;
        case '.' :
          inputtype = Type.Dot;
          break;
        case 'e' : case 'E':
          inputtype = Type.Exp;
          break;
        default:
          inputtype = Type.Null;
          return false;
      }
      
      state = states[inputtype.getType()][state];
      if (state < 0){
        return false;
      }
    }
    return (state == 1 || state == 4 || state == 7 || state == 8);
    
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String str = new String("  3.7E8");
    System.out.println(new IsNumber().isNumber(str));
    str = new String("  3.7E.8");
    System.out.println(new IsNumber().isNumber(str));
  }

}
