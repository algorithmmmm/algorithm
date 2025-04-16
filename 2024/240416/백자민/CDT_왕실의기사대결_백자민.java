package day240416;

import java.util.*;
import java.io.*;

public class CDT_왕실의기사대결_백자민 {

    static int L,N,Q;

    static int[][] map; //맵정보 저장

    static Player[] knights; //각 기사들 정보 저장
    static boolean[] isDead; //각 기사들 상태 정보 저장(F:살/T:죽)
    static int[] dmglist; //각 기사들이 받은 데미지 누계

    static Queue<movePlayer> movequeue; //이동할 기사 정보 저장할 임시큐(일괄이동)

    static int[] di = {-1,0,1,0}; //U-R-D-L
    static int[] dj = {0,1,0,-1};

    static boolean isPossible(int num, int d) { //num번 기사부터 이동시키며 데미지 계산
        Queue<Integer> q = new LinkedList<>();
        boolean flag = true;

        movequeue = new LinkedList<>();//최종적으로 이동할 기사들의 정보를 담을 큐
        boolean[] visit = new boolean[N+1];//중복이동 체크

        q.offer(num);
        visit[num] = true;

        while(!q.isEmpty()) {
            int tmp = q.poll();

            // System.out.println("지금기사: "+tmp);
            // System.out.println("원래위치: "+knights[tmp].i+" "+knights[tmp].j);

            //다음 이동 위치 - 시작점
            int si = knights[tmp].i+di[d];
            int sj = knights[tmp].j+dj[d];

            //다음 이동 위치 - 끝점
            int ei = si+knights[tmp].h-1;
            int ej = sj+knights[tmp].w-1;

            //범위 벗어날 경우
            if(si<1 || ei>L || sj<1 || ej>L) return false;

            // System.out.println("다음 이동 위치:"+si+" "+sj+" "+ei+" "+ej);

            //구역 내 있는 함정 개수(=데미지) 카운팅
            int dmg=0;
            for(int i=si;i<=ei;i++) {
                for(int j=sj;j<=ej;j++) {
                    if(map[i][j]==2) return false; //벽에 가로막히면 이동 불가
                    if(map[i][j]==1) dmg++;
                }
            }

            if(tmp==num) dmg=0; //명령받은 기사는 데미지를 받지 않음
            movequeue.offer(new movePlayer(tmp, si, sj, dmg));          

            //다른 기사를 밀치는 경우 
            for(int next=1;next<=N;next++) {
                if(visit[next] || isDead[next]) continue; //이미 이동했거나 탈락한 기사

                int nsi = knights[next].i;
                int nsj = knights[next].j;

                int nei = nsi+knights[next].h-1;
                int nej = nsj+knights[next].w-1;

                //현재 기사와 안겹치는 경우
                if(nsi>ei || nei<si || nsj>ej || nej<sj) continue;

                visit[next] = true;
                q.offer(next);
            }

        }

        return true;
    }

    static void move(int num, int dir) {
        if(isDead[num]) return;

        if(!isPossible(num, dir)) { //이동할 수 없는 경우
            return;
        }

        //기사들 일괄 이동
        while(!movequeue.isEmpty()) {
            movePlayer tmp = movequeue.poll();
            knights[tmp.n].i = tmp.i;
            knights[tmp.n].j = tmp.j;

            if(knights[tmp.n].k<=tmp.dmg) { //탈락처리
                isDead[tmp.n] = true;
            } else {
                knights[tmp.n].k-=tmp.dmg;
                dmglist[tmp.n]+=tmp.dmg;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[L+1][L+1];
        for(int i=1;i<=L;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=L;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        knights = new Player[N+1]; //1~N번 기사들 정보 저장
        isDead = new boolean[N+1];
        dmglist = new int[N+1];
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            knights[i] = new Player(r,c,k,h,w);
        }

        for(int q=0;q<Q;q++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            
            move(num, dir);
        }

        int ans=0;
        for(int i=1;i<=N;i++) {
            if(!isDead[i]) { //생존 기사의 데미지만 더함
                ans += dmglist[i];
            }
        }
        System.out.println(ans);
    }

    static class Player { //기사 객체 클래스
        int i, j, k, h, w;
        
        public Player(int i, int j, int k, int h, int w) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.h = h;
            this.w = w;
        }
    }

    static class movePlayer { //이동할 기사 정보를 저장할 객체 클래스
        int n, i, j, dmg;
        
        public movePlayer(int n, int i, int j, int dmg) {
            this.n = n;
            this.i = i;
            this.j = j;
            this.dmg = dmg;
        }
    }
}