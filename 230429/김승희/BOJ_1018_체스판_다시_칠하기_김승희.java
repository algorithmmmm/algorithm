import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ_1018_체스판_다시_칠하기 {
    static int N, M, startR, startC, endR, endC, result;
    static char[][] MAP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);

        MAP = new char[N][];
        for(int i= 0; i < N; i++){
            MAP[i] = br.readLine().toCharArray();
        }
        // end input
        result = Integer.MAX_VALUE;
        for(int r = 0; r <= N-8; r++){
            for(int c = 0; c <= M-8; c++){
                boolean[][] visit = new boolean[N][M];
                startR = r;
                startC = c;
                endR = r + 7;
                endC = c + 7;
                int temp = tint(0, MAP[startR][startC]);
                result = Math.min(result, temp);
                temp = tint(1, reverseColor(MAP[startR][startC]));
                result = Math.min(result, temp);
            }
        }
        System.out.println(result);
    }
    static Character reverseColor(char ch){
        if(ch == 'B') return 'W';
        if(ch == 'W') return 'B';
        return null;
    }

    static int tint(int cnt, char prev){
        for(int p = startR; p <= endR; p++){
            for(int q = startC; q <= endC; q++){
                if(p == startR && q == startC){
                    continue;
                }
                if(prev == MAP[p][q]){
                    cnt++;
                    prev = reverseColor(MAP[p][q]);
                }else {
                    prev = MAP[p][q];
                }
            }
            prev = reverseColor(prev);
        }
        return cnt;
    }
}
