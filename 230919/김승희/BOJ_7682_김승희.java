import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_7682 {
    static char[][] Board;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        boolean chk = false;
        while (true) {
            String input = br.readLine();
            if (input.equals("end")) break;
            chk = true;
            if (process(input)) sb.append("valid").append("\n");
            else sb.append("invalid").append("\n");
        }
        if (chk)
            System.out.println(sb.toString());
    }

    static boolean process(String str) {
        Board = new char[3][3];
        int cntO = 0;
        int cntX = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'O') cntO++;
            if (str.charAt(i) == 'X') cntX++;
            int a = i / 3;
            int b = i % 3;
            Board[a][b] = str.charAt(i);
        }
        if (cntO+cntX == 0) return true;
        if (!(cntO==cntX || cntX == (cntO+1))) {
            return false;
        }
        int[] chk = checkBoard();
        // X랑 O랑 둘 다 빙고면 false
        if (chk[0] > 0 && chk[1] > 0) return false;

        if (chk[0] >= 1) return cntX == (cntO+1);
        if (chk[1] == 1) return  cntX == cntO;

        // 더이상 놓을 곳이 없으면 true
        return (cntX + cntO)== 9;
    }
    // 빙고를 만들었는지
    static int[] checkBoard(){
        int[] arr = new int[2];     // {'X', 'O'}
        for (int i = 0; i < 3; i++) {
            char chI = Board[i][0];
            char chJ = Board[0][i];
            int I = 1;  // 행 확인
            int J = 1;  // 열 확인
            for (int j = 1; j < 3; j++) {
                if (chI != '.' && chI == Board[i][j]) {
                    I++;
                }
                if (chJ != '.' && chJ == Board[j][i]){
                    J++;
                }
            }
            if (I == 3) {
                if (chI == 'X') arr[0]++;
                if (chI == 'O') arr[1]++;
            }
            if (J == 3) {
                if (chJ == 'X') arr[0]++;
                if (chJ == 'O') arr[1]++;
            }
        }
        // 대각선 확인
        if ('.' != Board[0][2] && Board[0][2] == Board[1][1] && Board[1][1] == Board[2][0]){
            if (Board[1][1] == 'X') arr[0]++;
            if (Board[1][1] == 'O') arr[1]++;
        }
        if ('.' != Board[0][0] && Board[0][0] == Board[1][1] && Board[1][1] == Board[2][2]){
            if (Board[1][1] == 'X') arr[0]++;
            if (Board[1][1] == 'O') arr[1]++;
        }
        return arr;
    }

}
