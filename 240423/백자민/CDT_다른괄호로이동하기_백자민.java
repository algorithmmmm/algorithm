package day240423;

import java.util.*;
import java.io.*;

public class CDT_다른괄호로이동하기_백자민 {

    static final int INF = Integer.MAX_VALUE;

    static int N,A,B;

    static char[][] map;
    static int[][] dist;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    static int dijkstra(int si, int sj) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N][N];
        for(int i=0;i<N;i++) {
            Arrays.fill(dist[i], INF);
        }
        
        pq.offer(new Node(si, sj, 0)); //시작 노드
        dist[si][sj] = 0;

        while(!pq.isEmpty()) {
            Node tmp = pq.poll();
            int ti = tmp.i, tj = tmp.j;
            char tmpc = map[ti][tj];

            if(dist[ti][tj]!=tmp.w) continue;
            
            for(int d=0;d<4;d++) {
                int ni = ti+di[d];
                int nj = tj+dj[d];

                if(ni<0 || ni>=N || nj<0 || nj>=N) continue;

                int nw = tmpc==map[ni][nj]?A:B; //이동 시간. 같으면 A, 아니면 B
                nw+=tmp.w;

                if(dist[ni][nj]>nw) {
                    dist[ni][nj] = nw;
                    pq.offer(new Node(ni, nj, nw));
                }
            }
        }

        int res = 0; //시작노드에서 다른 노드까지 걸리는 최소 시간
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                res = Math.max(dist[i][j], res);
            }
        }        

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        for(int i=0;i<N;i++) {
            map[i] = br.readLine().toCharArray();
        } //end input

        //모든 좌표에 대해 해당 좌표를 시작점으로 두고 최소 경로값을 찾아 최댓값 갱신
        int ans = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                ans = Math.max(ans, dijkstra(i,j));
            }
        }

        System.out.println(ans);
    }

    static class Node implements Comparable<Node> {
        int i,j,w;

        public Node(int i, int j, int w) {
            this.i = i;
            this.j = j;
            this.w = w;
        }

        @Override
        public int compareTo(Node n) {
            return this.w-n.w; //비용 기준 오름차순
        }
    }
}
