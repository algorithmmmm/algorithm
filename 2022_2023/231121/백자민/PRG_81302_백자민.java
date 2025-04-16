package day1121;

import java.util.*;

public class PRG_81302_백자민 {

	class Solution {
	    
	    static int[] di = {-1,1,0,0};
	    static int[] dj = {0,0,-1,1};
	    
	    static boolean bfs(int pi, int pj, String[] map) {
	        
	        Queue<int[]> q = new LinkedList<>();
	        q.offer(new int[] {pi,pj,'P'});
	                
	        int idx=1;
	        while(true) { 
	            
	            if(idx==3) break;
	            
	            for(int i=0,size=q.size();i<size;i++) {
	                int ti = q.peek()[0];
	                int tj = q.poll()[1];
	                char tmp = map[ti].charAt(tj);

	                for(int d=0;d<4;d++) {
	                    int ni = ti+di[d];
	                    int nj = tj+dj[d];

	                    if(ni<0 || ni>=5 || nj<0 || nj>=5) continue;
	                    
	                    if(ni==pi && nj==pj) continue;

	                    char next = map[ni].charAt(nj);

	                    if(tmp=='P' && next=='P') return false;

	                    if(tmp=='O' && next=='P') return false;

	                    q.offer(new int[] {ni,nj,tmp});
	                }
	            }
	            idx++;
	        }
	        
	        
	        return true;
	    }
	    
	    static int check(String[] map) {
	        
	        for(int i=0;i<5;i++) {
	            for(int j=0;j<5;j++) {
	                if(map[i].charAt(j)=='P' && !bfs(i,j,map)) return 0; 
	            }
	        }
	        
	        return 1;
	    }
	    
	    public int[] solution(String[][] places) {
	        int[] answer = new int[5];
	        
	        for(int i=0;i<5;i++) {
	            answer[i] = check(places[i]);
	        }
	        
	        return answer;
	    }
	}
}
