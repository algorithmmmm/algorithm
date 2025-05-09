package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_싸지방에간준하 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 시작 시간, 끝나는 시간 오름차순 정렬
		Arrays.sort(arr, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

		// (자리 번호, 끝나는 시간)이 삽입되어 끝나는 시간 오름차순 정렬되는 우선순위 큐
		PriorityQueue<int[]> pq1 = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		
		HashMap<Integer, Integer> map = new HashMap<>(); // 번호, 사람 수 기록
		int idx = 0; // 자리 번호
		
		PriorityQueue<Integer> pq2 = new PriorityQueue<>(); // 자리가 난 큐
		
		for(int i = 0; i < N; i++) {
			while(!pq1.isEmpty() && arr[i][0] >= pq1.peek()[1]) { // 사용 지난 컴퓨터 확인
				int[] p  = pq1.poll();
				pq2.add(p[0]);
			}
			
			if(pq2.isEmpty()) { // 새로운 컴퓨터 필요
				pq1.add(new int[]{++idx, arr[i][1]});
				map.put(idx, 1);
			} else { // 기존 자리 사용
				int p = pq2.poll();
				pq1.add(new int[]{p, arr[i][1]});
				map.put(p, map.get(p) + 1);
			}
		}
		
		// 출력
		System.out.println(idx);
		
		StringBuilder sb = new StringBuilder();
		for(int key : map.keySet()) {
			sb.append(map.get(key) + " ");
		}
		System.out.println(sb);
	}

}
