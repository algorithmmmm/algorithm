package day0829;

public class PRG_150368_백자민 {
	class Solution {
	    
	    static int N;
	    static int[] answer;
	    
	    static int[] discount = {10,20,30,40};
	    
	    static void perm(int idx, int[] discounts, int[][] users, int[] emoticons) {
	        if(idx==N) { //할인율을 모두 선택했으면 계산 
	            int plus=0, cost=0;
	            
	            for(int[] u: users) {
	                int sum = 0;
	                
	                for(int i=0;i<N;i++) {
	                    if(discounts[i]>=u[0]) { //이모티콘 구매 
	                        sum += emoticons[i]/100*(100-discounts[i]);
	                    }
	                }
	                
	                if(sum>=u[1])
	                    plus++;
	                else
	                    cost+= sum;
	            }
	            
	            if(plus>answer[0]) {
	                answer[0] = plus;
	                answer[1] = cost;
	            } else if(plus==answer[0]) {
	                answer[1] = Math.max(cost, answer[1]);
	            }
	            
	            return;
	        }
	        
	        for(int d=0;d<4;d++) {
	            discounts[idx] = discount[d];
	            perm(idx+1, discounts, users, emoticons);
	        }
	    }
	    
	    public int[] solution(int[][] users, int[] emoticons) {
	        answer = new int[2];
	        N = emoticons.length;
	        perm(0,new int[N], users, emoticons);
	        
	        
	        return answer;
	    }
	}
}
