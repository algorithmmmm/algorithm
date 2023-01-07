package day230107;

import java.util.*;

class Point {
    int i,j;
    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Solution {
    
    static int[] di = {0,0,-1,1};
    static int[] dj = {-1,1,0,0};
    
    static int N,M;
    
    public int solution(int[][] maps) {
        
        N = maps.length; //i
        M = maps[0].length; //j
        
        int[][] visit = new int[N][M]; //maps 방문체크용 배열
        
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0));
        visit[0][0] = 1;
        
        while(!q.isEmpty()) {
            Point temp = q.poll();
            
            for(int d=0;d<4;d++) {
                int ni = temp.i+di[d];
                int nj = temp.j+dj[d];
                
                if(ni<0 || ni>=N || nj<0 || nj>=M) continue; //범위 체크
                if(visit[ni][nj]>0 || maps[ni][nj]==0) continue; // 이미 방문했거나 벽인 경우
                
                visit[ni][nj] = visit[temp.i][temp.j]+1;
                q.add(new Point(ni,nj));
                
            }
        }
        
        int answer = visit[N-1][M-1]==0?-1:visit[N-1][M-1];
        
        return answer;
    }
}
