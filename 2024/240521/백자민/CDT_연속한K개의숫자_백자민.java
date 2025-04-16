package day240521;

import java.io.*;
import java.util.*;

public class CDT_연속한K개의숫자_백자민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        boolean[] loseNum = new boolean[N+1]; //빠진 숫자를 표시하는 배열. True면 빠졌다는 뜻
        for(int i=0;i<B;i++) {
            int n = Integer.parseInt(br.readLine());
            loseNum[n] = true;
        } //end input

        int[] prefix = new int[N+1]; //빠진 숫자 개수의 누적합을 저장할 배열
        for(int i=1;i<=N;i++) {
            prefix[i] = loseNum[i]?prefix[i-1]+1:prefix[i-1];
        }

        int ans = B;
        K--; //1-index를 사용하므로 인덱스 관리를 위해 K값 조정
        for(int s=1,max=N-K;s<=max;s++) { //구간의 숫자 개수가 K개여야 하므로 N-K+1까지만 탐색 진행
            int e = s+K; //구간의 끝나는 지점
            ans = Math.min(prefix[e]-prefix[s-1], ans);
        }

        System.out.println(ans);


    }
}