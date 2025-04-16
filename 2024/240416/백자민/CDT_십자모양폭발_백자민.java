package day240416;

import java.util.*;
import java.io.*;

public class CDT_십자모양폭발_백자민 {

    static int N;

    static int[][] map;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    static void gravity() {//맵에 중력을 작용시키는 함수
        Queue<Integer> q = new LinkedList<>();;
        for(int j=0;j<N;j++) {
            //1. 한 열의 숫자들 임시 큐에 담기(0은 제외, 아래에서부터)
            for(int i=N-1;i>=0;i--) {
                if(map[i][j]==0) continue;
                q.offer(map[i][j]);
            }

            //2. 큐에 있는 숫자들을 아래에서부터 채워넣기 + 큐가 비었다면 0으로 채우기
            for(int i=N-1;i>=0;i--) {
                if(!q.isEmpty()) {
                    map[i][j] = q.poll();
                } else {
                    map[i][j] = 0;
                }
            }
        }
    }

    static void bomb(int ti, int tj, int target) { //폭탄을 터트리는 함수 | (ti,tj): 중심 위치, target:폭탄 범위
        //4방향을 탐색해가며 폭탄의 영향을 받는 곳을 0으로 변경

        map[ti][tj] = 0;

        for(int dist=1;dist<target;dist++) {
            for(int d=0;d<4;d++) {
                int ni = ti+di[d]*dist;
                int nj = tj+dj[d]*dist;

                if(ni<0 || ni>=N || nj<0 || nj>=N) continue; //범위를 벗어난 경우

                map[ni][nj] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        
        StringTokenizer st;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int bombi = Integer.parseInt(st.nextToken())-1;
        int bombj = Integer.parseInt(st.nextToken())-1;
        //end input

        //1. 폭탄 터트리기
        bomb(bombi, bombj, map[bombi][bombj]);

        //2. 중력 작용
        gravity();

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}