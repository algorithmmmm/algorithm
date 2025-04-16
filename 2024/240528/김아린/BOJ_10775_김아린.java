package algorithm;

import java.io.*;

public class BOJ_공항 {
	static int[] parent;
	
	public static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		int X = find(x);
		int Y = find(y);

		if (X != Y) parent[X] = Y;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}
		
		int count = 0;
		for(int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			
			int head = find(g); //1~g 중 도킹할 수 있는 곳 
			
			if(head == 0) { //도킹 불가 
				break;
			}
						
			union(head, head - 1); //1~g중 도킹 가능한 곳 연결 
			count++;
		}
		
		System.out.println(count);
	}

}
