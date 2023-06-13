package day0613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_23083_백자민 {
    static long[][] dp;
    static final long MOD = 1000000007;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[N+1][M+1];
        for(int i=0;i<=N;i++)
        	Arrays.fill(dp[i], -1);

        int k = Integer.parseInt(br.readLine());
        for (int i=0;i<k;i++) {
        	st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            dp[x][y] = 0;
        }
        dp[1][1] = 1;
        System.out.println(calc(N, M));
    }

    static long calc(int x, int y) {
        if (x<1 || x>N || y<1 || y>M) {
            return 0;
        }


        if (dp[x][y]!=-1) {
            return dp[x][y];
        }

        if (y%2==0) {
            dp[x][y] = (calc(x-1,y) + calc(x,y-1) + calc(x+1,y-1))% MOD;
        } else {
            dp[x][y] = (calc(x-1,y) + calc(x-1,y-1) + calc(x,y-1))% MOD;
        }

        return dp[x][y];
    }
}
