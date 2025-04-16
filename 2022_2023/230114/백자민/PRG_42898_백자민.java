package day230114;

public class PRG_42898_백자민 {
    public int solution(int m, int n, int[][] puddles) {
        int[][] board = new int[n][m];
        board[0][0] = 1;
        
        for(int p=0,size=puddles.length;p<size;p++) {
            board[puddles[p][1]-1][puddles[p][0]-1] = -1;
        }
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(board[i][j]<0) continue; //웅덩이 피하기
                if(j>0 && board[i][j-1]>0) board[i][j] += board[i][j-1] % 1_000_000_007; //왼쪽으로 오는 경우
                if(i>0 && board[i-1][j]>0) board[i][j] += board[i-1][j] % 1_000_000_007; //위쪽으로 오는 경우
            }
        }
        
        return board[n-1][m-1] % 1_000_000_007;
    }
}
