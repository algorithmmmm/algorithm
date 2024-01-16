import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inArr = br.readLine().split(" ");
        int[] number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(inArr[i]);
        }
        // end input

        Arrays.sort(number);
        int end = N - 1;
        int acc = Integer.MAX_VALUE;
        int[] answer = {0, 0};
        for (int start = 0; start < N - 1; start++) {
            int temp = Math.abs(number[start] + number[end]);
            if (start < end) {
                if (acc > temp) {
                    acc = temp;
                    answer[0] = number[start];
                    answer[1] = number[end];
                }
            }
            while (start < end - 1 && number[start] + number[end] > 0) {
                end--;
                temp = Math.abs(number[start] + number[end]);
                if (acc > temp) {
                    acc = temp;
                    answer[0] = number[start];
                    answer[1] = number[end];
                }
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}