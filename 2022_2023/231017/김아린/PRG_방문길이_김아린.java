import java.util.*;

class Solution {
    static int[] di = {-1, 1, 0, 0}; //U, D, R, L
    static int[] dj = {0, 0, 1, -1};
    static boolean[][][][] visited;
    
    public int solution(String dirs) {
        char[] chs = dirs.toCharArray();
        visited = new boolean[11][11][11][11]; //가는 길 좌표, 오는 길 좌표
        
        visited[5][5][5][5] = true; //원점
        int i = 5;
        int j = 5;
        
        int count = 0;
        int d = 0;
        for(char dir : chs) {
            switch(dir) { //방향
                case 'U' :
                    d = 0;
                    break;
                case 'D' :
                    d = 1;
                    break;
                case 'R' :
                    d = 2;
                    break;
                case 'L' :
                    d = 3;
                    break;
            }
            
            int ni = i + di[d]; //다음 좌표
            int nj = j + dj[d];
            
            if(0 <= ni && ni < 11 && 0 <= nj && nj < 11) { //좌표 안
                if(!visited[i][j][ni][nj]) { //처음 가봄
                    count++;
                }
                
                //양방향으로 체크
                visited[i][j][ni][nj] = true;
                visited[ni][nj][i][j] = true;
                
                i = ni;
                j = nj;
            }
            
        }
        
        return count;
    }
    
}