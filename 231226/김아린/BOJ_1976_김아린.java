package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_여행가자 {
	static int N;
	static int[] parent;
	
	static void union(int a, int b) {
	    int A = find(a);
	    int B = find(b);

	    if (A != B) parent[B] = A;
	}

	static int find(int a) {
	    if (parent[a] == a) return a;
	    return parent[a] = find(parent[a]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		//초기화
		parent = new int[N + 1];
		
		for (int i = 1; i < N + 1; i++) {
		    parent[i] = i;
		}
		
		//실행
		for(int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j < N + 1; j++) {
				int info = Integer.parseInt(st.nextToken());
				
				if(info == 1) { //연결되어 있음
					if (find(i) != find(j)) {
					       union(i, j);
					}
				}
			}
		}
		
		//여행 계획 확인
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int S = find(start);
		
		boolean isPossible = true;
		for(int i = 1; i < M; i++) {
			int city = Integer.parseInt(st.nextToken());
			
			if(S != find(city)) {
				isPossible = false;
				break;
			}
		}
		
		//출력
		if(isPossible) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
