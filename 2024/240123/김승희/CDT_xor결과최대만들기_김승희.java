import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, Answer;
    static int[] Num, select;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        Num = new int[N];
        inArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            Num[i] = Integer.parseInt(inArr[i]);
        }
        // end input
        select = new int[M];
        Answer = Integer.MIN_VALUE;
        combination(0, 0);
        System.out.println(Answer);
    }

    static void combination(int idx, int cnt) {
        if (cnt == M) {
            Answer = Math.max(Answer, xorOp());
            return;
        }
        for (int i = idx; i < N; i++) {
            select[cnt] = i;
            combination(i + 1, cnt + 1);
        }
    }
    static int xorOp() {
        int ans = Num[select[0]];
        for (int i = 1; i < M; i++) {
            ans ^= Num[select[i]];
        }
        return ans;
    }
}