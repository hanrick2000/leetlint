package frequency_5;

import java.util.Arrays;
import java.util.HashMap;

// Given an array of integers, find two numbers such that they add up to a specific target number.
//
// The function twoSum should return indices of the two numbers such that they add up to the target,
// where index1 must be less than index2. Please note that your returned answers (both index1 and
// index2) are not zero-based.
//
// You may assume that each input would have exactly one solution.
//
// Input: numbers={2, 7, 11, 15}, target=9
// Output: index1=1, index2=2

// �����cc������ �����е㲻һ��
// 1.����CC��Ҫ�������з���target�Ľ� ����ֻ��Ҫ һ���⼴��
// 2.��Ҫ�󷵻�ԭ������±� ������������ֵ�ĺ�
// 3.�±��1��ʼ
// ���� ����Ȼ���±�++ --
// ��ȥԭ�������ң� ��ȥԭ�����Ҿ�̫��ʱ���� ���Ӷȹ����ˡ�����Ҫ������ʱ���ٴ�һ�������¼ԭʼλ�ú���λ��
// �ͺ���һ��mapping�ı� �鷳�ĺ�. ���Կ��Բμ���hashmap��������
public class TwoSum {

  public int[] twoSum(int[] numbers, int target) {
    int[] result = new int[2];
    int[] newNumbers = numbers.clone();
    Arrays.sort(newNumbers);
    int first = 0;
    int last = newNumbers.length - 1;
    int result1 = 0;
    int result2 = 0;
    while (first < last) {
      if (newNumbers[first] + newNumbers[last] == target) {
        for (int i = 0; i < numbers.length; i++) {
          if (newNumbers[first] == numbers[i]) {
            result1 = i;
          }
          if (newNumbers[last] == numbers[i]) {
            result2 = i;
          }
          if (result1 != 0 && result2 != 0) {
            if (result1 < result2) {
              result[0] = result1 + 1;
              result[1] = result2 + 1;
              return result;
            } else {
              result[1] = result1 + 1;
              result[0] = result2 + 1;
              return result;
            }
          }
        }
      } else if (newNumbers[first] + newNumbers[last] > target) {
        --last;
      } else {
        ++first;
      }

    }


    return null;
  }


  public int[] twoSum2(int[] numbers, int target) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    int[] result = new int[2];
    int len = numbers.length;
    assert (len >= 2); // assert��֤numbers������2����
    // ��Ϊ��Ŀ�Ѿ���������Ψһ�� ���Բ������ظ���val ���������val���Ե�key index���Ե� val
    for (int i = 0; i < numbers.length; i++) {// i++������for����֮���++��
      // ���map���Ѿ�����ȱ����һ�������� �Ǿͷ��ؽ�������û�У��ǾͰѱ�numbers[i], i ��������
      // ��Ϊһ��ʼ�϶��ǿյ�,���Կ϶��Ǳȷ�˵i=50��ʱ���ҵ���i=20����ƥ�� ���� i�Ǻ���� map.get(target-numbers[i]) ��ǰ���
      if (map.containsKey(target - numbers[i])) {
        result[1] = i + 1;
        result[0] = map.get(target - numbers[i]) + 1;
        return result;
      } else {

        map.put(numbers[i], i);
      }
    }
    return null;


  }


  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
