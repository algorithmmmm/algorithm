package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_이모티콘 {
	static boolean[][] visited;
	
	static class Point {
		int num; //현재 개수
		int copy; //복사한 개수
		
		Point(int num, int copy) {
			this.num = num;
			this.copy = copy;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int S = Integer.parseInt(br.readLine());
		
		visited = new boolean[S + 1][S + 1]; //현재 개수, 복사한 개수
		
		System.out.println(bfs(1, S));
	}

	public static int bfs(int i, int S) {
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(1, 1)); //처음에 복사
		visited[1][1] = true;
		
		int count = 1; //시간
		while(!queue.isEmpty()) {
			int size = queue.size(); //같은 시간 내 큐 사이즈
			
			count++;
			for(int s = 0; s < size; s++) {
				Point point = queue.poll();
				int num = point.num;
				int copy = point.copy;
				int next = 0; //다음 탐색시 이모티콘의 수
				
				//복사하는 경우
				if(!visited[num][num]) {
					queue.add(new Point(num, num));
					visited[num][num] = true;
				}
				
				//붙여넣기하는 경우
				next = num + copy;
				
				if(next <= S && !visited[next][copy]) {
					if(next == S) return count; //찾음!
					
					queue.add(new Point(next, copy));
					visited[next][copy] = true;
					
				}
				
				//삭제하는 경우
				next = num - 1;
				
				if(1 < next && !visited[next][copy]) {
					if(next == S) return count; //찾음!
					
					queue.add(new Point(next, copy));
					visited[next][copy] = true;
				}
			}
		}
		return count;
	}

}
