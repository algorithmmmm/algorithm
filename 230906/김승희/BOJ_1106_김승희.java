import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int Goal = Integer.parseInt(inArr[0]);
        int N = Integer.parseInt(inArr[1]);
        int[] people = new int[1100];
        for (int i = 0; i < 1100; i++) {
            people[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            int cost = Integer.parseInt(inArr[0]);
            int num = Integer.parseInt(inArr[1]);
            int acc = 0;
            for (int j = num; j < 1100; j += num) {
                people[j] = Math.min(people[j], cost+acc);
                acc += cost;
            }
        }
        if (people[1] != Integer.MAX_VALUE) {
            people[2] = Math.min(people[2], people[1] + people[1]);
        }
        for (int i = 3; i < 1100; i++) {
            int idx = i / 2;
            for (int j = 1; j <= idx; j++) {
                if (people[j] == Integer.MAX_VALUE || people[i-j] == Integer.MAX_VALUE) continue;
                people[i] = Math.min(people[i], people[j] + people[i - j]);
            }
        }
        int Ans = Integer.MAX_VALUE;
        for (int i = Goal; i < 1100; i++) {
            if (people[i] == Integer.MAX_VALUE) continue;
            Ans = Math.min(Ans, people[i]);
        }
        System.out.println(Ans);
    }
}
