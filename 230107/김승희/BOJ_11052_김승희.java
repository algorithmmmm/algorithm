import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

/*
* 다이나믹 프로그래밍으로 풀 수 있다.
* dp[i] : i개의 카드를 뽑는데 드는 최대 금액
* dp[i] : max(dp[1] + dp[i-1], dp[2] + dp[i-2], ... , dp[i/2] + dp[i-i/2], prices[i])
*
* N의 최대 수가 1000이기 때문에
* 최대 금액은 1,000 * 10,000 = 10,000,000 이기 때문에 int 형 사용
*
* dp[1000]을 구하기 위해서
* 500의 연산 필요
* dp 배열의 값을 구할 때 모두 500의 연산을 한다고 가정하면
* 500 * 1,000 = 500,000의 연산 필요.
* */
public class BOJ11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());       // N : 카드의 개수
        String[] inArr = br.readLine().split(" ");
        int[] prices = new int[N+1];    // prices[i] : i개의 카드가 들은 카드팩의 가격
        for(int i = 0; i < N; i++){
            prices[i+1] = Integer.parseInt(inArr[i]);
        }

        int[] dp = new int[N+1];    // dp[i] : i개의 카드를 뽑는데 드는 최대 금액
        dp[1] = prices[1];

        for(int i = 2; i <= N; i++){
            for(int j = 1; j <= i / 2; j++){
                dp[i] = Math.max(dp[i], dp[j] + dp[i-j]);
            }
            dp[i] = Math.max(dp[i], prices[i]);
        }
        System.out.println(dp[N]);
    }
}
/*
5
1 10 1 1 1
21
-----------------
6
3 4 2 8 4 20
20

6
3 4 2 8 4 10
18

* */