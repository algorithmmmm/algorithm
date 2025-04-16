import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static char[][] Board;
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Board = new char[12][6];
        for (int i = 0; i < 12; i++) {
            Board[i] = br.readLine().toCharArray();
        }//end input
        int answer = 0;
        while (true) {
            if (!bomb()) break;
            answer++;
            down();
        }
        System.out.println(answer);
    }

    static void down() {
        for (int j = 0; j < 6; j++) {
            ArrayList<Character> ch = new ArrayList<>();
            for (int i = 11; i >= 0; i--) {
                if (Board[i][j] == '.') continue;
                ch.add(Board[i][j]);
                Board[i][j] = '.';
            }
            for (int i = 0; i < ch.size(); i++) {
                Board[11 - i][j] = ch.get(i);
            }
        }
    }

    static boolean bomb() {
        boolean chk = false;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (Board[i][j] == '.') continue;
                chk = chk | BFS(new Point(i, j));
            }
        }
        return chk;
    }

    static boolean BFS(Point start) {
        boolean[][] visit = new boolean[12][6];
        Queue<Point> Q = new ArrayDeque<>();
        Q.add(start);
        visit[start.x][start.y] = true;
        ArrayList<Point> list = new ArrayList<>();
        while (!Q.isEmpty()) {
            Point pnt = Q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = pnt.x + di[k];
                int nj = pnt.y + dj[k];
                if (ni < 0 || nj < 0 || ni >= 12 || nj >= 6) continue;
                if (visit[ni][nj] || Board[ni][nj] != Board[start.x][start.y]) continue;
                Q.add(new Point(ni, nj));
                visit[ni][nj] = true;
                list.add(new Point(ni, nj));
                Board[ni][nj] = '.';
            }
        }

        if (list.size() + 1 < 4) {  // 안터짐 돌려놓기
            for (int s = 0; s < list.size(); s++) {
                Board[list.get(s).x][list.get(s).y] = Board[start.x][start.y];
            }
            return false;
        } else { // 시작 위치 터뜨리기
            Board[start.x][start.y] = '.';
            return true;
        }
    }

}
