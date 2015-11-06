package frequency_2;

//Given a sorted array and a target val, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
//You may assume no duplicates in the array.
//
//Here are few examples.
//[1,3,5,6], 5 �� 2
//[1,3,5,6], 2 �� 1
//[1,3,5,6], 7 �� 4
//[1,3,5,6], 0 �� 0 

//�����Ǻ���ͨ��binary search ��һ����,�������û�ҵ��Ļ����Ƿ���-1
//���Ƿ��� �����target���Ҫ����������� ���ڵ��Ǹ��±��λ��
//���Ծ����㷨BSģ�� ����Ϊ�˺ܺõ�meet�����������ġ�

public class SearchInsertPosition {
	public int searchInsert(int[] A, int target) {
		int start = 0;
		int end = A.length - 1;
		int mid;
		// A ��sorted
		if (target < A[0]) {
			return 0;
		}
		// ����bsģ��,�ұ�targetС�������Ǹ��� �����Ⱦ��ҵ���
		// �������ȣ���ô�����λ��Ӧ�þ�����������±�ĺ�һλ��
		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (A[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
			if (A[end] == target) {
				return end;
			}
		}
		if (A[end] < target) {
			return end + 1;
		}
		if (A[start] == target) {
			return start;
		}
		return start + 1;
	}
}
