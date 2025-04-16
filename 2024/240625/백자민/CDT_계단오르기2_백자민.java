package day240624;

import java.util.*;
import java.io.*;

public class CDT_계단오르기2_백자민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] coins = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++) {
            coins[i] = Integer.parseInt(st.nextToken()); 
        }//end input

        int[][] dp = new int[N+1][4]; //i: 계단 층 수, j:지금까지 1계단 오른 횟수
        
        //init
        for(int i=0;i<=N;i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0; //초기상태 동전 개수 = 0개
        dp[1][1] = coins[1]; //1계단 오른 경우 = 1층에 있는 동전을 얻음

        for(int i=2;i<=N;i++) {

            dp[i][0] = dp[i-2][0]<0?dp[i][0]:dp[i-2][0]+coins[i]; //두칸 올라서 동전을 얻은 경우

            for(int j=1;j<4;j++) { //1칸 오르기를 사용한 경우
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-2][j]); //2칸을 오른 경우와 비교해서 최적의 상태 저장
                if(dp[i][j]>-1) {
                    dp[i][j] += coins[i];
                }
            }
        }

        int max = 0;
        for(int j=0;j<4;j++) {
            max = Math.max(max, dp[N][j]);
        }

        System.out.println(max);
    }
}
