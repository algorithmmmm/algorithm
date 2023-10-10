import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9082 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] number = new int[N];  // number[i] : 주위에 있는 지뢰의 숫자
            int[] exit = new int[N];    // exit[i] : -1이면 못 놓는 곳, 1이면 지뢰, 0이면 모름
            char[] chars = br.readLine().toCharArray();
            for (int i = 0; i < N; i++) {
                number[i] = Integer.parseInt(chars[i] + "");
                if (number[i] == 0) {
                    if (i-1 >= 0) exit[i - 1] = -1;
                    exit[i] = -1;
                    if (i+1 < N) exit[i + 1] = -1;
                }
                if (number[i] == 3) {
                    if (i-1 >= 0) exit[i - 1] = 1;
                    exit[i] = 1;
                    if (i+1 < N) exit[i + 1] = 1;
                }
            }
            chars = br.readLine().toCharArray();
            for (int i = 0; i < N; i++) {
                if (chars[i] == '*') {
                    exit[i] = 1;
                }
            }

            for (int i = 0; i < N; i++) {
                if (exit[i] == 1) {
                    if (i-1 >= 0) number[i - 1]--;
                    number[i]--;
                    if (i+1 < N) number[i + 1]--;
                }
            }
            int[] delta = {-1, 0, 1};
            // 이제 지뢰 찾기
            for (int i = 0; i < N; i++) {
                // 이미 지뢰가 있거나 지뢰를 못 놓는 경우
                if (exit[i] == -1 || exit[i] == 1) continue;
                // 지뢰를 놔보자
                boolean no = false;     // no : 지뢰를 놓을 수 있으면 fale, 못 놓으면 true
                for (int j = 0; j < 3; j++) {
                    int value = i + delta[j];
                    if (value < 0 || value >= N) continue;
                    if (number[value] - 1 < 0) {
                        no = true;
                        continue;
                    }
                }
                if (!no) {  // 지뢰 놓기
                    exit[i] = 1;
                    for (int j = 0; j < 3; j++) {
                        int value = i + delta[j];
                        if (value < 0 || value >= N) continue;
                        number[value]--;
                    }
                }
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (exit[i] == 1) cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
