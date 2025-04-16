import javax.sound.midi.Track;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M, ANS;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = chars[j] - '0';
            }
        } // end input
        ANS = 0;
        if (init()) ANS = 1;
        process();
        System.out.println(ANS * ANS);
    }
    static boolean init(){
        for (int i = 0; i < N; i++) {
            if (board[i][0] == 1) return true;
        }
        for (int j = 0; j < M; j++) {
            if (board[0][j] == 1) return true;
        }
        return false;
    }
    static void pretty() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
    static void process(){
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {   // (i, j) 좌표를 정사각형의 맨 오른쪽 맨 아래 좌표로 생각
                if (board[i][j] == 0) continue;     // 0이면 볼 필요 없음
                for (int len = 2; len < Math.min(i + 1, j + 1)+1; len++) {    // 만들 수 있는 정사각형의 길이
                    int half = len / 2;
                    int chk = len / 2;
                    if (len % 2 != 0) chk++;

                    // 범위 벗어나면 더 이상 볼 필요 없음
                    if (i-half < 0 || j-half < 0) break;
                    // 3개가 다 chk과 같거나 크면 len 길이의 정사각형을 만들 수 있음
                    if (board[i-half][j-half] >= chk && board[i-half][j] >= chk && board[i][j-half] >= chk) {
                        board[i][j] = len;
                        ANS = Math.max(len, ANS);
                    } else {
                        break;
                    }
                }
            }
        }
    }

}