package bdfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1068_트리 {
	static int N, target, root, count;
	static ArrayList<Integer>[] tree;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		tree = new ArrayList[N];
		visit = new boolean[N];
		for(int i=0; i<N; i++) {
			tree[i] = new ArrayList<>();
		}
		target = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent==-1) root = i;
			else if(i==target || parent==target) continue; // 건너뛰기
			else {
				tree[parent].add(i);
			}
		}
		
		dfs(root);
		if(root==target) count = 0;
		System.out.println(count);
		
	}
	
	public static void dfs(int root) {
		if(tree[root].size()==0) {
			count++;
			return;
		}
		
		for(int i : tree[root]) {
			dfs(i);
		}
	}
}
