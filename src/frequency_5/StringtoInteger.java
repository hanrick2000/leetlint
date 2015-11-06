package frequency_5;

import java.lang.reflect.Array;

// Implement atoi to convert a string to an integer.
//
// Hint: Carefully consider all possible input cases. If you want a challenge, please do not see
// below and ask yourself what are the possible input cases.
//
// Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You
// are responsible to gather all the input requirements up front.
//
// spoilers alert... click to show requirements for atoi.
// Requirements for atoi:
//
// The function first discards as many whitespace characters as necessary until the first
// non-whitespace character is found. Then, starting from this character, takes an optional initial
// plus or minus sign followed by as many numerical digits as possible, and interprets them as a
// numerical val.
//
// The string can contain additional characters after those that form the integral number, which are
// ignored and have no effect on the behavior of this function.
//
// If the first sequence of non-whitespace characters in str is not a valid integral number, or if
// no such sequence exists because either str is empty or it contains only whitespace characters, no
// conversion is performed.
//
// If no valid conversion could be performed, a zero val is returned. If the correct val is out
// of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

// ���⿼����Ҫ��ܶ������� �Ƿ��������������� ��������
public class StringtoInteger {
  public static int atoi(String str) {
    str = str.trim();
    long value = 0;
    if (str.equals("")) {
      return 0;
    }
    boolean isPositive = true;
    for (int i = 0; i < str.length(); i++) {
      // �ڵ�һλ��״̬���ȴ����һλ ע������д�� ������ �߼�������
      if (i == 0 && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
        if (str.charAt(i) == '-') {
          isPositive = false;
        }
        continue;
      } // continue����˼�� continue����Ķ���ִ���� ֱ�ӽ�����һ��ѭ��
      // �㿴 ����ASCII ��ȴ�С,�㲻�ðɾ�����뱳����
      // ֻҪ��0С ��9��� ����������,������ʹ��������ʽ
      // ����ֻ��string����������char������ֱ����
      if (str.charAt(i) < '0' || str.charAt(i) > '9') {
        break;
      }
      // ����������� ��Ϊ�Ǵ����Ҷ�ȡ �ϴε�ֵx10+���ε�ֵ ����currentֵ
      // eg��123= /1/1*10+2/12x10+3 =123
      value = 10 * value + str.charAt(i) - '0';
    }
    if (isPositive == false) {
      value = value * -1;
    }
    if (value > Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    } else if (value < Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    } else {
      return (int) value;
    }// �����Ѿ�������int��Χ����

  }



  public static void main(String[] args) {
    System.out.print(atoi("  -233"));
  }

}
