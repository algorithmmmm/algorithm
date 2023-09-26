import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16974 {
    static int N, level;
    static long X, answer;
    static long[] total, patty;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        X = Long.parseLong(inArr[1]);
        total = new long[N + 1];
        patty = new long[N + 1];
        total[0] = 1;
        patty[0] = 1;
        for (int i = 1; i < N + 1; i++) {
            // 햄버거 번 + level i-1 버거 + 패티 + level i-1 버거 + 햄버거 번
            total[i] = 1 + total[i - 1] + 1 + total[i - 1] + 1;
            // level i-1 버거의 패티 개수 + 1 + level i-1 버거의 패티 개수
            patty[i] = patty[i - 1] + 1 + patty[i - 1];
        }

        process();
        System.out.println(answer);


    }
    static void process() {
        answer = 0;
        level = N;
        // 앞 부분 먹기
        eat(1);
        if (X == 0) return;
        // 뒷 부분 먹기
        level = N;
        eat(0);
    }
    // n이 0이면 햄버거 번, 1이면 패티
    static void eat(int n) {
        while (level >= 0) {
            if (total[level] > X) { // level 버거 못먹음
                X--;    // level 버거의 햄버거 번 없애기
                level--;
                continue;
            }
            // level 버거 먹을 수 있음
            X -= total[level];
            answer += patty[level];
            if (X == 0) return;
            //
            X -= n;
            answer += n;
            if (X == 0) return;
        }
    }
}
