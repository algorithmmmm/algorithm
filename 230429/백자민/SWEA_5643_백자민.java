package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제이름: 키높이 
 * 문제 요약: 학생들의 키를 비교한 결과가 주어질 때 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 출력
 * 
 * */
 
public class SWEA_5643_백자민 {
     
    static int N,M, cnt;
     
    static ArrayList<Integer>[] front, back;
     
    static int count(int num) {
        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(num);
        boolean visited[] = new boolean[N+1];
        visited[num] = true;
        int cnt = 0;
        while(!que.isEmpty()) {
            int now = que.poll();
            for(int i : front[now]) {
                if(visited[i])
                    continue;
                visited[i] = true;
                cnt++;
                que.offer(i);
            }
        }
        que.offer(num);
        while(!que.isEmpty()) {
            int now = que.poll();
            for(int i : back[now]) {
                if(visited[i])
                    continue;
                visited[i] = true;
                cnt++;
                que.offer(i);
            }
        }
        return cnt;
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
             
            front = new ArrayList[N+1];
            back = new ArrayList[N+1];
             
            for(int n=0;n<=N;n++) { //초기화 
                front[n] = new ArrayList<Integer>();
                back[n] = new ArrayList<Integer>();
            }
             
            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                int f = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                 
                front[f].add(b);
                back[b].add(f);
            }// end input
             
            cnt = 0;
            //나보다 큰사람+작은사람 합 구하기
            for(int n=1;n<=N;n++) {
                int sum = count(n);
                if(sum == N-1)
                    cnt++;
            }
            System.out.println("#"+t+" "+cnt);
        }
    }
}
