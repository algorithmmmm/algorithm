package day240305;

import java.io.*;
import java.util.*;

public class CDT_격자칠하기2_백자민 {

    static int N;

    static int[][] map;
    static boolean[][] visit;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    static int coloring(int si, int sj, int D) {
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[] {si, sj});
        visit[si][sj] = true;

        int cnt=1;
        while(!q.isEmpty()) {
            int ti = q.peek()[0];
            int tj = q.poll()[1];

            for(int d=0;d<4;d++) {
                int ni = ti+di[d];
                int nj = tj+dj[d];

                if(ni<0 || ni>=N || nj<0 || nj>=N) continue;
                if(visit[ni][nj]) continue;
                if(Math.abs(map[ti][tj]-map[ni][nj])>D) continue; 

                q.offer(new int[] {ni,nj});
                visit[ni][nj] = true;
                cnt++;
            }
        }

        return cnt;
    }

    static boolean isHalf(int D) {
        visit = new boolean[N][N];
        int cnt = 0;
        
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(visit[i][j]) continue;
                int tmp = coloring(i, j, D);
                cnt = Math.max(cnt, tmp);
            }
        }

        if(cnt>=(N*N+1)/2) 
            return true;
        
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        StringTokenizer st;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } //end input

        int l=0,r=1_000_000;
        int ans = Integer.MAX_VALUE;
        while(l<=r) {
            int mid = (l+r)/2;
            if(isHalf(mid)) {
                r = mid-1;
                ans = Math.min(ans, mid);
            } else {
                l = mid+1;
            }
        }

        System.out.println(ans);
    }
}
