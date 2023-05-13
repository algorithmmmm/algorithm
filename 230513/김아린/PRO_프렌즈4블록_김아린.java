/*
1. 다 탐색해서 지울 블록 체크
2. 지울 수 있는 블럭 카운팅하기
3. 블록 내리기
4. 반복하기
*/

import java.util.*;

class Solution {
    static boolean[][] checked; //지울 수 있는 블록 체크 배열
    static int answer;
    
    public int solution(int m, int n, String[] board) {
    	char[][] arr = new char[m][n];
        checked = new boolean[m][n];
        answer = 0;
        
        //입력값을 배열에 저장하기
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = board[i].charAt(j);
            }
        }
        
        //지울 블록이 없을 때까지
        while(!check(m, n, arr)) { //1. 지워지는 블록 채크하고, 2. 개수 세서 지울 블록있는지 판별  
            //3. 블록 내리기
        	char[][] tmp = new char[m][n];
        	
            for(int i = 0; i < n; i++){
                List<Character> list = new ArrayList<>(); //지워지지 않을 블록 저장
                
                //지워지지 않을 블록 저장해서
                for(int j = m - 1; j >= 0; j--){
                    if(!checked[j][i]) {
                        list.add(arr[j][i]);
                    }
                }
                
                //새 배열에 옮기기
                int idx = m - 1;
                for(char ch : list) {
                	tmp[idx][i] = ch;
                    idx--;
                }
            }
            
            //배열 복사하기
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                	arr[i][j] = tmp[i][j];
                }
            }
            
        }
        
        return answer;
    }
    
    public static boolean check(int m, int n, char[][] arr) { //더 이상 지울 블록이 있는지 체크하는 함수
    	checked = new boolean[m][n];
    	
    	for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char ch = arr[i][j];
                
                //null이 아니고 4블록을 만족하면, 지울 블록으로 체크하기
                if(0 <= i && i < m && 0 <= i + 1 && i + 1 < m && 0 <= j && j < n && 0 <= j + 1 && j + 1 < n) {
                	if(ch != 0 && ch == arr[i][j + 1] && ch == arr[i + 1][j] && ch == arr[i][j + 1] && ch == arr[i + 1][j + 1]) {
                		checked[i][j] = true;
                        checked[i][j + 1] = true;
                        checked[i + 1][j] = true;
                        checked[i + 1][j + 1] = true;
                    }
                }
            } 
        }
        
        //지울 블록 개수 세서 결과 반환	
        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(checked[i][j]) {
                    count++;
                }
            }
        }
        
        answer += count; //지워지는 블록 개수 정답에 누적시키기
        
        return (count == 0) ? true : false;
    }
}