import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] Map;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        R = Integer.parseInt(inArr[0]);
        C = Integer.parseInt(inArr[1]);
        int K = Integer.parseInt(inArr[2]);
        Map = new boolean[R][C];
        for (int k = 0; k < K; k++) {
            inArr = br.readLine().split(" ");
            int N = Integer.parseInt(inArr[0]);
            int M = Integer.parseInt(inArr[1]);
            boolean[][] sticker = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                inArr = br.readLine().split(" ");
                for (int j = 0; j < M; j++) {
                    if (inArr[j].equals("1")) {
                        sticker[i][j] = true;
                    }
                }
            }
            process(N, M, sticker);
        }
        System.out.println(count());
    }
    // sticker를 맨 위, 맨 왼부터 붙일 수 있는 위치를 찾아서 붙인다.
    // 만약 붙일 수 없다면 시계 방향으로 90도, 180도, 270도 순으로 돌려서 시도한다.
    static void process(int N, int M, boolean[][] sticker) {
        for (int i = 0; i < 4; i++){
            if (attach(N, M, sticker)){
                break;
            }
            sticker = rotate(N, M, sticker);
            int temp = N;
            N = M;
            M = temp;

        }
    }

    // Map 배열에서 True인 개수 반환
    static int count() {
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (Map[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    // 맨 위, 맨 왼부터 N * M 크기의 sticker를 붙일 수 있는지 확인하고(check)
    // 붙이기(draw)
    static boolean attach(int N, int M, boolean[][] sticker){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (check(i, j, N, M, sticker)) {
                    draw(i, j, N, M, sticker);
                    return true;
                }
            }
        }
        return false;
    }
    // (I, J)를 맨 위, 맨 왼으로 해서 sticker를 붙일 수 있는지 확인
    static boolean check(int I, int J, int N, int M, boolean[][] sticker){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int ni = I + i;
                int nj = J + j;
                if (ni >= R || nj >= C) return false;
                if (sticker[i][j] && Map[ni][nj]) return false;
            }
        }
        return true;
    }
    // (I, J)를 맨 위, 맨 왼으로 해서 sticker 붙이기
    static void draw(int I, int J, int N, int M, boolean[][] sticker){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (sticker[i][j]){
                    Map[I + i][J + j] = sticker[i][j];
                }
            }
        }
    }
    // N*M크기의 origin 스티커를 시계방향으로 90도 돌리기
    static boolean[][] rotate(int N, int M, boolean[][] origin) {
        boolean[][] sticker = new boolean[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sticker[j][N-1-i] = origin[i][j];
            }
        }
        return sticker;
    }
}