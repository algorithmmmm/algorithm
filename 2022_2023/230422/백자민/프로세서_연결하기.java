

import java.util.ArrayList;
import java.util.Scanner;
 
public class 프로세서_연결하기 {
     
    static int N, ans, maxcnt, size;
    static int[][] map;
     
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
     
    static ArrayList<Point> list; //코어 위치 저장할 큐
     
     
    static void print(int[][] map) {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
     
    static int[][] deepcopy(int[][] map) { //배열 복사 메소드
        int[][] copy = new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }
    //idx: 몇 번째 코어 연결중이냐, cnt: 최대로 연결했냐, sum: 최소값이냐, map: 복사해서 갖고다닐 맵
    static void connect(int idx, int cnt, int sum, int[][] map) { 
        if(sum > ans) { //지금까지의 합이 최솟값보다 클 경우
            return;
        }
         
        if(idx == size) { //마지막 코어까지 왔을 경우
            if(sum !=0 && maxcnt<=cnt) {
                //System.out.println(sum);
                //print(map);
                ans = Math.min(ans, sum);
                maxcnt = cnt;
            }
            return;
        }
        //꺼내서 연결하고 다음으로 넘어가기
        Point temp = list.get(idx);
        for(int d=0;d<4;d++) {
            int[][] copy = deepcopy(map);
            int count=0; //연결한 전선 길이 셀거야
            int ti = temp.i;
            int tj = temp.j;
             
            while(true) {
                int ni = ti+di[d];
                int nj = tj+dj[d];
                 
                if(ni<0 || ni>=N || nj<0 || nj>=N) break; //범위를 벗어났을 경우 멈추기
                 
                if(copy[ni][nj]!=0) //더 이상 진행할 수 없는 경우 다음 방향 탐색하러 가기
                    break;
                 
                copy[ni][nj] = 2;
                count++;
                 
                if(ni==0 || nj==0 || ni==N-1 || nj==N-1) { //전원 연결에 성공했을 경우
                    //다음 코어 꺼내기, 연결한 코어 수 증가, 전선 길이, 지금까지 맵
                    connect(idx+1, cnt+1, sum+count, copy); 
                }
                 
                 
                //아직 연결 못했을 경우에는 방문처리 하고 킵고잉 
                ti = ni;
                tj = nj;
            }
        }
        //4방향 다 탐색을 못했을 경우에도 킵고잉
        connect(idx+1, cnt, sum, map);
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        for(int t=1;t<=T;t++) {
            N = sc.nextInt();
            map = new int[N][N];
            list = new ArrayList<>();
             
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j]==1) {
                        if(i==0 || i==N-1 || j==0 || j==N-1) continue; //가장자리 - 이미 전원 공급
                        list.add(new Point(i,j));
                    }
                }
            }//end input
             
            maxcnt = 0; //연결한 코어의 최대 개수
            ans = Integer.MAX_VALUE; //최솟값을 갱신할거라서 최댓값으로 초기화
            size = list.size(); //코어 개수
            connect(0, 0, 0, map);
             
            System.out.println("#"+t+" "+ans);
        }
         
    }
     
    static class Point {
        int i,j;
 
        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}