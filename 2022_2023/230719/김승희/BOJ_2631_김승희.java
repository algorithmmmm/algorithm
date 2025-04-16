import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // N : 아이들의 수
        int[] children = new int[N];    // children[i] : i번째 위치에 있는 아이
        int[] DP = new int[N];          // DP[i] : i번을 마지막 원소로 하는 최장 증가 부분 수열의 길이
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
            DP[i] = 1;
        }
        // end input

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++){
                if (children[j] < children[i]){     // j번째 친구가 i번째 친구보다 작다면
                    // j번을 마지막으로 하는 최장 증가 부분 수열에 children[i]를 붙일 수 있으므로
                    // 기존 DP[i]값과 DP[j] + 1 값 중 큰 값이 DP[i] 의 값이 된다.
                    DP[i]= Math.max(DP[i], DP[j]+1);
                }
            }
        }
        int cnt = 0;
        for (int j = 0; j < N; j++){
            cnt = Math.max(cnt, DP[j]);
        }
        System.out.println(N - cnt);
    }
}