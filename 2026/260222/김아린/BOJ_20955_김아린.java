package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_민서의응급수술 {
	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        parent = new int[N + 1];
        
        for(int i = 0; i < parent.length; i++) { // 부모 노드 초기화
        	parent[i] = i;
        }
        
        int answer = 0;
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            if(find(u) == find(v)) { // 사이클이 생기는 경우 연결 끊기
            	answer++;
            } else {
            	union(u, v);
            }
        }
        
        // 연결해야 하는 뉴런 개수 구하기
        for(int i = 1; i <= N; i++) {
        	if(parent[i] == i) answer++;
        }
        
        System.out.println(answer - 1);
        
	}
	
	public static int find(int x) {
		if(parent[x] == x) return x;
		return find(parent[x]);
	}
	
	static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x <= y) parent[y] = x;
		else parent[x] = y;
    }
}
