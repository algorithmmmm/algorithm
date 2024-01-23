import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CDT_강력한폭발 {
    static int N, B;
    static int[][] Board;
    static ArrayList<int[]> Bomb;
    static int[] select;
    static int Answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Board = new int[N][N];
        B = 0;  // 폭탄의 개수
        Bomb = new ArrayList<>();   // 각 폭탄의 위치
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                Board[i][j] = Integer.parseInt(inArr[j]);
                if (Board[i][j] == 1) {
                    B++;
                    Bomb.add(new int[]{i, j});
                }
            }
        }
        // end input

        select = new int[B];
        Answer = 0;
        permutation(0);
        System.out.println(Answer);
    }

      static void pretty(boolean[][] visit) {
            System.out.println("---------" + Arrays.toString(select));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j]) {
                    sb.append("*");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void permutation(int cnt) {
        if (cnt == B) {
            // 순열로 결정한 폭탄을 놓기
            Answer = Math.max(process(), Answer);
            // 폭탄 터뜨리키
            return;
        }
        for (int i = 1; i <= 3; i++) {
            select[cnt] = i;
            permutation(cnt + 1);
        }
    }

    static int[][] di = {
            {0, 0, 0, 0},
            {-1, -2, 1, 2},
            {-1, 1, 0, 0},
            {-1, -1, 1, 1},
    };
    static int[][] dj = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, -1, 1},
            {-1, 1, -1, 1},
    };

    // visit : 초토화된 위치, pnt : 폭탄 위치, type : 폭탄 종류
    private static void explode(boolean[][] visit, int[] pnt, int type) {
        for (int k = 0; k < 4; k++) {
            int ni = pnt[0] + di[type][k];
            int nj = pnt[1] + dj[type][k];
            if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
            visit[ni][nj] = true;
        }
    }

    private static int process() {
        boolean[][] visit = new boolean[N][N];
        for (int b = 0; b < B; b++) {
            int[] pnt = Bomb.get(b);
            visit[pnt[0]][pnt[1]] = true;
            explode(visit, pnt, Board[pnt[0]][pnt[1]]);
        }
        return count(visit);
    }

    private static int count(boolean[][] visit) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j]) cnt++;
            }
        }
        return cnt;
    }

}