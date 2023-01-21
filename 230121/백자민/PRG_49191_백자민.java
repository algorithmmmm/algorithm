package day230121;

public class PRG_49191_백자민 {
	class Solution {
	    public int solution(int n, int[][] results) {
	    int[][] score =  new int[n][n]; //이김: 1
	    //초기 세팅
	    for(int i=0; i<results.length; i++)
	        score[results[i][0]-1][results[i][1]-1] = 1;
	    
	        
	    for(int i=0;i<n;i++){
	        for(int j=0;j<n;j++){
	            for(int k=0;k<n;k++){
	                if(score[j][i]==1 && score[i][k]==1)
	                    score[j][k] = 1; //1->2, 2->3 이면 1->3 (실력이 좋으면 무조건 이기기 때문)
	            }
	        }
	    }
	    
	    int answer = 0;
	    int temp = 0;
	    for(int i=0;i<n;i++){
	        temp = 0;
	        
	        for(int j=0;j<n;j++){
	            if(score[i][j]==1 || score[j][i]==1) //i번째 선수가 이기거나 진 경우 카운팅
	                temp++;
	        }
	        
	        if(temp==n-1) //이기거나 진 경우가 n-1이면 모든 경기 정보가 있는 경우 
	            answer++;
	    }
	    return answer;
	    }
	}
}
