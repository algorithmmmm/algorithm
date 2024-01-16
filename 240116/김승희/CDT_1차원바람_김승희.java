import java.util.*;
import java.io.*;
public class Main {
    static int N, M, Q;
    static String LR = "LR";
    static int[][] Building;
    static int[][] Wind;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        Q = Integer.parseInt(inArr[2]);
        Building = new int[N][M];
        for(int i = 0; i < N; i++){
            inArr = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                Building[i][j] = Integer.parseInt(inArr[j]);
            }
        }
        Wind = new int[Q][2];
        for(int q = 0; q < Q; q++){
            inArr = br.readLine().split(" ");
            Wind[q][0] = Integer.parseInt(inArr[0])-1;
            Wind[q][1] = LR.indexOf(inArr[1]);
        } // end input

        blowProcess();
        prettyPrint();
    }
    static void prettyPrint(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(Building[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void blowProcess(){
        for(int q = 0; q < Q; q++){
            // 바람 불기
            blow(Wind[q][0], Wind[q][1]);
            // 전파시키기
            effect(Wind[q][0], Wind[q][1]);
        }
    }
    // idx 행에 dir 방향으로 바람이 분다.
    static void blow(int idx, int dir){
        // 바람 불기
        if(dir == 1){
            shiftR(idx);
        }else{
            shiftL(idx);
        }
    }
    
    // idx행에 오른쪽으로 바람이 분다. -> 맨 앞이 맨 뒤로
    static void shiftR(int idx){
        int temp = Building[idx][0];
        for(int j = 1; j < M; j++){
            Building[idx][j-1] = Building[idx][j];
        }
        Building[idx][M-1] = temp;
    }
    // idx행에 왼쪽으로 바람이 분다. -> 맨 뒤가 맨 앞으로
    static void shiftL(int idx){
        int temp = Building[idx][M-1];
        for(int j = M-2; j >= 0; j--){
            Building[idx][j+1] = Building[idx][j];
        }
        Building[idx][0] = temp;
    }
    // idx 위 아래로 전파시키기
    static void effect(int idx, int dir){
        // 위로 전파
        int up = dir;
        for(int i = idx-1; i >= 0; i--){
            if(!isEffect(i, i + 1)) break;
            up = (up+1) % 2;
            blow(i, up);
        }
        //아래로 전파
        int down = dir;
        for(int i = idx+1; i < N; i++){
            if(!isEffect(i, i-1)) break;
            down = (down+1) % 2;
            blow(i, down);
        }
    }
    // a행과 b행이 같은 열에 같은 숫자가 있으면 true
    static boolean isEffect(int a, int b){
        for(int j = 0; j < M; j++){
            if(Building[a][j] == Building[b][j]) return true;
        }
        return false;
    }
}