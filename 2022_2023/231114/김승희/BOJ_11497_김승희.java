import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            String[] inArr = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(inArr[i]);
            }
            System.out.println(process(N, arr));
        }
    }

    static int process(int N, int[] arr) {
        int[] answer = new int[arr.length];
        Arrays.sort(arr);
        int idx = 0;
        for (int i = 0; i < N; i += 2) {
            answer[idx++] = arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        for (int j = 1; j < N; j += 2) {
            stack.add(arr[j]);
        }
        while (!stack.isEmpty()) {
            answer[idx++] = stack.pop();
        }

        int ans = Math.abs(answer[0] - answer[N - 1]);
        for (int i = 0; i < N - 1; i++) {
            ans = Math.max(ans, Math.abs(answer[i] - answer[i + 1]));
        }
        return ans;
    }
}