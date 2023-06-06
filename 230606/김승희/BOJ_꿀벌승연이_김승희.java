import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    // 오른 위, 오른 아래, 아래
    static int[][] di = {
            {-1, 0, 1}, // 열이 짝수일 경우 (그림과 반대임. zero-index로 바꿔서 풀었기 때문)
            {0, 1, 1}   // 열이 홀수일 경우
    };
    static int[] dj = {1, 1, 0};
    static int N, M;        // N행 M열
    static int pivot = 1000_000_000 + 7;
    static boolean[][] Map; // Map[i][j] : true면 (i, j) 칸이 구멍

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);

        Map = new boolean[N][M];
        int num = Integer.parseInt(br.readLine());  // num : 구멍의 개수
        for(int i = 0; i < num; i++){
            inArr = br.readLine().split(" ");
            // zero index로 바꿔서 true로
            Map[Integer.parseInt(inArr[0])-1][Integer.parseInt(inArr[1])-1] = true;     
        }
        // end input

        System.out.println(BFS());
    }

    static int BFS(){
        // 오른 위, 오른 아래, 아래로 가기 때문에 방문체크는 따로 할 필요가 없다.
        int answer = 0;
        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{0, 0});
        while (!Q.isEmpty()) {
            int[] pop = Q.poll();

            for (int k = 0; k < dj.length; k++) {
                int ni = di[1][k] + pop[0];

                if(pop[1] % 2 == 0){
                    ni = di[0][k] + pop[0];
                }
                int nj = dj[k] + pop[1];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M || Map[ni][nj]) {
                    continue;
                }
                if (ni == N - 1 && nj == M - 1) {
                    answer = (answer + 1) % pivot;
                    continue;
                }
                Q.add(new int[]{ni, nj});
            }
        }
        return answer;
    }
}