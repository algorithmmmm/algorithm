package day0627;
import java.util.*;

public class PRG_92343_백자민 {

	class Solution {
	    
	    static int[] info;
	    static int[][] edges;
	    static int max;
	    
	    static void dfs(int sheep, int wolf, int temp, boolean[] visit) {
	        visit[temp] = true;
	        if(info[temp]==1) { //늑대가 있는 노드
	            if(wolf+1>=sheep) //늑대가 한 마리 더 늘어나서 양을 잡아먹을 경우 종료
	                return;
	            wolf++;
	        } else { //양이 있는 노드
	            sheep++;
	            if(sheep>max)
	                max = sheep;
	        }
	        
	        for(int[] edge: edges) {
	            if(visit[edge[0]] && !visit[edge[1]]) {
	                dfs(sheep, wolf, edge[1], visit.clone());
	            }
	        }
	    }
	    
	    public int solution(int[] tinfo, int[][] tedges) {
	        max = 0;
	        info = tinfo;
	        edges = tedges;
	        boolean[] visit = new boolean[info.length];
	        dfs(0,0,0,visit);
	        
	        return max;
	    }
	}
}

