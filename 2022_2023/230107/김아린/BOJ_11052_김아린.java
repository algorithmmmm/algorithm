import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. dp : 해당 카드 개수로 살 수 있는 금액의 최대값
 * 2. 상향식으로 카드 조합 별로 최대 갱신해나감
 * 	ex) dp[4] = arr[4], dp[4] = dp[1] + dp[3], dp[4] = dp[2] + dp[2]..
 */

public class BOJ_11052_김아린 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 구매하려고 하는 카드의 개수

		int[] arr = new int[N + 1]; // 카드 개수-금액

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1]; //해당 카드 개수로 살 수 있는 금액의 최대값 저장 dp
		
		dp[1] = arr[1];
		
		for(int i = 2; i <= N; i++) { //상향식으로 구함
			dp[i] = arr[i]; //초기값 : i개 들어있는 카드팩의 금액 
			
			for(int j = 1; j < i; j++) { //2 = 1 + 1, 3 = 1 + 2, 2 + 1, 4 = 1 + 3, 2 + 2..
				dp[i] = Math.max(dp[i], dp[j] + dp[i - j]); //i개 들어있는 카드팩을 구매했을 경우와 여러 카드팩을 구매하는 경우 중 최대값 갱신
			}
		}
		
		//출력
		System.out.println(dp[N]);
	}
}
