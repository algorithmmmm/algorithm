import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            int[] answer = process(str, K);
            if (answer[0] == Integer.MAX_VALUE || answer[1] == Integer.MIN_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static int[] process(String str, int K) {
        // K개를 포함한 가장 짧은 연속 문자열 길이, K개를 포함하고 첫글자 = 마지막글자가 그 문자고 가장 긴 문자열 길이
        int[] answer = {Integer.MAX_VALUE, Integer.MIN_VALUE};

        Deque<Integer>[] alpha = new Deque[26];
        for (int i = 0; i < 26; i++) {
            alpha[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i) - 'a';
            alpha[ch].add(i);
            if (alpha[ch].size() < K) continue;
            if (alpha[ch].size() > K) alpha[ch].pollFirst();

            int start = alpha[ch].peekFirst();
            int end = alpha[ch].peekLast();
            answer[0] = Math.min(answer[0], end - start + 1);
            answer[1] = Math.max(answer[1], end - start + 1);

        }

        return answer;
    }
}