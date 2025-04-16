import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int LEN = 6;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        count = new int[LEN+1];   // count[i] : i * i 크기 색종이의 개수
        for (int i = 0; i < LEN; i++) {
            count[i + 1] = Integer.parseInt(br.readLine());
        }
        // 6크기 색종이
        int cnt = count[LEN]; // count에 있는 색종이를 6 * 6 크기의 판에 채우기 위해 필요한 6 * 6 크기의 판의 최소 개수
        // 5크기 색종이
        cnt += count[5];
        count[1] = Math.max(0, count[1] - count[5] * 11);
        // 4 크기 색종이 -> 4 * 4 * 1 + 2 * 2 * 5
        cnt += count[4];
        int temp = count[4] * 5;
        if (temp > count[2]) {
            temp -= count[2];
            count[1] = Math.max(0, count[1] - temp * 4);
            count[2] = 0;
        } else {
            count[2] -= temp;
        }
        // 3 크기 색종이
        cnt += count[3] / 4;
        count[3] %= 4;
        int[] max2 = {0, 5, 3, 1};
        if (count[3] != 0) {
            if (count[2] < max2[count[3]]) {
                count[1] = Math.max(0, count[1] - (max2[count[3]] - count[2]) * 4);
                count[2] = 0;
            }else{
                count[2] -= max2[count[3]];
            }
            int notFill = 36 - count[3] * 9 - max2[count[3]] * 4;
            count[1] = Math.max(0, count[1] - notFill);
            cnt++;
            count[3] = 0;
        }
        // 2 크기 색종이
        cnt += count[2] / 9;
        count[2] %= 9;
        if (count[2] != 0) {
            count[1] = Math.max(0, count[1] - (9 - count[2]) * 4);
            cnt++;
            count[2] = 0;
        }
        // 1 크기 색종이
        cnt += count[1] % 36 == 0 ? count[1] / 36 : count[1] / 36 + 1;
        System.out.println(cnt);
    }
}