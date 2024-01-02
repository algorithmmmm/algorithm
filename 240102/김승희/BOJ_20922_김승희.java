import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int N = Integer.parseInt(inArr[0]);     // N : 수열의 길이
        int K = Integer.parseInt(inArr[1]);     // K : 같은 정수의 개수 조건

        int[] number = new int[N];
        inArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(inArr[i]);
        }
        System.out.println(process(N, K, number));
    }

    static int process(int N, int K, int[] number) {
        int answer = 0;
        int start = 0;
        int end = 0;
        HashMap<Integer, Integer> map = new HashMap<>();    // key : 원소, value : 개수
        while (end < N) {
            int temp = map.getOrDefault(number[end], 0);
            map.put(number[end], temp + 1);
            if (temp + 1 > K) { // number[end]가 K개보다 많이 들어 있음
                while (true) {
                    // number[start]의 개수를 1개 줄이기
                    map.put(number[start], map.get(number[start]) - 1);
                    if (number[start] == number[end]) {
                        start++;
                        break;
                    }
                    start++;
                }
            } else {
                answer = Math.max(answer, end - start + 1);
            }
            end++;
        }
        return answer;
    }
}
