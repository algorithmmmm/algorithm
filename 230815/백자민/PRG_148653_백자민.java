package day0815;

public class PRG_148653_백자민 {
	class Solution {
	    public int solution(int storey) {
	        int answer = 0;
	        
	        while(storey>0) {
	            int tmp = storey%10; //가장 뒷자리수부터 계산
	            
	            if(tmp<5) { 
	                answer+=tmp;
	            } else if(tmp>5) {
	                storey += 10;
	                answer += (10-tmp);
	            } else { //tmp==5일 경우
	                int next = (storey%100)/10; //다음 자리 숫자
	                if(next>=5) 
	                    storey += 10;
	                answer += 5;
	            }
	            
	            storey /= 10; //그 다음 자리수로 넘어가기
	        }
	        
	        return answer;
	    }
	}
}
