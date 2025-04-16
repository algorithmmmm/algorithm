import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2179 {
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] strs = new String[N];
        for (int i = 0; i < N; i++) {
            strs[i] = br.readLine();
        }
        ans = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N-1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (strs[i].charAt(0) != strs[j].charAt(0)) continue;
                int chk = check(strs[i], strs[j]);
                if (ans < chk) {
                    ans = chk;
                    sb.setLength(0);
                    sb.append(strs[i]).append("\n").append(strs[j]);
                }
            }
        }
        System.out.println(sb);
    }

    static int check(String a, String b) {
        int M = Math.min(a.length(), b.length());
        if (ans > M) return -1;
        for (int i = 0; i < M; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return i;
            }
        }
        return M;
    }
}