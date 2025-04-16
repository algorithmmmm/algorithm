package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_저울 {
	static ArrayList<Integer>[] graph1; //인접리스트: 관계 저장(A > B)
    static ArrayList<Integer>[] graph2; //반대로 저장(B < A)
    static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		graph1 = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++) {
            graph1[i] = new ArrayList<>();
        }
        
        graph2 = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++) {
            graph2[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++) { //물건 비교 정보 저장
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	graph1[a].add(b); //A > B
            graph2[b].add(a); //B < A
        }
        
        //구현
        int[] answer = new int[N + 1];
        
        for(int i = 1; i < N + 1; i++) {
        	visited = new boolean[N + 1];
        	int sum = bfs(graph1, i); //i보다 작은 물건 개수(i포함)
        	
        	visited = new boolean[N + 1];
        	sum += bfs(graph2, i); //i보다 큰 물건 개수(i포함)
        	
        	answer[i] = N - sum - 1; //비교 결과를 알 수 없는 물건의 개수
        }
        
        //출력
        for(int i = 1; i < N + 1; i++) {
        	System.out.println(answer[i]);
        }
	}
	
	public static int bfs(ArrayList<Integer>[] graph, int v) {
        Queue<Integer> queue = new LinkedList<>();
         
        queue.add(v);
        visited[v] = true;
         
        int count = 0; //비교 가능 물건 개수
        while(!queue.isEmpty()) {
            int p = queue.poll();
             
            for(int i : graph[p]) {
                if(!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
         
        return count;
    }

}
