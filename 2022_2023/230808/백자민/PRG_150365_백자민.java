package day0808;

public class PRG_150365_백자민 {
	class Solution {
	    
	    static int N,M,R,C;
	    static String answer;
	    
	    static int[] di = {1,0,0,-1}; //d-l-r-u 순서
	    static int[] dj = {0,-1,1,0};
	    static String[] command = {"d","l","r","u"};
	    
	    static void dfs(int i, int j, int k, String tmp, int dist) {
	        if(k==0 && dist==0) {
	            answer = tmp;
	            return;
	        }
	        
	        for(int d=0;d<4;d++) {
	            int ni = i+di[d];
	            int nj = j+dj[d];
	            
	            if(ni<1 || ni>N || nj<1 || nj>M) continue;
	            
	            if(dist>k) continue;
	                        
	            dfs(ni,nj,k-1, tmp+command[d], Math.abs(ni-R)+Math.abs(nj-C));
	            
	            if(answer.length()>0) return; //사전순으로 가장 빠른 답을 출력하므로 더 이상 탐색을 진행하지 않음
	        }
	    }
	    
	    public String solution(int n, int m, int x, int y, int r, int c, int k) {
	        N = n;
	        M = m;
	        R = r;
	        C = c;
	        answer = "";
	        
	        int dist = Math.abs(x-r)+Math.abs(y-c);
	        
	        if((dist%2 != k%2) || dist>k)
	            return "impossible";
	        
	        dfs(x,y,k,"",dist);
	        
	        return answer;
	    }
	}
}
