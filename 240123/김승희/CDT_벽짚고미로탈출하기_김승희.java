import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static boolean[][] wall;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] inArr = br.readLine().split(" ");
        int[] start = {Integer.parseInt(inArr[0])-1, Integer.parseInt(inArr[1])-1, 3};
        wall = new boolean[N][N];
        for(int i = 0; i < N; i++){
            String a = br.readLine();
            for(int j = 0; j < N; j++){
                if(a.charAt(j) == '#') wall[i][j] = true;
            }
        }
        // end input
        System.out.println(BFS(start));
    }
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, -1, 0, 1};
    static int[] clockwise = {3, 0, 1, 2}; 
    static int BFS(int[] pnt){
        boolean[][][] visit = new boolean[N][N][4];
        visit[pnt[0]][pnt[1]][3] = true;
        Queue<int[]> Q = new ArrayDeque<>();
        Q.add(new int[]{pnt[0], pnt[1], pnt[2], 0});
        while(!Q.isEmpty()){
            int[] pop = Q.poll();
            // 오른쪽 확인
            int ci = pop[0] + di[clockwise[pop[2]]];
            int cj = pop[1] + dj[clockwise[pop[2]]];
            if(ci < 0 || cj < 0 || ci >= N || cj >= N){
                continue;
            }
            
            if(wall[ci][cj]){   // 오른쪽에 벽이 있음
                int ni = pop[0] + di[pop[2]];
                int nj = pop[1] + dj[pop[2]];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N) return pop[3]+1;
                if(wall[ni][nj]){
                    int d = (pop[2] + 1) % 4;
                    if(visit[pop[0]][pop[1]][d]) continue;
                    Q.add(new int[]{pop[0], pop[1], d, pop[3]});
                    continue;
                }
                if(visit[ni][nj][pop[2]]) continue;
                Q.add(new int[]{ni, nj, pop[2], pop[3]+1});
                visit[ni][nj][pop[2]] = true;
            }else{  // 오른쪽에 벽이 없음
                int d = clockwise[pop[2]];
                int ni = pop[0] + di[d];
                int nj = pop[1] + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if(wall[ni][nj] || visit[ni][nj][d]) continue;
                Q.add(new int[]{ni, nj, d, pop[3]+1});
                visit[ni][nj][d] = true;
            }
        }
        return -1;
    }
    static void pretty(int[] pnt){
        char[] ch = {'^', '<', 'v', '>'};
        StringBuilder sb = new StringBuilder();
        sb.append("------------- ").append(pnt[3]).append("\n");
        sb.append(Arrays.toString(pnt)).append("\n");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(pnt[0] == i && pnt[1] == j) {
                    sb.append(ch[pnt[2]]);
                    continue;
                }
                if(wall[i][j]){
                    sb.append("#");
                }else{
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}