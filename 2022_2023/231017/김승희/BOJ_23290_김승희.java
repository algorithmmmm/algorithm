import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_23290 {
    static final int N = 4;
    static int[] Shark;
    static int[][] Smell;
    static long[][][] Board;
    static Queue<long[]> Fish;

    static int[] fi = {0, - 1, -1, -1, 0, 1, 1, 1};
    static int[] fj = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int M = Integer.parseInt(inArr[0]);     // M : 물고기의 수
        int S = Integer.parseInt(inArr[1]);     // S : 연습 횟수

        Board = new long[N][N][8];       // Board[i][j][d] : (i, j)위치에 d방향으로 있는 물고기의 마릿수
        Smell = new int[N][N];          // Smell[i][j] : (i, j) 위치에 남아있는 냄새

        for (int i = 0; i < M; i++) {
            inArr = br.readLine().split(" ");
            int x = Integer.parseInt(inArr[0]) -1;
            int y = Integer.parseInt(inArr[1]) -1;
            int d = Integer.parseInt(inArr[2]) -1;
            Board[x][y][d]++;
        }
        inArr = br.readLine().split(" ");
        Shark = new int[]{Integer.parseInt(inArr[0]) - 1, Integer.parseInt(inArr[1]) - 1};
        // end input

        for (int no = 0; no < S; no++) {
            getFish();
            moveFish();
            moveShark();
            removeSmell();
            copyFish();
        }
        long answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += countFish(i, j);
            }
        }
        System.out.println(answer);
    }
    
    static void getFish() {
        Fish = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 8; k++) {
                    if (Board[i][j][k] > 0)
                        Fish.add(new long[]{i, j, k, Board[i][j][k]});   // (i, j)위치의 k방향의 Board[i][j][k] 마릿수 복제
                }
            }
        }
    }
    static void moveFish() {
        long[][][] board = new long[N][N][8];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d = 0; d < 8; d++) {
                    if (Board[i][j][d] == 0) continue;
                    boolean flag = false;
                    for (int k = 0; k < 8; k++) {
                        int nd = (8 + d - k) % 8;   // 이동 방향
                        int ni = i + fi[nd];
                        int nj = j + fj[nd];
                        if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                        if (ni == Shark[0] && nj == Shark[1]) continue;
                        if (Smell[ni][nj] > 0) continue;
                        board[ni][nj][nd] += Board[i][j][d];
                        flag = true;
                        break;
                    }
                    if (!flag) board[i][j][d] += Board[i][j][d];
                }
            }
        }
        Board = board;
    }

    static long Feed;
    static int[] select, temp;
    static void moveShark() {
        Feed = Long.MIN_VALUE;
        select = new int[3];
        temp = new int[3];
        permutation(0);
        // 상어 움직이기~
        for (int d = 0; d < 3; d++) {
            Shark[0] += di[select[d]];
            Shark[1] += dj[select[d]];
            if (countFish(Shark[0], Shark[1]) == 0) continue;
            Board[Shark[0]][Shark[1]] = new long[8];
            Smell[Shark[0]][Shark[1]] = 3;
        }
    }

    static void permutation(int cnt) {
        if (cnt == 3) {
            long feed = 0;
            int[] shk = {Shark[0], Shark[1]};
            boolean[][] visit = new boolean[N][N];
            for (int i = 0; i < 3; i++) {
                int ni = shk[0] + di[temp[i]];
                int nj = shk[1] + dj[temp[i]];
                if (ni < 0 || nj < 0 || ni >= N || nj >= N) return;
                shk[0] = ni;
                shk[1] = nj;
                if (!visit[ni][nj]) feed += countFish(ni, nj);
                visit[ni][nj] = true;
            }
            if (feed > Feed) {
                Feed = feed;
                for (int i = 0; i < 3; i++) {
                    select[i] = temp[i];
                }
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            temp[cnt] = i;
            permutation(cnt + 1);
        }
    }
    static void removeSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Smell[i][j] = Integer.max(Smell[i][j] - 1, 0);
            }
        }
    }

    static void copyFish() {
        while (!Fish.isEmpty()) {
            long[] fish = Fish.poll();
            Board[(int)fish[0]][(int)fish[1]][(int)fish[2]] += fish[3];
        }
    }

    static long countFish(int i, int j) {
        long cnt = 0;
        for (int d = 0; d < 8; d++) {
            cnt += Board[i][j][d];
        }
        return cnt;
    }

}