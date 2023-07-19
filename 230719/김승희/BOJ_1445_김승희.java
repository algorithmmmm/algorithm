import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, M;        // N : 세로길이     M : 가로 길이
    static int ansDirect, ansSide;
    static Point S;      // S : 시작 위치
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static char[][] MAP;
    // visit[i][j] : (i, j) 좌표에서 x : 쓰레기를 통과하는 칸 최소 값, y : 쓰레기 옆을 지나는 칸 최소값
    static Point[][] visit;
    static class Point{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class QElement{
        Point pnt;
        int direct, side;

        public QElement(Point pnt, int direct, int side) {
            this.pnt = pnt;
            this.direct = direct;
            this.side = side;
        }

        public QElement(Point pnt) {
            this.pnt = pnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        MAP = new char[N][];
        for (int i = 0; i < N; i++) {
            MAP[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 'S'){
                    S = new Point(i, j);
                }
            }
        } // end input

        visit = new Point[N][M];
        ansDirect = Integer.MAX_VALUE;
        ansSide = Integer.MAX_VALUE;
        BFS(S);
        System.out.printf("%d %d", ansDirect, ansSide);

    }
    static void BFS(Point start){
        Queue<QElement> Q = new ArrayDeque<>();
        visit[start.x][start.y] = new Point(0, 0);
        Q.add(new QElement(start, 0, 0));
        while (!Q.isEmpty()) {
            QElement element = Q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = element.pnt.x + di[k];
                int ny = element.pnt.y + dj[k];
                // 범위 벗어나면 안됨
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (MAP[nx][ny] == 'F') { // 도착지 도착
                    if (ansDirect > element.direct) {       // 정답(통과하는 칸 수)가 더 크면 바꾸기
                        ansDirect = element.direct;
                        ansSide = element.side;
                    } else if (ansDirect == element.direct && ansSide > element.side) {
                        // 정답(통과하는 칸 수는 같고 지나는 칸 수)가 더 크면 바꾸기
                        ansSide = element.side;
                    }
                    continue;
                }

                Point pnt = new Point(nx, ny);
                QElement temp = new QElement(pnt, element.direct, element.side);
                if (MAP[nx][ny] == 'g') temp.direct++;  // g면 통과하는 거
                else if (isSide(pnt)) temp.side++;           // 옆이면 지나가는 거
                // visit 배열 값이 null이거나
                // visit 배열 값의 x값이 temp.direct보다 크거나
                // visit 배열 값의 x값이 temp.direct과 같고 y값이 temp.side보다 크다면
                // -> 방문 가능
                if (visit[nx][ny] == null || visit[nx][ny].x > temp.direct ||
                        (visit[nx][ny].x == temp.direct && visit[nx][ny].y > temp.side)) {
                    visit[nx][ny] = new Point(temp.direct, temp.side);
                    Q.add(temp);
                }
            }
        }
    }

    static boolean isSide(Point pnt) {
        for (int k = 0; k < 4; k++) {
            int nx = di[k] + pnt.x;
            int ny = dj[k] + pnt.y;
            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;
            if (MAP[nx][ny] == 'g'){
                return true;
            }
        }
        return false;
    }
}