import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_최장_공통_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // T : testcase의 수
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            String[] inArr = br.readLine().split(" ");
            sb.append("#").append(t).append(" ").append(LCS(inArr[0], inArr[1])).append("\n");
        }
        System.out.println(sb.toString());
    }

    /**
     *
     * @param A 문자열 A
     * @param B 문자열 B
     * @return A와 B의 최대 공통 부분 수열의 길이
     */
    static int LCS(String A, String B) {
        int[][] dp = new int[A.length()][B.length()];
        for(int i = 0; i < A.length(); i++){
            for(int j = 0; j < B.length(); j++){
                if(i > 0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
                }
                if(j > 0){
                    dp[i][j] = Math.max(dp[i][j-1], dp[i][j]);
                }
                if(A.charAt(i) == B.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = Math.max(1, dp[i][j]);
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j-1] + 1, dp[i][j]);
                    }
                }
            }
        }
        return dp[A.length() - 1][B.length() - 1];
    }
}

/*
5
acaykp capcak
aaaa aaa
abc def
abc dea
acacb bcaca

#1 4
#2 3
#3 0
#4 1
#5 3


3
a a
a aaaa
a b

#1 1
#2 1
#3 0
 */