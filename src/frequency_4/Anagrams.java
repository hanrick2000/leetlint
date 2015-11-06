package frequency_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;



//Given an array of strings, return all groups of strings that are anagrams.
//
//Note: All inputs will be in lower-case.


//���Ǹ���n��string Ȼ�󷵻����滥Ϊanagram�Ĵ� �ȷ�˵ abc def bca �ͷ��� abc bca
//��Ҫ�������� ����д�ж�2��string�Ƿ�Ϊanagram�ķ���                                                          
///��hashmap�� key��sorted���string val�� Arraylist<String> ��Ż�Ϊanagram���ǵ�ԭʼstring
//�ȷ�˵ key�� abcd  val���� bcda  �� dbca

public class Anagrams {

	
public ArrayList<String> anagrams(String[] strs) {
HashMap<String, ArrayList<String>> map=new HashMap<String, ArrayList<String>>();
ArrayList<String> result=new ArrayList<String>();
ArrayList<String> temp;
for(String s:strs){
	char[] chars=s.toCharArray();
	Arrays.sort(chars);
	String key=new String(chars);
	//key is sorted word
	//���map��û�����sorted word
	if(!map.containsKey(key)){
    		temp=new ArrayList<String>();
    		temp.add(s);//ע�� Arraylist��ӵ� ��ԭʼ��δ����s ����anagram
	 map.put(key,temp);
	}else{
	map.get(key).add(s);}}

//��Ϊmap���key��sorted����Ĵ� 
//ÿ��sorted�ʿ�������ԭʼs�м��� ����2���Ļ����ǻ�Ϊanagram 
//
for(String s:map.keySet()){
      //��val �Ǹ�arraylist
	if(map.get(s).size()>1){
		//���� arraylist
		for(String string:map.get(s)){
			result.add(string);
		}
	}
}
return result;
}

    


	
 
	
	public static void main(String[] args) {
String[] strs={"","","abc","cba","da","e"};
System.out.print((new Anagrams()).anagrams(strs));
 
	}

	
	
	
}
