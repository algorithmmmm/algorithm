import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.TransferQueue;

public class Main {
    static Integer N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);     // N : input 문자 배열의 길이
        K = Integer.parseInt(inArr[1]);     // K : 한번에 뒤집을 수 있는 동전 갯수
        char[] input = br.readLine().toCharArray();     // input : input 문자 배열
        // end input

        int front = 0;      // front : input 배열에서 앞면인 경우(H)
        for (char ch : input) {
            if (ch == 'H') {
                front++;
            }
        }
        if (front == 0){
            System.out.println(0);
            System.exit(0);
        }
        boolean[] visit = new boolean[N + 1];   // 앞면인 동전의 개수는 최소 0개 최대 N개이므로 size는 N+1
        visit[front] = true;
        Queue<Integer> Q = new ArrayDeque<>();
        Q.add(front);
        int cnt = 0;
        int answer = -1;
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int j = 0; j < size; j++){
                int num = Q.poll();
//                System.out.printf("---------- num %d ----------\n", num);
                for (int i = 0; i <= K; i++){   // 앞면인 동전을 i개 뒤집음
                    int nF = num - i;
                    int nB = (N - num) - (K - i);
                    if (nF < 0 || nB < 0) continue;
                    nF += (K - i);
                    if (visit[nF]) continue;
//                    System.out.printf("nF : %d nB : %d\n", nF, nB);
                    if (nF == 0) {
                        answer = cnt+1;
                        continue;
                    }
                    visit[nF] = true;
                    Q.add(nF);
                }
            }
            cnt++;
        }
        System.out.println(answer);
    }
}
