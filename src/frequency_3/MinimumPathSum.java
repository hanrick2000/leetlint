package frequency_3;

//Given a m x n grid filled with non-negative numbers, 
//find a path from top left to bottom right which minimizes the sum of all numbers along its path.
//
//Note: You can only move either down or right at any point in time.
//����һ������ ���� �ö�Ǹ���
//Ȼ��Ҫ������ϵ�������һ��path ʹ��path���ڵĺ���С Ȼ�󷵻غ�

//����DPģ�� matrixDP Ԥ���������󷵻�sum[rows-1][cols-1] 
//sum���������ÿһ�񶼴������ߵ��ⲽ���������sum��
//1.��initial ������  sum[0][col] = sum[0][col - 1] + grid[0][col];
//Ȼ��2.DP (˫��ѭ�� Ȼ��    sum[row][col]=Math.min(sum[row-1][col],sum[row][col-1])+grid[row][col];) Math.min�Ĵ��� ����Ϊ ֻ�ܴ����ϵ����£����Ե�ǰ���ӵ�sum. �ǵ�������ڵ����ٵ�path 

public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		// 2d matrix�� grid.length==0�� grid[0].length==0 ���涼���൱�ڿռ� ���ܴ�Ԫ��
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}

		int rows = grid.length;
		int cols = grid[0].length;
		int[][] sum = new int[rows][cols];
		//initialize 
		sum[0][0] = grid[0][0];
		 
		for (int row = 1; row < rows; row++) {
			// ǰһ����sum�ӱ���grid[][]�� val���Ǳ����sum
			sum[row][0] = sum[row - 1][0] + grid[row][0];
		}

		for (int col = 1; col < cols; col++) {
			sum[0][col] = sum[0][col - 1] + grid[0][col];
		}
		// dp
		for (int row = 1; row < rows; row++) {
			for (int col = 1; col < cols; col++) {
				//��Ϊ �� Math.min�Ĵ��� ����Ϊ ֻ�ܴ����ϵ����£����Ե�ǰ����
				//��sum. �ǵ�������ڵ����ٵ�path 
         sum[row][col]=Math.min(sum[row-1][col],sum[row][col-1])+grid[row][col];
          }
		}
return sum[rows-1][cols-1];
	}
}
