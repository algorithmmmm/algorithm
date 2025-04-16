import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point{
        int x, y, value;
        public Point(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    static int N, M, IDX, R_MAX;
    static int[][] MAP;
    // 서 북 동 남, 왼 위 오 아래
    static int[] di = {0, -1, 0, 1};
    static int[] dj = {-1, 0, 1, 0};
    static int[] select;
    static HashMap<Integer, Integer> rooms;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inArr = br.readLine().split(" ");
        M = Integer.parseInt(inArr[0]);     // M : 가로 길이
        N = Integer.parseInt(inArr[1]);     // N : 세로 길이

        MAP = new int[N][M];
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(inArr[j]);
            }
        }   // end input
        R_MAX = 0;
        rooms = new HashMap<>();        // key : 방번호, value : 방 크기
        IDX = -1;       // IDX : -방 번호. -> 마지막에 -1을 곱하면 방의 개수가 된다.
        int max_size = 0;   // max_size : 가장 넓은 방의 넓이
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if (MAP[i][j] < 0) continue;
                int cnt = BFS(new Point(i, j, MAP[i][j]));
                max_size = Math.max(max_size, cnt);
                rooms.put(IDX, cnt);
                IDX--;
            }
        }
        System.out.println((-IDX)-1);
        System.out.println(max_size);
        System.out.println(R_MAX);
    }

    static Integer BFS(Point start){
        Queue<Point> Q = new ArrayDeque<>();
        Q.add(start);
        MAP[start.x][start.y] = IDX;
        int temp_max = 0;   // temp_max : 현재 방의 인접 방 중 가장 넓이가 넓은 방
        int cnt = 1;
        while (!Q.isEmpty()) {
            Point pnt = Q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = pnt.x + di[k];
                int ny = pnt.y + dj[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;   // 범위 벗어남
                if (MAP[nx][ny] < 0){       // 이미 방문한 경우
                    if (IDX != MAP[nx][ny]) // 다른 방인 경우
                        temp_max = Math.max(temp_max, rooms.get(MAP[nx][ny]));
                    continue;
                }
                // k번째 비트가 0이면 0, 1이면 0보다 큰 값
                int chk = pnt.value & (1 << k);
                if (chk > 0 ) { // chk가 0이 아니면 벽
                    continue;
                }
                Q.add(new Point(nx, ny, MAP[nx][ny]));
                MAP[nx][ny] = IDX;
                cnt++;
            }
        }
        // 벽 한 개 부숴보기
        R_MAX = Math.max(R_MAX, temp_max + cnt);

        return cnt;
    }

}
