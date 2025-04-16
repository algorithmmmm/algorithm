import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
2차원 세계를 아래에서부터 보면서 고일 수 있는 곳을 찾았다.
*/
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int h = Integer.parseInt(inArr[0]);	// h : 2차원 세계의 세로 길이
        int w = Integer.parseInt(inArr[1]);	// w : 2차원 세계의 가로 길이

        boolean[][] blocks = new boolean[h][w];
        inArr = br.readLine().split(" ");
        for (int i = 0; i < w; i++) {
            int a = Integer.parseInt(inArr[i]);
            for(int j = 0; j < a; j++){
                blocks[j][i] = true;		// blocks[i][j] : true면 (i, j) 가 벽
            }
        }
        // end input

        int acc = 0;
        for(int i = 0; i < h; i++){
            int start = -1;
            for(int j = 0; j < w; j++){
                if(blocks[i][j]){
                    if(start == -1){	// i 높이에서 벽의 시작
                        start = j;
                    }else{		// i 높이에서 벽의 끝
                        acc += (j - start - 1);		// 벽의 시작과 끝 사이의 공간 누적
                        start = j;			// 벽의 끝은 이제 벽의 시작이 됨
                    }
                }
            }
        }
        System.out.println(acc);
    }
}
