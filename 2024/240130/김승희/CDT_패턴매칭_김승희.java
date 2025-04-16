import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "-" + br.readLine();
        String temp = br.readLine();
        ArrayList<String> pattern = new ArrayList<>();
        pattern.add(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(temp.charAt(0));
        for (int i = 1; i < temp.length(); i++) {
            if (temp.charAt(i) == '*') {
                sb.append(temp.charAt(i));
            } else {
                pattern.add(sb.toString());
                sb = new StringBuilder();
                sb.append(temp.charAt(i));
            }
        }
        pattern.add(sb.toString());

        int sLen = str.length();
        int pLen = pattern.size();
        // dp[i][j] : str을 i번 문자까지 고려했고, pattern을 j까지 고려했을 때, str이 pattern에 속하는가
        boolean[][] dp = new boolean[sLen][pLen];
        Arrays.fill(dp[0], true);
        for (int i = 0; i < sLen; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < sLen; i++) {
            for (int j = 1; j < pLen; j++) {
                // pattern matching true
                String p = pattern.get(j);
                if (str.charAt(i) == p.charAt(0) || p.charAt(0) == '.') {
                    // dp[i-1][j-1]이나 dp[i-1][j] 둘 중에 1개가 true여야 true
                    boolean chk = dp[i - 1][j - 1];

                    if (p.length() == 2 && p.charAt(1) == '*' ) {
                        chk |= dp[i - 1][j];
                    }
                    dp[i][j] = chk;
                }
            }
        }
        System.out.println(dp[sLen - 1][pLen - 1]);
    }
}
/*
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String pattern = br.readLine();
        System.out.println(str.matches(pattern));
    }
}
*/