import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] checkpoint = new int[N][2];
        int acc = 0;
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            checkpoint[i][0] = Integer.parseInt(inArr[0]);
            checkpoint[i][1] = Integer.parseInt(inArr[1]);
            if (i != 0) {
                acc += distance(checkpoint[i], checkpoint[i - 1]);
            }
        }
        // end input
        int answer = acc;
        for (int i = 1; i < N - 1; i++) {
            // i-1 -> i -> i+1
            int a = distance(checkpoint[i], checkpoint[i - 1]) + distance(checkpoint[i], checkpoint[i + 1]);
            // i-1 -> i+1
            int b = distance(checkpoint[i - 1], checkpoint[i + 1]);

            answer = Math.min(answer, acc - a + b);
        }
        System.out.println(answer);
    }

    static int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}