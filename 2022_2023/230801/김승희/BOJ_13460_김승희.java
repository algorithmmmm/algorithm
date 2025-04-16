import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13460 {
    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, ANS;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);     // N : 세로길이
        M = Integer.parseInt(inArr[1]);     // M : 가로길이
        board = new char[N][M];
        Point[] beads = new Point[2];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R'){
                    beads[0] = new Point(i, j);
                }else if(board[i][j] == 'B'){
                    beads[1] = new Point(i, j);
                }
            }
        }
        // end input

        ANS = Integer.MAX_VALUE;    // ANS : 빨간 구슬이 탈출하는 최소 움직임 횟수
        for (int k = 0; k < 4; k++) {
            // 4가지 방향을 각각 시작으로 하는 recursion 함수 호출
            recursion(k, 0, deepcopy(beads));
        }
        // 한번도 update 되지 않았다면 10번 안에 빨간 구슬이 탈출하지 못한 것
        System.out.println(ANS == Integer.MAX_VALUE ? -1 : ANS);

    }

    /**
     *
     * @param k     : 이동하는 방향
     * @param cnt   : 현재 이동 횟수
     * @param pnts  : pnts[0] - 빨간 구슬 위치, pnts[1] - 파란 구슬 위치
     */
    static void recursion(int k, int cnt, Point[] pnts){
        if (cnt == 10) return;      // 10번 이상 할 필요 없음
        int f = first(k, pnts);     // f : 먼저 움직여야 하는 구슬 인덱스
        int s = f == 0 ? 1 : 0;     // s : 그 다음에 움직여야 하는 구슬 인덱스
        // 구슬 움직이기
        pnts = UDLR(k, f, pnts);
        pnts = UDLR(k, s, pnts);

        // 파란 구슬이 빠져나옴 -> 더 이상 재귀 진행 할 필요 없음
        if (board[pnts[1].x][pnts[1].y] == 'O') return;
        // 빨간 구슬이 빠져나옴 -> 더 이상 재귀 진행 할 필요 없음 + 정답 업데이트
        if (board[pnts[0].x][pnts[0].y] == 'O') {
            ANS = Math.min(ANS, cnt+1);
            return;
        }
        // 현재 진행한 방향 말고 다른 방향들로 recursion 함수 재귀 호출
        for (int p = 0; p < 4; p++) {
            if (k == p) continue;
            recursion(p, cnt+1, deepcopy(pnts));
        }

    }

    /**
     *
     * @param k     : 이동하는 방향
     * @param idx   : 움직일 구슬 인덱스
     * @param pnts  : pnts[0] - 빨간 구슬 위치, pnts[1] - 파란 구슬 위치
     * @return
     */
    static Point[] UDLR(int k, int idx, Point[] pnts) {
        int chk = idx == 0 ? 1 : 0;     // chk : 현재 움직이는 구슬 말고 다른 구슬의 인덱스
        int nx = pnts[idx].x;
        int ny = pnts[idx].y;
        while(true){
            nx += di[k];
            ny += dj[k];
            // 범위를 벗어나거나 벽을 만났다면 그 전 위치를 기록
            if(nx < 0 || ny < 0 || nx >= N || ny >= M || board[nx][ny]=='#'){
                pnts[idx].x = nx - di[k];
                pnts[idx].y = ny - dj[k];
                break;
            }
            // 탈출했다면 해당 위치를 기록
            if (board[nx][ny] == 'O'){
                pnts[idx].x = nx;
                pnts[idx].y = ny;
                break;
            }
            // 다른 구슬을 만났다면 그 전 위치를 기록
            if (nx == pnts[chk].x && ny == pnts[chk].y){
                pnts[idx].x = nx - di[k];
                pnts[idx].y = ny - dj[k];
                break;
            }
        }
        return pnts;
    }

    // 먼저 움직여야 하는 index를 반환
    static int first(int k, Point[] pnts){
        int red = pnts[0].x * di[k] + pnts[0].y * dj[k];
        int blue = pnts[1].x * di[k] + pnts[1].y * dj[k];
        return red > blue ? 0 : 1;
    }

    static Point[] deepcopy(Point[] pnts) {
        Point[] copy = new Point[2];
        copy[0] = new Point(pnts[0].x, pnts[0].y);
        copy[1] = new Point(pnts[1].x, pnts[1].y);
        return copy;
    }
}