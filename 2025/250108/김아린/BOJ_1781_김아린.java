package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_컵라면 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		//컵라면 수 내림차순, 데드라인 내림차순하는 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? o2[0] - o1[0] : o2[1] - o1[1]);
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int deadline = Integer.parseInt(st.nextToken());
			int cup = Integer.parseInt(st.nextToken());
			
			pq.add(new int[] {deadline, cup, (i+1)});
		}
		
		//큰 수부터 체크해나가기 위한 treeSet 초기화
		TreeSet<Integer> treeSet = new TreeSet<>();
		for(int i = 0; i <= N; i++) {
			treeSet.add(i);
		}
		
		int answer = 0;
		while(!pq.isEmpty()) {
			int[] p = pq.poll();
			int deadline = p[0];
			int cup = p[1];
			
			int maxTime = treeSet.floor(deadline); //아직 체크되지 않은 시간 중에 큰 수
			
			if(maxTime != 0 && maxTime <= deadline) { //데드라인 안에 풀 수 있음
				treeSet.remove(maxTime); //체크하기
				answer += cup;
			}
			
		}
			
		System.out.println(answer);
	}

}
