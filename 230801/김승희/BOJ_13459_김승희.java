import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class BOJ_13459 {
    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static boolean endOfGame;
    static Point[] Beads, beads;
    // 아래, 위, 오, 왼
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static char[][] board;
    static final int COUNT = 10;
    static int[] select;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);     // N : board의 세로 길이
        M = Integer.parseInt(inArr[1]);     // M : board의 가로 길이
        board = new char[N][];
        Beads = new Point[2];   // 0번 구슬은 빨강, 1번 구슬은 파랑
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    Beads[0] = new Point(i, j);
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    Beads[1] = new Point(i, j);
                    board[i][j] = '.';
                }
            }
        }
        //end input
        select = new int[COUNT];
        endOfGame = false;      // game이 끝났는지
        permutation(0);
        System.out.println(endOfGame ? 1 : 0);
    }
    static void permutation(int cnt){
        if (endOfGame) return;
        if (cnt == COUNT) {     // 모두 구했음
            if (game()) {       // 만약 빨강이 먼저 나간다면 game 끝내기
                endOfGame = true;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {   // 중복 순열
            select[cnt] = i;
            permutation(cnt+1);
        }
    }
    static boolean game(){
        beads = new Point[2];
        beads[0] = new Point(Beads[0].x, Beads[0].y);
        beads[1] = new Point(Beads[1].x, Beads[1].y);

        // num은 움직이는 방향
        for (int num : select) {
            int f = first(num);     // 먼저 움직여야 하는 구슬 인덱스
            int s = f == 0 ? 1 : 0; // f 말고 다른 구슬의 인덱스
            boolean[] exit = new boolean[2];
            // 움직여
            exit[f] = ULDR(f, num);
            exit[s] = ULDR(s, num);
            if (exit[0]){   // 빨강이 탈출
                return !exit[1];    // 파랑이 탈출했으면 F, 파랑이 탈출 안했으면 T
            }
            if (exit[1]){   // 파랑이 탈출 -> 빨강이 탈출 안했는데 파랑이 탈출한거니까 F
                return false;
            }
        }
        return false;   // 빨간 구슬이 탈출하지 못했음
    }

    // idx번 구슬을 k방향으로 움직이기
    static boolean ULDR(int idx, int k) {
        int chk = idx == 0 ? 1 : 0;
        int nx = beads[idx].x;
        int ny = beads[idx].y;
        while (true) {
            nx += di[k];
            ny += dj[k];
            // 범위가 벗어났거나, 벽을 만난 경우 -> 이전 위치 기록
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || board[nx][ny] == '#') {
                beads[idx].x = nx - di[k];
                beads[idx].y = ny - dj[k];
                return false;
            }
            // 탈출구에 도착한 경우
            if (board[nx][ny] == 'O') {
                beads[idx].x = nx;
                beads[idx].y = ny;
                return true;
            }
            // 다른 구슬을 만난 경우 -> 이전 위치 기록
            if (nx == beads[chk].x && ny == beads[chk].y) {
                beads[idx].x = nx - di[k];
                beads[idx].y = ny - dj[k];
                return false;
            }
        }
    }

    // k방향으로 움직였을 때 빨간공과 파란공 중 먼저 움직여야 하는 것
    // x * di[k] + y * dj[k] 의 값이 더 큰 값 먼저 움직여야 함
    // 어차피 둘 중에 하나는 0이 된다.
    // 왼쪽 위쪽ㅇ로 가는 경우는 음수 -> 인덱스가 작은게 먼저 움직여야 함. 음수니까 그냥 큰거 먼저
    // 오른쪽 아래쪽으로 가는 경우는 양수 -> 인덱스가 큰 게 먼저 움직여야 함
    static int first(int k) {
        int red = beads[0].x * di[k] + beads[0].y * dj[k];
        int blue = beads[1].x * di[k] + beads[1].y * dj[k];
        if (red > blue) {
            return 0;
        }
        return 1;
    }
}
/*
5 6
######
#....#
#....#
#R.BO.#
######

0
-----------------
 */