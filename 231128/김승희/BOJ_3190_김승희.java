import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Queue;

public class Main {
    static int N, D, Time;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};
    static boolean[][] apple, isSnake;
    static Deque<int[]> Q;
    static HashMap<Integer, String> direction;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        apple = new boolean[N][N];      // apple[i][j] : (i, j) 좌표에 사과가 있으면 true
        isSnake = new boolean[N][N];    // isSnake[i][j] : (i, j) 좌표가 뱀이면 true
        int M = Integer.parseInt(br.readLine());    // M : 사과의 개수
        for (int i = 0; i < M; i++) {
            String[] inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0])-1;
            int b = Integer.parseInt(inArr[1])-1;
            apple[a][b] = true;
        }
        direction = new HashMap<>();    // key : time, value : 방향.
        M = Integer.parseInt(br.readLine());    // M : 방향 변환 횟수
        for (int i = 0; i < M; i++) {
            String[] inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            direction.put(a, inArr[1]);
        }
        // end input

        Q = new ArrayDeque<>();     // Q의 맨 앞 : 뱀의 꼬리, Q의 맨 뒤 : 뱀의 머리
        Q.add(new int[]{0, 0});
        isSnake[0][0] = true;
        D = 0;      // D : 뱀의 이동 방향. 처음엔 오른쪽으로
        Time = 0;   // Time : 절대 시간
        while (true) {
            // 뱀 움직이기
            if (!move()) break;
            Time++;
            String nd = direction.getOrDefault(Time, "NO");
            if (nd.equals("NO")) continue;
            if (nd.equals("D")) {
                D = (D + 1) % 4;
            } else if (nd.equals("L")) {
                D = (D - 1 + 4) % 4;
            }
        }
        System.out.println(Time + 1);
    }
    static boolean move() {
        if (Q.isEmpty()) return false;
        int[] now = Q.peekLast();
        // 새로운 머리 위치
        int headI = now[0] + di[D];
        int headJ = now[1] + dj[D];
        // 벽을 만나면 게임 끝
        if (headI >= N || headJ >= N || headI < 0 || headJ < 0) return false;
        // 자기 몸을 만나면 끝
        if (isSnake[headI][headJ]) return false;
        if (apple[headI][headJ]) {  // 사과가 있으면 사과 먹기
            apple[headI][headJ] = false;
        }else {     // 사과가 없으면 꼬리 말기
            int[] tail = Q.poll();
            isSnake[tail[0]][tail[1]] = false;
        }
        Q.add(new int[]{headI, headJ});
        isSnake[headI][headJ] = true;
        return true;
    }
}