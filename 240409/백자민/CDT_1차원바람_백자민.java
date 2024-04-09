package day240409;

import java.util.*;
import java.io.*;

public class CDT_1차원바람_백자민 {

    static int N,M,Q;

    static int[][] map;

    static boolean isPass(int tmp, int target) { //기준행(tmp)과 전파대상 행(target)을 비교해 전파 가능한지 체크하는 함수
        boolean flag = false;
        for(int j=0;j<M;j++) {
            if(map[tmp][j]==map[target][j]) { //하나라도 일치할 경우 true 판정
                flag = true;
                break;
            }
        }
        return flag;
    }

    static void moveRight(int i) { //행을 오른쪽으로 이동시키는 함수, 진행방향 ->
        int tmp = map[i][M-1];
        for(int j=M-2;j>=0;j--) {
            map[i][j+1] = map[i][j];
        }
        map[i][0] = tmp;
    }

    static void moveLeft(int i) { //행을 왼쪽으로 이동시키는 함수, 진행방향 <-
        int tmp = map[i][0];
        for(int j=1;j<M;j++) {
            map[i][j-1] = map[i][j];
        }
        map[i][M-1] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int q=0;q<Q;q++) {
            st = new StringTokenizer(br.readLine());
            int initRow = Integer.parseInt(st.nextToken())-1; //0-index므로 1빼주기
            boolean initDir = st.nextToken().equals("L")?true:false; //초기 방향 | T:왼쪽,F:오른쪽

            //1. 대상 행 이동시키기
            if(initDir) {
                moveRight(initRow);
            } else {
                moveLeft(initRow);
            }

            //2. 위,아래 행에 전파가 진행되는지 체크하고 이동 -> 마지막 행까지 반복
            //위로 전파
            int nextRow=initRow-1, tmpRow=initRow;
            boolean isLeft=!initDir; //다음 행에 영향을 미치는 전파 방향 | T:왼쪽, F:오른쪽
            while(nextRow>=0) {
                if(!isPass(tmpRow, nextRow)) { //전파가 불가능하면 멈춤
                    break;
                }
                //전파 방향에 따라 이동
                if(isLeft) {
                    moveRight(nextRow);
                } else {
                    moveLeft(nextRow);
                }

                nextRow--;
                tmpRow--;
                isLeft = !isLeft;
            }

            //아래로 전파-방향 제외 알고리즘 동일
            nextRow = initRow+1;
            tmpRow = initRow;
            isLeft = !initDir;

            while(nextRow<N) {
                if(!isPass(tmpRow, nextRow)) {
                    break;
                }
                if(isLeft) {
                    moveRight(nextRow);
                } else {
                    moveLeft(nextRow);
                }

                nextRow++;
                tmpRow++;
                isLeft = !isLeft;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}