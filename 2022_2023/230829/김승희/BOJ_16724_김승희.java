import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    static class Tuple{
        int x, y;

        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static final String UDLR = "UDLR";
    // UDLR
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] Dir;
    static int[][] Parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        Dir = new int[N][M];
        Parent = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                Dir[i][j] = UDLR.indexOf(chars[j]);
                Parent[i][j] = -1;
            }
        }   // end input

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // Parent[i][j] == -1인 좌표부터 갈 수 있는 곳 까지 간다.
                if (Parent[i][j] == -1){
                    if (findPath(i, j, cnt)) {
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }

    static boolean findPath(int i, int j, int cnt) {
        ArrayList<Tuple> path = new ArrayList<>();
//        while 문을 멈추는 조건은 아래와 같다.
//        1. 현재 방문한 곳에 또 간 경우 -> 이는 새로운 경로를 찾았음을 의미한다.
//        2. Parent[ni][nj]의 값이 -1이 아닌 좌표가 현재 경로에 포함되어 있는 경우
//            -> 이는 이미 발견된 경로에 포함되는 것을 의미한다.
        Queue<Tuple> Q = new ArrayDeque<>();
        Q.add(new Tuple(i, j));
        path.add(new Tuple(i, j));
        while (!Q.isEmpty()) {
            Tuple tp = Q.poll();
            if (Parent[tp.x][tp.y] != -1) {     // 2번 경우
                draw(path, Parent[tp.x][tp.y]);
                return false;
            }
            Parent[tp.x][tp.y] = cnt;
            int d = Dir[tp.x][tp.y];
            int ni = tp.x + di[d];
            int nj = tp.y + dj[d];
            if (Parent[ni][nj] == cnt){                 // 1번 경우
                return true;
            }
            path.add(new Tuple(ni, nj));
            Q.add(new Tuple(ni, nj));
        }
        return false;
    }

    static void draw(ArrayList<Tuple> path, int value) {
        for (int i = 0; i < path.size(); i++) {
            Parent[path.get(i).x][path.get(i).y] = value;
        }
    }
}