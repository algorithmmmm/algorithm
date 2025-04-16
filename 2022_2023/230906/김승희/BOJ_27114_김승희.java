import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_27114 {
    static int[] Energy, Count;

    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inArr = br.readLine().split(" ");
        int[] temp = new int[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = Integer.parseInt(inArr[i]);
        }
        K = Integer.parseInt(inArr[3]);
        // end input
        init(temp);
        int[] answer = new int[K + 1];
        for (int i = 0; i < 6; i++) {
            if (Energy[i] > K) continue;
            answer[Energy[i]] = answer[Energy[i]] == 0 ? Count[i] : Math.min(Count[i], answer[Energy[i]]) ;
        }
        for (int i = 0; i < K + 1; i++) {
            for (int j = 0; j < 6; j++) {
                int num = i - Energy[j];
                if (num < 0 || answer[num]==0) continue;
                answer[i] = answer[i] == 0 ? answer[num] + Count[j] : Math.min(answer[i], answer[num] + Count[j]);
            }
        }
        System.out.println(answer[K] == 0 ? -1 : answer[K]);
    }
    static void init(int[] temp) {
        Energy = new int[6];
        Count = new int[6];

        // 제자리로 돌아오는 6가지 경우의 수

        // 좌로 90도 4번
        Energy[0] = temp[0] * 4;
        Count[0] = 4;
        // 우로 90도 4번
        Energy[1] = temp[1] * 4;
        Count[1] = 4;
        // 우로 180도 2번
        Energy[2] = temp[2] * 2;
        Count[2] = 2;
        // 좌로 90도 1번 우로 90도 1번
        Energy[3] = temp[0] + temp[1];
        Count[3] = 2;
        // 좌로 90도 2번 우로 180도 1번
        Energy[4] = temp[0] * 2 + temp[2];
        Count[4] = 3;
        // 우로 90도 2번 우로 180도 1번
        Energy[5] = temp[1] * 2 + temp[2];
        Count[5] = 3;

    }
}