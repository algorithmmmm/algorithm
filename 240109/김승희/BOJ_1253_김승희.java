import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class BOJ_1253 {
    static int N;
    static int[] number;
    static HashMap<Integer, Integer> map;   // key : 숫자, value : 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] inArr = br.readLine().split(" ");
        map = new HashMap<>();
        number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i]  = Integer.parseInt(inArr[i]);
            map.put(number[i], map.getOrDefault(number[i], 0) + 1);
        }
        Arrays.sort(number);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (isGood(i)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    // idx번 숫자가 좋은 숫자인가?
    static boolean isGood(int idx) {
        for (int i = 0; i < N; i++) {
            // 지금 확인해야 할 숫자로 덧셈을 하면 안됨
            if (idx == i) continue;
            int num = number[idx] - number[i];
            int get = map.getOrDefault(num, 0);
            if (num == number[idx] && num == number[i]) {
                if (get >= 3) return true;
            } else if (num == number[idx] || num == number[i]) {
                if (get >= 2) return true;
            } else {
                if (get > 0) return true;
            }
        }
        return false;
    }
}
