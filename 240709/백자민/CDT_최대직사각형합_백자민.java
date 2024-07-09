package day240708;

import java.util.*;
import java.io.*;

public class CDT_최대직사각형합_백자민 {

    static int N;

    static int[] dp;

    static int[][] map, prefix; //1-index 사용

    static int getMaxSum(int si, int ei) { //si:시작행, ei:끝행 
        int res = Integer.MIN_VALUE;

        //j열마다 si~ei행의 적힌 숫자들을 누적했을 때 최댓값을 찾는다. 
        //dp 배열에 저장된 값 중 가장 큰 값이 구간 내 최댓값
        for(int j=1;j<=N;j++) {
            int tmp = getSum(si,j,ei,j);

            dp[j] = Math.max(tmp, dp[j-1]+tmp);
            res = Math.max(res, dp[j]);
        }

        return res;
    }

    static int getSum(int si, int sj, int ei, int ej) {
        //(si,sj)~(ei,ej) 구간 내 원소 합 리턴
        return prefix[ei][ej] - prefix[si-1][ej] - prefix[ei][sj-1] + prefix[si-1][sj-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1]; //배열 값 저장
        StringTokenizer st;
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } //end input

        //누적합 배열 만들기
        prefix = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + map[i][j];
            }
        }

        int ans = Integer.MIN_VALUE;
        dp = new int[N+1];

        //특정 좌표를 기준으로 만들 수 있는 직사각형 중 최댓값 찾아 갱신
        for(int i=1;i<=N;i++) {
            for(int j=i;j<=N;j++) {
                ans = Math.max(ans, getMaxSum(i,j));
            }
        }

        System.out.println(ans);
    }
}