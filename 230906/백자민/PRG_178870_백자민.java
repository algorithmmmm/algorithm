package day0905;

public class PRG_178870_백자민 {
	class Solution {
	    public int[] solution(int[] sequence, int k) {
	        int[] answer = {0,0};
	        
	        int l=0, r=0, sum=sequence[0],len=Integer.MAX_VALUE;
	        int N = sequence.length;
	        
	        if(sum==k)
	            return answer;
	        
	        while(l<N && r<N) {
	            if(sum<k) {
	                r++;
	                if(r==N)
	                    continue;
	                sum += sequence[r];
	            } else if(sum==k) {
	                if(r-l < len) {
	                    len = r-l;
	                    answer[0] = l;
	                    answer[1] = r;
	                } else {
	                    sum -= sequence[l];
	                    l++;
	                }
	            } else {
	                sum -= sequence[l];
	                l++;
	            }
	        }
	        
	        return answer;
	    }
	}
}
