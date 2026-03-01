package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_4연산 {
	
	static class Node {
		long n;
		String str;
		
		Node(long n, String str) {
			this.n = n;
			this.str = str;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long s = Long.parseLong(st.nextToken());
        long t = Long.parseLong(st.nextToken());

        String answer = "0";
        if(s != t) {
        	answer = bfs(s, t);
        }
        
        System.out.println(answer);
	}

	private static String bfs(long s, long t) {
		Queue<Node> queue = new LinkedList<>();
		HashSet<Long> visited = new HashSet<>();
		
		queue.add(new Node(s, ""));
		visited.add(s);
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			long p = node.n;
			String str = node.str;
			
			if(p == t) {
				return str;
			}
			
			// *
			if(!visited.contains(p * p)) {
				queue.add(new Node(p * p, str + "*"));
				visited.add(p * p);
			}
			
			// +
			if(!visited.contains(p + p)) {
				queue.add(new Node(p + p, str + "+"));
				visited.add(p + p);
			}
			
			// -
			if(!visited.contains(p - p)) {
				queue.add(new Node(p - p, str + "-"));
				visited.add(p - p);
			}
			
			// /
			if(p != 0 && !visited.contains(p / p)) {
				queue.add(new Node(p / p, str + "/"));
				visited.add(p / p);
			}	
		}
		
		return "-1";
	}

}
