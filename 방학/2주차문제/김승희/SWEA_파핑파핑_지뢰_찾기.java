import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class SWEA_파핑파핑_지뢰찾기 {
    static int N;       // N : 지뢰 찾기 표의 크기

    // dx, dy : 주변 8방향을 탐색할 때 사용
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static int[][] map;         // map[i][j] : (i, j) 좌표의 주변 8칸의 지뢰 개수. -1 : 지뢰.
    static boolean[][] visit;   // visit[i][j] : (i, j) 좌표를 방문했는지 여부
    static ArrayList<Point> list;   // list : 지뢰의 위치를 담은 list

    // Point : 좌표값(i, j)를 저장할 클래스
    static class Point{
        int I, J;
        public Point(int i, int j){
            this.I = i;
            this.J = j;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // T : testcase의 수
        StringBuilder sb = new StringBuilder();         // sb : 각 testcase의 정답

        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visit = new boolean[N][N];
            list = new ArrayList<>();
            for(int i= 0; i < N; i++){
                char[] temp = br.readLine().toCharArray();
                for(int j = 0; j < N; j++){
                    // 지뢰인 경우.
                    if(temp[j] == '*'){
                        map[i][j] = -1;     // map[i][j] 에 -1 저장 후 방문 처리
                        visit[i][j] = true;
                        list.add(new Point(i, j));  // list에 좌표 추가
                    }
                }
            }
            connect();
            sb.append("#").append(t).append(" ").append(zeroBFS() + countRemain()).append("\n");
        }
        System.out.println(sb.toString());
    }

    // 지뢰 주변 8방향의 좌표에 +1
    static void connect(){
        for(int p = 0; p < list.size(); p++){
            Point pnt = list.get(p);
            for(int k = 0; k < dx.length; k++){
                int ni = pnt.I + dx[k];
                int nj = pnt.J + dy[k];

                // 범위를 벗어나거나 지뢰인 경우 continue
                if (ni < 0 || nj < 0 || ni >= N || nj >= N || map[ni][nj] == -1) {
                    continue;
                }
                map[ni][nj]++;
            }
        }
    }

    // 주변에 지뢰가 하나도 없는 좌표에 대해 BFS를 돌린다.
    static int zeroBFS(){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 0 && !visit[i][j]){
                    BFS(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 주변 8개의 방향에 대해 방문 처리
    static void BFS(int i, int j){
        Queue<Point> Q = new ArrayDeque<>();
        Q.add(new Point(i, j));
        visit[i][j] = true;
        while (!Q.isEmpty()) {
            Point pnt = Q.poll();
            for(int k = 0; k < dx.length; k++){
                int ni = pnt.I + dx[k];
                int nj = pnt.J + dy[k];

                // 범위를 벗어나면 continue
                if (ni < 0 || nj < 0 || ni >= N || nj >= N) {
                    continue;
                }
                // (ni, nj)가 지뢰이거나 방문했으면 continue
                if(map[ni][nj] == -1 || visit[ni][nj]) continue;

                // 방문 처리
                visit[ni][nj] = true;
                // (ni, nj)가 주변에 지뢰가 없다면 Q에 넣는다.
                if(map[ni][nj] == 0){
                    Q.add(new Point(ni, nj));
                }
            }
        }
    }
    // 주변에 지뢰가 없는 좌표에 대해 BFS를 모두 돌린 뒤 남은 좌표(지뢰가 아닌) 개수 count
    static int countRemain(){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] > 0 && !visit[i][j]){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void print(){
        System.out.println("----------------");
        for(int i = 0; i < N; i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
