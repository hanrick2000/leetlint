package frequency_3;
//Given a sorted array of integers, find the starting and ending position of a given target val.
//Your algorithm's runtime complexity must be in the order of O(log n).
//If the target is not found in the array, return [-1, -1].
//For example,
//Given [5, 7, 7, 8, 8, 10] and target val 8,
//return [3, 4].
//��һ�����ظ�ֵ��sorted array���� ��key �������key�������±� 
//��2��BS һ�������±� һ�������±�
//��ô��֤�ҵ���������/�����أ�  
//�ȷ�˵���������A[mid]=targetʱ�� end=mid �����ͱ�֤�ڴ𰸷�Χ��midԽ��Խ��� ֱ��start+1<end ����
// ����ڼ��if��ʱ��start����result[0].�����Ǵ�ʱstart������ ��ô˵��ֻ��һ�����ַ��ϴ� ����A[end]=end
// ��֮��Ȼ
public class SearchForARange {

	public int[] searchRange(int[] A, int target) {
	     //��2�ַ�(sorted-lgn) 9���㷨��ģ��
	      if(A.length==0||A==null){
	          int[] result={-1,-1};
	          return result;
	      }
	      int[] bound = new int[2]; 
	      int start=0;
	      int end=A.length-1;
	      int mid=0;
	      //�Ҵ𰸵����±�
	      while(start+1<end){
	         mid=start+(end-start)/2;
	          //���Ե�mid=tergetʱ�� end=mid�� ������mid������mid�������  
	          if(A[mid]==target){
	              end=mid;
	               
	          }else if(A[mid]<target){
	           start=mid;
	          }else{
	              end=mid;
	          }
	      }
	      if(A[start]==target){
	    	  bound[0]=start;
	      }else if(A[end]==target){
	    	  bound[0]=end;
	      }else{
	    	  bound[0]=bound[1]=-1;
	    	  return bound;
	      }
	      //search for right bound
	      start=0;
	      end =A.length-1;
	      while(start+1<end){
	    	  mid=start+(end-start)/2;
	    	  if(A[mid]==target){
	    		  start=mid; //	    ���Ե�mid=tergetʱ�� start=mid�� ������mid������mid���ұ���
	    	  }else if(A[mid]<target){
	    		  start=mid;
	    	  }else{
	    		  end=mid;
	    	  }
	    		  
	      }
	      if(A[end]==target){
	    	  bound[1]=end;
	      }else if(A[start]==target){
	    	  bound[1]=start;
	      }else{
	    	  bound[0]=bound[1]=-1;
	    	  return bound;
	      }
	      return bound;
	    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
