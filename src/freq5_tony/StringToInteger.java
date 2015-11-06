package freq5_tony;

public class StringToInteger {

  public static int atoi(String str){
    str = str.trim();   // Remember to give return val.
    char flag = '+';
    int index = 0;
    if (str.charAt(0) == '-'){
      flag = '-';
      index++;
    }
    else if (str.charAt(0) == '+'){
      index++; 
    }
    
    int ret = 0;
    while ((str.length() > index) && str.charAt(index) >= '0' && str.charAt(index) <= '9'){
      ret = ret*10 + str.charAt(index)-'0';
      System.out.println(ret);
      index++;
    }
    
    if (flag == '-')
      ret = -ret;
    
    return (int) ret;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String inp = "  -238    ";
    System.out.println(atoi(inp));
    
  }

}
