package day240514;

import java.util.*;
import java.io.*;

public class CDT_사이클판별_백자민 {

    static int N,M;

    static int[] indegree; //각 노드별 진입 차수

    static ArrayList<Integer>[] graph; //간선 정보 저장

    static boolean sort() { //위상정렬을 수행하는 함수. 사이클이 있다면 true를, 없다면 false를 리턴 
        Queue<Integer> q = new LinkedList<>();
        int cnt=0; //정렬을 완료한 노드 개수 카운팅

        //진입 차수가 0인 노드부터 정렬 시작
        for(int i=1;i<=N;i++) {
            if(indegree[i]==0) {
                q.offer(i);
                cnt++;
            }
        }

        while(!q.isEmpty()) {
            int tmp = q.poll();

            for(int next: graph[tmp]) { //연결된 모든 노드를 탐색하며 진입 차수를 감소시킴
                indegree[next]--;

                if(indegree[next]==0) { //진입 차수가 0이 되었다면 큐에 넣고, 해당 노드의 정렬이 완료되었으므로 카운팅
                    q.offer(next);
                    cnt++;
                }
            }
        }

        if(cnt<N) { //모든 노드를 정렬하기 전에 큐가 비었다면 사이클이 존재하는 그래프이다. 
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int m=0;m<M;m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            indegree[b]++;
        } //end input

        System.out.println(sort()?"Exists":"Not Exists"); //위상정렬을 수행한 결과에 따라 정답 출력
    }
}
