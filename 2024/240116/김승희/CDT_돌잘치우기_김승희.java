import java.util.*;
import java.io.*;
public class Main {
    static int N, M, K, Answer;
    static int[] select;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] start;
    static boolean[][] isWall;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        K = Integer.parseInt(inArr[1]);
        M = Integer.parseInt(inArr[2]);
        isWall = new boolean[N+1][N+1];
        for(int i = 0; i < N; i++){
            inArr = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                if(Integer.parseInt(inArr[j]) == 1){
                    isWall[i+1][j+1] = true;
                }
            }
        }
        start = new int[K][2];
        for(int i = 0; i < K; i++){
            inArr = br.readLine().split(" ");
            start[i][0] = Integer.parseInt(inArr[0]);
            start[i][1] = Integer.parseInt(inArr[1]);
        }
        // end input

        Answer = Integer.MIN_VALUE;
        select= new int[M];
        combination(0, 0);

        System.out.println(Answer);

    }
    static void combination(int idx, int cnt){
        if(cnt == M){
            BFS();
            return;
        }
        for(int i = idx; i < N*N; i++){
            int I = i / N + 1;
            int J = i % N + 1;
            if(!isWall[I][J]) continue;
            select[cnt] = i;
            combination(i+1, cnt+1);
        }
    }
    static void BFS(){
        boolean[][] value = remove();
        boolean[][] visit = new boolean[N+1][N+1];
        Queue<int[]> Q = new ArrayDeque<>();
        for(int k = 0; k < K; k++){
            Q.add(new int[]{start[k][0], start[k][1]});
            visit[start[k][0]][start[k][1]] = true;
        }
        while(!Q.isEmpty()){
            int[] pop = Q.poll();
            for(int k = 0; k < 4; k++){
                int ni = pop[0] + di[k];
                int nj = pop[1] + dj[k];
                if(ni < 1 || nj < 1 || ni > N || nj > N || visit[ni][nj]) continue;
                if(value[ni][nj]) continue;
                Q.add(new int[]{ni, nj});
                visit[ni][nj] = true;
            }
        }
        Answer = Math.max(Answer, count(visit));
    }
    static int count(boolean[][] arr){
        int cnt = 0;
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < N+1; j++){
                if(arr[i][j]) cnt++;
            }
        }
        return cnt;
    }
    static boolean[][] deepcopy(){
        boolean[][] copy = new boolean[N+1][N+1];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i+1][j+1] = isWall[i+1][j+1];
            }
        }
        return copy;
    }
    static boolean[][] remove(){
        boolean[][] value = deepcopy();
        for(int i = 0; i < M; i++){
            int I = select[i] / N + 1;
            int J = select[i] % N + 1;
            value[I][J] = false;
        }
        return value;
    }
}