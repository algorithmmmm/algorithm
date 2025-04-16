package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
/*
 * 문제이름: 벽돌깨기
 * 문제 요약: W x H 크기로 벽돌이 채워진 배열이 주어지고, N번 구슬을 쏘는 시뮬레이션 코드를 작성하는 문제(남은 벽돌의 개수 구하기)
 * 세부 조건
 * - 구슬은 좌.우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
 * - 벽돌은 숫자 1~9로 표현되며, 구슬이 명중한 벽돌은 상하좌우로 (벽돌에 적힌 숫자-1) 칸 만큼 같이 제거된다. 
 * - 벽돌은 동시에 제거된다. 
 * 
 * 
 * */

public class SWEA_5656_백자민 {
     
    static int N,W,H, min;
     
    static int[][] map;
    static int[] spotList;
    static boolean[][] visit;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
     
    static void fill(int[][] map) { //벽돌 채우기
        Queue<Integer> q = new LinkedList<>();
        for(int j=0;j<W;j++) {
            for(int i=H-1;i>=0;i--) {
                if(map[i][j]!=0) {
                    q.offer(map[i][j]);
                    map[i][j] = 0;
                }
            }
            for(int i=H-1;i>=0;i--) {
                if(!q.isEmpty()) {
                    map[i][j] = q.poll();
                }
            }
        }
    }
     
    static void destroy(int[][] map, int spot) {
        Queue<Point> q = new LinkedList<>(); //부술 벽돌을 담을 큐
        //구슬과 닿는 벽돌부터 부수기
        for(int i=0;i<H;i++) {
            if(map[i][spot]==1) { //1이면 그 자리의 블록만 폭파
                map[i][spot] = 0;
                return;
            }
            else if(map[i][spot]>1) { //1이 아니면 연쇄 반응 준비
                q.offer(new Point(i,spot,map[i][spot]));
                map[i][spot] = 0;
                visit[i][spot] = true;
                break;
            }
        }
         
        //벽돌에 있는 숫자만큼 사방 탐색하면서 블록 터트리기
        while(!q.isEmpty()) {
            Point temp = q.poll();
             
            for(int d=0;d<4;d++) {
                for(int n=1;n<temp.num;n++) {
                    int ni = temp.i+di[d]*n;
                    int nj = temp.j+dj[d]*n;
                     
                    if(ni<0 || nj<0 || ni>=H || nj>=W) continue;
                    if(visit[ni][nj]) continue;
                     
                    q.offer(new Point(ni,nj,map[ni][nj]));
                    map[ni][nj] = 0;
                    visit[ni][nj] = true;
                }
            }
        }
    }
     
    static void perm(int cnt) {
        if(cnt==N) { //부술 벽돌이 모두 선정되었으면 벽돌 부수기 시작
            int[][] copy = deepcopy(map);
             
            for(int n=0;n<N;n++) {
                visit = new boolean[H][W]; //방문체크할 배열
                destroy(copy, spotList[n]);
                fill(copy);
            }
             
            int leftovers = count(copy);
            min = leftovers<min?leftovers:min; //최솟값 갱신 
            return;
        }
         
        for(int w=0;w<W;w++) {
            spotList[cnt] = w;
            perm(cnt+1);
        }
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
             
            map = new int[H][W];
            spotList = new int[N];
            min = Integer.MAX_VALUE;
             
            for(int i=0;i<H;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<W;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            perm(0);
            System.out.println("#"+t+" "+min);
        }
    }
     
    //남은 벽돌의 개수를 세는 메소드
    static int count(int[][] map) { 
        int cnt = 0;
        for(int i=0;i<H;i++) {
            for(int j=0;j<W;j++) {
                if(map[i][j]!=0)
                    cnt++;
            }
        }
        return cnt;
    }
     
    static int[][] deepcopy(int[][] origin) { 
        int[][] copy = new int[H][W];
        for(int i=0;i<H;i++) {
            for(int j=0;j<W;j++) {
                copy[i][j] = origin[i][j];
            }
        }
        return copy;
    }
    //벽돌 좌표, 벽돌에 써있는 숫자 
    static class Point {
        int i,j,num;
 
        public Point(int i, int j, int num) {
            this.i = i;
            this.j = j;
            this.num = num;
        }
         
    }
}