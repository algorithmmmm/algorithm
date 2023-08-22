import java.util.*;
class Solution {
    static int N, M, K;
    static Tuple Start, End;
    static int[] di = {1, 0, 0, -1};
    static int[] dj = {0, -1, 1, 0};
    static class Tuple{
        int x, y;
        public Tuple(int x, int y){
            this.x = x;
            this.y = y;
        }
        int d;
        public Tuple(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
        
    }
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        N = n; 
        M = m;
        K = k;
        Start = new Tuple(x-1, y-1);
        End = new Tuple(r-1, c-1);
        
        return BFS();
    }
    static String BFS(){
        boolean[][][] visit = new boolean[N][M][K+1];
        Tuple[][][] prev = new Tuple[N][M][K+1];
        Queue<Tuple> Q = new ArrayDeque<>();
        visit[Start.x][Start.y][0] = true;
        Q.add(new Tuple(Start.x, Start.y));
        
        int dist = 0;
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int p = 0; p < size; p++){
                Tuple tp = Q.poll();
                for(int k = 0; k < 4; k++){
                    int ni = tp.x + di[k];
                    int nj = tp.y + dj[k];
                    if (ni < 0 || nj < 0 || ni >= N || nj >= M || visit[ni][nj][dist+1]) continue;
                    visit[ni][nj][dist+1] = true;
                    prev[ni][nj][dist+1] = new Tuple(tp.x, tp.y, k);
                    if(dist+1 == K && ni == End.x && nj == End.y){
                        return answer(prev);
                    }
                    Q.add(new Tuple(ni, nj));
                }
            }
            dist++;
            if(dist == K) break;
        }
        return "impossible";
    }
    static char[] dir = {'d', 'l', 'r', 'u'};
    static String answer(Tuple[][][] prev){
        StringBuffer sb = new StringBuffer();
        int dist = K;
        Tuple tp = prev[End.x][End.y][dist--];
        while(tp != null){
            sb.append(dir[tp.d]);
            tp = prev[tp.x][tp.y][dist--];
        }
        return sb.reverse().toString();
    }
}
