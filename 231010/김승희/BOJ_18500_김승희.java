import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_18500 {
    static int R, C;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static char[][] Board;
    static boolean[][] Visit;
    static int[] op;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        R = Integer.parseInt(inArr[0]);     // R : 세로길이
        C = Integer.parseInt(inArr[1]);     // C : 가로 길이
        Board = new char[R][C];             // Board : 동굴의 상태
        for (int i = 0; i < R; i++) {
            Board[i] = br.readLine().toCharArray();
        }
        int T = Integer.parseInt(br.readLine());
        op = new int[T];                // op[i] : i번째에 던지는 막대기의 높이
        inArr = br.readLine().split(" ");
        for (int i = 0; i < T; i++) {
            op[i] = R - Integer.parseInt(inArr[i]);
        }
        // end input
        for (int i = 0; i < T; i++) {
            // 막대기 던지기
            process(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(Board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void process(int idx) {
        int[] point = new int[2];
        if (idx % 2 == 0){      // 짝수번은 왼쪽에서 던짐
            point = leftThrow(op[idx]);
        }else {                 // 홀수번은 오른쪽에서 던짐
            point = rightThrow(op[idx]);
        }
        if (point[0] == -1 && point[1] == -1) return;   // 부서진게 아무것도 없음
        // 부서진 위치를 기준으로 인접 4 좌표에서  클러스터가 위에 떠있는지 확인
        Visit = new boolean[R][C];
        for (int i = 0; i < 4; i++) {
            int ni = point[0] + di[i];
            int nj = point[1] + dj[i];
            if (ni < 0 || nj < 0 || ni >= R || nj >= C || Board[ni][nj] == '.' || Visit[ni][nj]) continue;
            ArrayList<int[]> cluster = bfs(ni, nj);
            if (!cluster.isEmpty()) {   // 공중에 떠있는 cluster. 항상 1개의 클러스터만 공중에 떠있다고 명시되어 있음
                down(cluster);
                break;
            }
        }
    }

    static void down(ArrayList<int[]> cluster) {
        // 일단 cluster에 속한 미네랄의 위치를 모두 비어있다고 표시
        for (int i = 0; i < cluster.size(); i++) {
            int[] temp = cluster.get(i);
            Board[temp[0]][temp[1]] = '.';
        }
        boolean flag = true;
        while (flag) {
            ArrayList<int[]> dcluster = new ArrayList<>();
            for (int i = 0; i < cluster.size(); i++) {
                int[] temp = cluster.get(i);
                if (temp[0]+1 == R || Board[temp[0]+1][temp[1]] == 'x') {
                    flag = false;
                    break;
                }
                dcluster.add(new int[]{temp[0] + 1, temp[1]});
            }
            if(flag) cluster = dcluster;
        }
        // cluster에 속한 미네랄의 위치를 미네랄로 표시
        for (int i = 0; i < cluster.size(); i++) {
            int[] temp = cluster.get(i);
            Board[temp[0]][temp[1]] = 'x';
        }
    }

    static ArrayList<int[]> bfs(int i, int j) {
        ArrayList<int[]> cluster = new ArrayList<>();   // cluster : 현재 클러스터에 속한 미네랄의 위치
        boolean floor = false;      // floor : 현재 클러스터가 바닥에 닿았으면 true
        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{i, j});
        cluster.add(new int[]{i, j});
        Visit[i][j] = true;
        while (!Q.isEmpty()) {
            int[] pnt = Q.poll();
            for (int k = 0; k < 4; k++) {
                int ni = pnt[0] + di[k];
                int nj = pnt[1] + dj[k];
                if (ni < 0 || nj < 0 || ni >= R || nj >= C || Visit[ni][nj]) continue;
                if (Board[ni][nj] == 'x'){
                    Q.add(new int[]{ni, nj});
                    cluster.add(new int[]{ni, nj});
                    Visit[ni][nj] = true;
                    if (ni == R-1) floor = true;
                }
            }
        }
        if (floor) return new ArrayList<>();    // 바닥에 닿았으면 빈 list
        return cluster;
    }
    static int[] leftThrow(int h) {
        int[] point = {-1, -1};     // 부서진 미네랄의 좌표. 만약 부서진게 없으면 (-1, -1)
        for (int j = 0; j < C; j++) {
            if (Board[h][j] == 'x'){
                Board[h][j] = '.';
                point[0] = h;
                point[1] = j;
                break;
            }
        }
        return point;
    }
    static int[] rightThrow(int h) {
        int[] point = {-1, -1};     // 부서진 미네랄의 좌표. 만약 부서진게 없으면 (-1, -1)
        for (int j = C-1; j >= 0; j--) {
            if (Board[h][j] == 'x'){
                Board[h][j] = '.';
                point[0] = h;
                point[1] = j;
                break;
            }
        }
        return point;
    }
}
