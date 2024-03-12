package day240312;

import java.io.*;
import java.util.*;

public class CDT_트리판별_백자민 {

    static int M,root;

    static HashMap<Integer, Integer> map;//들어오는 간선 개수 저장

    static ArrayList<Integer>[] graph;

    static boolean findPath() { //루트 노드부터 경로 탐색

        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[10001];

        q.offer(root);
        visit[root] = true;
        int cnt = 1; //탐색한 노드 개수 카운팅

        while(!q.isEmpty()) {
            int tmp = q.poll();

            for(int next: graph[tmp]) {
                if(visit[next]) { //사이클이 생긴 경우 
                    return false;
                }
                q.offer(next);
                visit[next] = true;
                cnt++;
            }
        }

        if(cnt!=map.size()) { //루트노드에서 모든 노드에 도달할 수 없음
            return false; 
        }

        return true;
    }

    static boolean findRoot() {
        root = -1;
        for(Map.Entry<Integer, Integer> e: map.entrySet()) {
            if(e.getValue()==0) { //진입 간선이 없는 노드 = 루트노드
                if(root>0) //이미 루트 노드를 발견한 경우(1,2번 조건 위배)
                    return false;
                root = e.getKey();
            }
        }

        if(root<0)
            return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[10001];
        for(int i=1;i<=10000;i++) {
            graph[i] = new ArrayList<>();
        }

        map = new HashMap<>();
        StringTokenizer st;
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            map.put(b, map.getOrDefault(b,0)+1);
            map.put(a, map.getOrDefault(a,0)); //루트노드일 경우 0 저장 
        } //end input

        int ans=1;
        //1번&2번 조건 체크 및 루트노드 찾기
        if(!findRoot()) {
            ans=0;
        } else if(!findPath()) { //경로 탐색(3번 조건 체크)
            ans=0;
        }

        System.out.println(ans);
    }
}