package day0905;
import java.util.*;

public class PRG_68645_백자민 {

	class Solution {
	    
	    static int[] di = {1,0,-1}; //아래, 오른쪽, 대각선왼쪽
	    static int[] dj = {0,1,-1};
	    
	    public int[] solution(int n) {
	        int[][] map = new int[n][n];
	        
	        int num=1;
	        int ti=0,tj=0;
	        boolean flag;
	        while(true) {
	            
	            flag = false;

	            if(map[ti][tj]==0) {
	                map[ti][tj] = num;
	                num++;
	                flag = true;    
	            }
	            
	            for(int d=0;d<3;d++) {
	                while(true) {
	                    int ni = ti+di[d];
	                    int nj = tj+dj[d];
	                
	                    if(ni<0 || ni>=n || nj<0 || nj>=n) break;
	                    
	                    if(map[ni][nj]!=0) break;
	                    
	                    map[ni][nj] = num;
	                    num++;
	                    flag = true;
	                    ti = ni;
	                    tj = nj;
	                }
	                if(!flag)
	                    break;
	            }
	            
	            if(!flag)
	                break;
	            
	        }
	        
	        int idx = 0;
	        int[] answer = new int[num-1];
	        for(int i=0;i<n;i++) {
	            for(int j=0;j<n;j++) {
	                if(map[i][j]!=0) {
	                    answer[idx] = map[i][j];
	                    idx++;
	                }
	            }
	        }
	        
	        
	        return answer;
	    }
	}
}
