package day240611;

import java.util.*;

public class CDT_편집거리_백자민 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] A = sc.next().toCharArray();
        char[] B = sc.next().toCharArray();

        int N = A.length;
        int M = B.length;

        int[][] dp = new int[N+1][M+1];

        //초기화
        for(int i=1;i<=N;i++) {
            dp[i][0] = i;
        }
        for(int j=1;j<=M;j++) {
            dp[0][j] = j;
        }

        //dp 배열 채우기
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                if(A[i-1]==B[j-1]) { //두 문자가 같은 경우에는 별도 연산이 필요하지 않음 
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    //두 문자가 다른 경우에는 삽입/삭제/수정 중 한가지 방법을 선택해서 연산을 수행해줘야 함.
                    //최소 연산은 이전까지 가장 적은 연산을 수행했던 상태에서 삽입/삭제/수정 연산을 수행하는 것
                    int min = Math.min(dp[i][j-1], dp[i-1][j]); //삽입&삭제 중 더 적은 연산 횟수 고르기
                    min = Math.min(dp[i-1][j-1], min); //수정 연산 횟수와도 비교

                    dp[i][j] = min+1; //현재 연산을 한 번 더 수행하므로 +1값을 저장
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}
