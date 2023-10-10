import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21609 {
    static int N, M, Rainbow;
    static int[] Standard;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] Board;
    static boolean[][] Visit;
    static ArrayList<int[]> Large;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        Board = new int[N][N];
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            for (int j = 0; j < N; j++){
                Board[i][j] = Integer.parseInt(inArr[j]);
            }
        } // end input

        int answer = 0;
        while (autoPlay()) {
            answer += Large.size() * Large.size();
        }


        System.out.println(answer);
    }
    static void pretty() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(Board[i]));
        }
    }
    static boolean autoPlay() {
        // 크기가 가장 큰 블록 그룹 찾기
        getLarge();
        if (Large.size() == 0) return false;
        // 블록 그룹의 블록 제거
        remove();

        // 중력 작용
        down();

        // 반시계로 90도 회전
        rotate();

        // 중력 작용
        down();
        return true;
    }
    static void rotate() {
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[N-j-1][i] = Board[i][j];
            }
        }
        Board = arr;
    }

    static void down() {
        for (int j = 0; j < N; j++) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < N; i++) {
                if (Board[i][j] == Integer.MAX_VALUE) continue;
                if (Board[i][j] == -1) {    // 검정 블록
                    int idx = i - 1;
                    while (!stack.isEmpty()) {
                        Board[idx][j] = stack.pop();
                        idx--;
                    }
                    stack = new Stack<>();
                } else {
                    stack.push(Board[i][j]);
                    Board[i][j] = Integer.MAX_VALUE;
                }
            }
            int idx = N - 1;
            while (!stack.isEmpty()) {
                Board[idx][j] = stack.pop();
                idx--;
            }
        }
    }

    static void remove() {
        for (int i = 0; i < Large.size(); i++) {
            int[] pnt = Large.get(i);
            Board[pnt[0]][pnt[1]] = Integer.MAX_VALUE;
        }
    }
    static void getLarge() {
        Visit = new boolean[N][N];
        Rainbow = -1;
        Standard = new int[]{N, N};
        Large = new ArrayList<>();
        ArrayList<int[]> large = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 방문했거나, 검정이거나 무지개거나, 빈 곳이거나
                if (Visit[i][j] || Board[i][j] == -1 || Board[i][j] == 0 || Board[i][j] == Integer.MAX_VALUE) continue;
                bfs(i, j, Board[i][j]);
            }
        }
    }

    static void bfs(int i, int j, int color) {
        ArrayList<int[]> rainbow = new ArrayList<>();
        int[] standard = {i, j};    // standard : 기준 블록 위치
        ArrayList<int[]> list = new ArrayList<>();
        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{i, j});
        list.add(new int[]{i, j});
        Visit[i][j] = true;
        while (!Q.isEmpty()) {
            int[] pnt = Q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = pnt[0] + di[k];
                int nj = pnt[1] + dj[k];
                // 범위 벗어남 & 블랙은 안됨 & 빈 곳도 안됨
                if (ni < 0 || nj <0 || ni >= N || nj >= N || Visit[ni][nj] ||
                        Board[ni][nj] == -1 || Board[ni][nj] == Integer.MAX_VALUE) continue;

                if (Board[ni][nj] == 0) {   // 무지개 블록
                    rainbow.add(new int[]{ni, nj});
                    Q.add(new int[]{ni, nj});
                    list.add(new int[]{ni, nj});
                    Visit[ni][nj] = true;
                    continue;
                }
                // 일반 블록인데, 다른 색이면 안됨
                if (Board[ni][nj] != color) continue;
                Q.add(new int[]{ni, nj});
                list.add(new int[]{ni, nj});
                Visit[ni][nj] = true;
                if (standard[0] > ni) {
                    standard = new int[]{ni, nj};
                } else if (standard[0] == ni) {
                    if (standard[1] > nj) {
                        standard = new int[]{ni, nj};
                    }
                }
            }
        }

        // 무지개 블록 방문 가능하도록 돌려놓기
        for (int r = 0; r < rainbow.size(); r++) {
            int[] p = rainbow.get(r);
            Visit[p[0]][p[1]] = false;
        }

        if (list.size() < 2) return;    // 블록 그룹이 아님

        if (isChange(rainbow.size(), standard, list.size())) {
            Rainbow = rainbow.size();
            Standard = standard;
            Large = list;
        }
    }

    static boolean isChange(int rainbow, int[] standard, int size) {
        if (Large.size() < size) return true;
        if (Large.size() > size) return false;
        if (Rainbow < rainbow) return true;
        if (Rainbow > rainbow) return false;
        if (Standard[0] < standard[0]) return true;
        if (Standard[0] > standard[0]) return false;
        return Standard[1] < standard[1];
    }
}
