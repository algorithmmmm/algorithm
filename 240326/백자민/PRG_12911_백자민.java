package day240326;

public class PRG_12911_백자민 {
	class Solution {
	    public int solution(int n) {
	        int answer=0, nCnt=Integer.bitCount(n);
	        
	        for(int tmp=n+1;tmp<=Integer.MAX_VALUE;tmp++) {
	            if(Integer.bitCount(tmp)==nCnt) {
	                answer = tmp;
	                break;
	            }
	        }
	        
	        return answer;
	    }
	}
}
