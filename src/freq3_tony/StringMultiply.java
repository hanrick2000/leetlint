package freq3_tony;

public class StringMultiply {
  /**
   * 
   * @param num1
   * @param num2
   * @return
   */
  public static String multiply(String num1, String num2) {  
    if (num1.equals("0") || num2.equals("0"))  return "0";
    int l1 = num1.length(), l2 = num2.length();
    int[] n1 = new int[l1];
    int[] n2 = new int[l2];
    int[] res = new int[l1+l2];
    // convert n1,n2 into array. 注意这里的index. 有意思
    for (int i = 0; i < l1; ++i) {
      n1[i] = num1.charAt(i) - '0';
    }
    for (int j = 0; j < l2; ++j) {
      n2[j] = num2.charAt(j) - '0';
    }
    // multiply n1,n2 into array. 还是注意这里的index, 很有意思. +1是为了留res[0]
    for (int i = 0 ; i < l1; ++i) {
      for (int j = 0 ; j < l2; ++j) {
        res[i+j+1] += n1[i] * n2[j];  // 有意思
      }
    }
    // convert to string
    StringBuilder sb = new StringBuilder();
    for (int k = l1+l2-1; k >= 0; --k) {
      sb.append((char)(res[k] % 10 + '0'));
      if (k> 0)  res[k-1] += res[k] / 10;
    }
    // 去掉保留的最高位, 如果是0的话
    int count = sb.charAt(sb.length()-1) == '0' ? 1 : 0;
    String s = sb.reverse().substring(count);
    return s;
  }  
  
  public static void main(String[] args) {
    System.out.println(multiply("45", "123"));
  }
}
