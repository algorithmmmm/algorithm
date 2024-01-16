import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int[][] Board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        Board = new int[N][M];
        for(int i = 0; i < N; i++){
            inArr = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                Board[i][j] = Integer.parseInt(inArr[j]);
            }
        } // end input
        int answer = Math.max(makeRect22(), makeRect13());
        System.out.println(Math.max(answer, makeRect31()));
    }
    static int makeRect22(){
        int answer = 0;
        for(int i = 0; i <= N -2; i++){
            for(int j = 0; j <= M - 2; j++){
                answer = Math.max(answer, sum22(i, j));
            }
        }
        return answer;
    }
    static int sum22(int I, int J){
        int sum = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                sum += Board[I+i][J+j];
            }
        }
        int a = Math.max(sum - Board[I][J], sum - Board[I + 1][J]);
        int b = Math.max(sum - Board[I][J + 1], sum - Board[I + 1][J + 1]);
        return Math.max(a, b);
    }
    static int makeRect13(){
        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j <= M - 3; j++){
                answer = Math.max(answer, sum13(i, j));
            }
        }
        return answer;
    }
    static int sum13(int I, int J){
        int sum = 0;
        for(int j = 0; j < 3; j++){
            sum += Board[I][J + j];
        }
        return sum;
    }
    static int makeRect31(){
        int answer = 0;
        for(int i = 0; i <= N - 3; i++){
            for(int j = 0; j < M; j++){
                answer = Math.max(answer, sum31(i, j));
            }
        }
        return answer;
    }
    static int sum31(int I, int J){
        int sum = 0;
        for(int i = 0; i < 3; i++){
            sum += Board[I + i][J];
        }
        return sum;
    }
}