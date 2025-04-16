package day240702;

import java.util.*;
import java.io.*;

public class CDT_홍팀과백팀_백자민 {

    static int N,M;

    static int[] team, opposite; 

    static int find(int x) {
        if(team[x]==x) {
            return x;
        }

        return team[x] = find(team[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        team[x] = y;
    } 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        opposite = new int[N+1]; //나와 겨뤘던 사람 정보 저장
        team = new int[N+1]; //각 번호의 사람 팀 정보 저장(홍팀:1, 백팀: 2)
        for(int i=1;i<=N;i++) {
            team[i] = i;
        } //init

        int ans = 1;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(find(a)==find(b)) { //경기에 나온 두 사람이 같은 팀에 속한 경우
                ans = 0;
                break;
            } else if(opposite[a]==0 && opposite[b]==0) { //두 사람 모두 첫출전
                //서로 다른 팀임을 기록
                opposite[a] = b;
                opposite[b] = a;
            } else if(opposite[a]==0) { //a만 첫출전
                opposite[a] = b;
                union(a, opposite[b]); //b의 상대팀과 같은 팀
            } else if(opposite[b]==0) { //b만 첫출전
                opposite[b] = a;
                union(b, opposite[a]); //a의 상대팀과 같은 팀
            } else { //두 사람 모두 출전 기록이 있음
                //서로의 상대팀과 같은 팀으로 기록 
                union(a, opposite[b]);
                union(b, opposite[a]); 

                if(find(a)==find(b)) { //모순이 발생한 경우
                    ans = 0;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
