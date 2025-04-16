import java.util.*;
class Solution {
    static class Point{
        int x, y, chk;
        public Point(int x, int y, int chk){
            this.x = x;
            this.y = y;
            this.chk = chk;
        }
    }
    static int[] di = {0, 0, 1, -1};
    static int[] dj = {1, -1, 0, 0};
    public int solution(int n, int m, int[][] hole) {
        boolean[][] isHole = new boolean[n][m];
        for(int i = 0; i < hole.length; i++){
            isHole[hole[i][0]-1][hole[i][1]-1] = true;
        }
        
        boolean[][][] visit = new boolean[n][m][2];
        Queue<Point> Q = new ArrayDeque<>();
        Q.add(new Point(0, 0, 0));
        visit[0][0][0] = true;
        int dist = 0;
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int s = 0; s < size; s++){
                Point pnt = Q.poll();
                // 신발 사용 안하고 가기
                for(int k = 0; k < 4; k++){
                    int ni = pnt.x + di[k];
                    int nj = pnt.y + dj[k];
                    if(ni < 0 || nj < 0 || ni >= n || nj >= m ||
                       isHole[ni][nj] || visit[ni][nj][pnt.chk]) continue;
                    if(ni == n-1 && nj == m-1) return dist+1;
                    visit[ni][nj][pnt.chk] = true;
                    Q.add(new Point(ni, nj, pnt.chk));
                }
                // 신발 사용 할 수 있는 경우
                if(pnt.chk == 0){
                    // 신발 사용하고 가기
                    int chk = 1;
                    for(int k = 0; k < 4; k++){
                        int ni = pnt.x + di[k] * 2;
                        int nj = pnt.y + dj[k] * 2;
                        if(ni < 0 || nj < 0 || ni >= n || nj >= m ||
                           isHole[ni][nj] || visit[ni][nj][chk]) continue;
                        if(ni == n-1 && nj == m-1) return dist+1;
                        visit[ni][nj][chk] = true;
                        Q.add(new Point(ni, nj, chk));
                    }
                }
            }
            dist++;
        }
        return -1;
    }
}