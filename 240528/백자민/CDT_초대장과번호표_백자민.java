package day240528;

import java.util.*;
import java.io.*;

public class CDT_초대장과번호표_백자민 {

    static int N,G;

    static HashSet<Integer> invite;

    static ArrayList<Integer>[] grouplist;
    static HashSet<Integer>[] graph;

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1); //1번 사람은 무조건 초대장을 받음
        invite.add(1);

        while(!q.isEmpty()) {
            int tmp = q.poll();

            //tmp번 사람이 속한 그룹에서 현재 사람을 제거
            for(int group: grouplist[tmp]) { 
                graph[group].remove(tmp);

                //만약 현재 그룹에서 1명만 남았다면 그 사람은 반드시 초대장을 받아야 함
                if(graph[group].size()==1) {
                    int last = graph[group].iterator().next();
                    if(invite.contains(last)) continue; //이미 초대한 사람이라면 큐에 넣지 않음

                    q.offer(last);
                    invite.add(last);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        grouplist = new ArrayList[N+1]; //각 사람별로 속한 그룹 저장
        graph = new HashSet[G]; //그룹 정보 저장

        //init
        for(int i=1;i<=N;i++) {
            grouplist[i] = new ArrayList<>();
        }
        for(int g=0;g<G;g++) {
            graph[g] = new HashSet<>();
        }

        for(int g=0;g<G;g++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());

            for(int i=0;i<size;i++) {
                int n = Integer.parseInt(st.nextToken());
                graph[g].add(n);
                grouplist[n].add(g);
            }
        } //end input

        invite = new HashSet<>(); //초대장을 받은 사람들을 저장할 hashset
        bfs();

        System.out.println(invite.size());
    }
}
