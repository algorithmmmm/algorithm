import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int N = Integer.parseInt(inArr[0]);
        int M = Integer.parseInt(inArr[1]);
        int H = Integer.parseInt(inArr[2]);
        ArrayList<Integer>[] numbers = new ArrayList[N+1];
        numbers[0] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers[i+1] = new ArrayList<>();
            inArr = br.readLine().split(" ");
            for (int j = 0; j < inArr.length; j++) {
                numbers[i+1].add(Integer.parseInt(inArr[j]));
            }
        }
        // end input

        // dp[i][j] : i번 학생의 블록까지 사용했을 때, j를 만들 수 있는 경우의 수
        int[][] dp = new int[N+1][H + 1];
        dp[0][0] = 1;   // 0은 원래 있는 거니까 1
        for (int i = 1; i < N+1; i++) {     // 학생 수만큼
            dp[i] = deepcopy(dp[i - 1]);
            for (int j = 0; j < numbers[i].size(); j++) {       // 해당 학생이 가진 블록만큼
                for (int k = 0; k <= H; k++) {      // H 까지
                    if (dp[i-1][k] == 0) continue;  // 이전에 만들 수 있는 경우의 수가 없으면 continue
                    int num = numbers[i].get(j);
                    if (num + k > H) break;     // 학생이 가진 블록과 k를 합친 높이가 H보다 크면 더 이상 볼 필요 없음
                    // k에 num을 더해서 만들 수 있음 -> dp[i-1][k] += dp[i-1][k]
                    dp[i][num + k] = (dp[i][num + k] + dp[i - 1][k]) % 10007;
                }
            }
        }
        System.out.println(dp[N][H]);
    }

    static int[] deepcopy(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }
}