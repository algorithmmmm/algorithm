package day240206;

import java.util.*;
import java.io.*;

public class CDT_합쳐지는구슬들_백자민 {

    static int N,M,T;

    static PriorityQueue<Gem>[][] map;

    static int[] di = {-1,1,0,0}; //U-D-L-R
    static int[] dj = {0,0,-1,1};

    static void merge() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j].size()>1) { //구슬이 겹쳐지는 경우
                    Gem bigNum = map[i][j].poll(); //번호가 가장 높은 구슬
                    int sum=bigNum.w;

                    for(int q=0,size=map[i][j].size();q<size;q++) {
                        sum+=map[i][j].poll().w;
                    }

                    map[i][j].offer(new Gem(i,j,bigNum.n, bigNum.d, sum));
                }
            }
        }
    }

    static int rotate(int d) {
        if(d==0) return 1;
        if(d==1) return 0;
        if(d==2) return 3;
        
        return 2;
    }

    static void move() {

        Queue<Gem> q = new LinkedList<>();

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j].size()>0) {
                    Gem tmp = map[i][j].poll();
                    int td = tmp.d;
                    int ni = i+di[td];
                    int nj = j+dj[td];
                    if(ni<0 || ni>=N || nj<0 || nj>=N) { //방향 회전
                        tmp.d = rotate(td);
                        q.offer(tmp);
                        continue;
                    }
                    tmp.i=ni;
                    tmp.j=nj;
                    q.offer(tmp);
                }
            }
        }

        //이동이 끝나면 맵에 반영
        while(!q.isEmpty()) {
            Gem tmp = q.poll();
            map[tmp.i][tmp.j].offer(tmp);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new PriorityQueue[N][N];

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                map[i][j] = new PriorityQueue<>();
            }
        }

        for(int m=0;m<M;m++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            
            String d = st.nextToken();
            int dir=0;
            if(d.equals("D")) dir=1;
            else if(d.equals("L")) dir=2;
            else if(d.equals("R")) dir=3;

            int w = Integer.parseInt(st.nextToken()); 

            map[i][j].offer(new Gem(i,j,m,dir,w));     
        }

        for(int t=0;t<T;t++) {
            move();
            merge();

        }

        int max=0,cnt=0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j].size()>0) {
                    max = Math.max(map[i][j].poll().w, max);
                    cnt++;
                }
            }
        }
        System.out.println(cnt+" "+max);
    }

    static class Gem implements Comparable<Gem> {
        int i,j,n,d,w;
        public Gem(int i, int j, int n, int d, int w) {
            this.i = i;
            this.j = j;
            this.n = n;
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Gem o) {
            return o.n-this.n;
        }
    }
}