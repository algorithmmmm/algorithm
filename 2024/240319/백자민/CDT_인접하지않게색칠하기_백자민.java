package day240319;

import java.util.*;
import java.io.*;

public class CDT_인접하지않게색칠하기_백자민 {

    static int N,K;

    static int[] nodes; //각 노드들의 값 저장
    static boolean[] visit;

    static int[][][] dp; //1: 노드번호, 2: 색칠한 노드 개수(서브트리에서), 3: 현재 노드 색칠 여부

    static ArrayList<Integer>[] tree;

    static void dfs(int tmp) {
        //초기값
        dp[tmp][0][0] = 0; //현재 노드를 색칠하지 않을 때
        dp[tmp][1][1] = nodes[tmp]; //현재 노드를 최초로 색칠했을 때

        for(int next: tree[tmp]) {
            if(visit[next]) continue;

            visit[next] = true;
            dfs(next);

            //색칠하지 않는 경우
            for(int k=K;k>=0;k--) {
                for(int i=0;i<=k;i++) {
                    int maxVal = Math.max(dp[next][i][0], dp[next][i][1]);
                    dp[tmp][k][0] = Math.max(dp[tmp][k][0], dp[tmp][k-i][0]+maxVal);
                }
            }

            //색칠하는 경우
            for(int k=K;k>=1;k--) {
                for(int i=1;i<=k;i++) {
                    dp[tmp][k][1] = Math.max(dp[tmp][k][1], dp[tmp][k-i][1]+dp[next][i][0]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree[from].add(to);
            tree[to].add(from);
        }

        nodes = new int[N+1];
        for(int i=1;i<=N;i++) {
            nodes[i] = Integer.parseInt(br.readLine());
        }

        K = Integer.parseInt(br.readLine());
        //end input

        dp = new int[N+1][K+1][2];
        visit = new boolean[N+1];

        visit[1] = true;
        dfs(1);

        int ans = 0;
        for(int k=0;k<=K;k++) {
            ans = Math.max(dp[1][k][0], ans);
            ans = Math.max(dp[1][k][1], ans);
        }

        System.out.println(ans);
    }
}
