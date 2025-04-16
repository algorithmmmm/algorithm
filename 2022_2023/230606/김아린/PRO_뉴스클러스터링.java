import java.util.*;
import java.util.regex.*;

class Solution {
   public int solution(String str1, String str2) {
    	ArrayList<String> s1 = new ArrayList<>(); //str1의 다중집합
    	ArrayList<String> s2 = new ArrayList<>(); //str2의 다중집합
    	ArrayList<String> intersection = new ArrayList<>(); //교집합
    	ArrayList<String> union = new ArrayList<>(); //합집합
    	
    	for(int i  = 0; i < str1.length() - 1; i++) { // str1 다중집합 만들기
    		String temp1 = str1.substring(i, i + 2); //두 글자씩 끊기
    		
    		if(Pattern.matches("^[a-zA-Z]*$", temp1)) { //영문자로 된 글자 쌍만 유효
    			String temp2 = temp1.toUpperCase(); //대문자 소문자 차이 무시
    			s1.add(temp2);
    		}
    	}
    	
    	for(int i  = 0; i < str2.length() - 1; i++) { // str2 다중집합 만들기
    		String temp1 = str2.substring(i, i + 2);
    		
    		if(Pattern.matches("^[a-zA-Z]*$", temp1)) {
    			String temp2 = temp1.toUpperCase();
    			s2.add(temp2);
    		}
    	}
    	
    	//교집합, 합집합 구하기
    	for(String str : s1) {
    		if(s2.remove(str)) { //s2에 있으면 제거하기
    			intersection.add(str);
    		}
    		union.add(str);
    	}
    	
    	for(String str : s2) { //제거하고 남은거 합집합에 넣기
    		union.add(str);
    	}
    	
    	//자카드 유사도 계산하기
    	int A = intersection.size();
    	int B = union.size();
    	
    	double J = 0;
    	if(A == 0 && B ==0) { //집합 A와 집합 B가 모두 공집합일 경우
    		J = 1;
    	} else {
    		J = (double)A / (double)B;
    	}
    	
        int answer = (int) (J * 65536); //소수점 아래를 버리고 정수부만 출력
        
        return answer;
    }
}