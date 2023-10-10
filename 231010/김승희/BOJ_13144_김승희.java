import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];
        String[] inArr = br.readLine().split(" ");
        int max = 0;
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(inArr[i]);
            max = Integer.max(max, number[i]);
        }
        boolean[] use = new boolean[max + 1];
        int start = 0;
        int end = 0;
        long acc = 0;
        while (end < N) {
            // 방금 추가한 숫자가 이미 사용 중이라면
            if (use[number[end]]) {
                // 해당 숫자 사용 안하기
                while (start <= end) {
                    if (number[end] == number[start]) {
                        start++;
                        break;
                    }
                    use[number[start]] = false;
                    start++;
                }
            }
            // 방금 추가한 숫자 사용
            use[number[end]] = true;
            acc += (end - start) + 1;
            end++;
        }
        System.out.println(acc);
    }
}
