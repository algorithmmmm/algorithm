package day0815;
import java.util.*;

public class PRG_86052_백자민 {

	class Solution {
	    
	    static int[] di = {-1,0,1,0}; //12시 방향부터 시계방향 
	    static int[] dj = {0,1,0,-1}; 
	    
	    static boolean[][][] check; //4방향의 방문 여부를 체크할 배열
	    
	    static ArrayList<Integer> answer;
	    
	    static void light(int i, int j, int d, int isize, int jsize, String[] grid) {
	        int cnt=0;
	        
	        while(!check[i][j][d]) {
	            cnt++;
	            check[i][j][d] = true;
	            
	            if(grid[i].charAt(j)=='L') {
	                d = (d+3)%4;
	            } else if(grid[i].charAt(j)=='R') {
	                d = (d+1)%4;
	            }
	            
	            i = (i+di[d]+isize)%isize;
	            j = (j+dj[d]+jsize)%jsize;
	        }
	        
	        answer.add(cnt);
	    }
	    
	    public int[] solution(String[] grid) {
	        answer = new ArrayList<>();
	        check = new boolean[grid.length][grid[0].length()][4];
	        
	        //모든 좌표에 대해서 쏠 수 있는 방향은 다 쏴보기 
	        for(int i=0,isize=grid.length;i<isize;i++) {
	            for(int j=0,jsize=grid[0].length();j<jsize;j++) {
	                for(int d=0;d<4;d++) {
	                    if(!check[i][j][d])
	                        light(i,j,d, isize, jsize, grid);
	                }
	            }
	        }
	        
	        Collections.sort(answer);
	        return answer.stream().mapToInt(i -> i).toArray();
	    }
	}
}
