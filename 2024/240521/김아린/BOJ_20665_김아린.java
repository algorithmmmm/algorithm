package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_독서실거리두기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); //독서실 좌석의 개수
		int T = Integer.parseInt(st.nextToken()); //독서실 예약자 수
		int P = Integer.parseInt(st.nextToken()); //민규가 좋아하는 좌석 번호
		
		int[][] reservation = new int[T][2];
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			String start = st.nextToken();
			String end = st.nextToken();
			
			reservation[i][0] = Integer.parseInt(start.substring(0, 2)) * 60 + Integer.parseInt(start.substring(2, 4));
			reservation[i][1] = Integer.parseInt(end.substring(0, 2)) * 60 + Integer.parseInt(end.substring(2, 4));
		}
		
		//시작 시간, 끝나는 시간 오름차순 정렬 
		Arrays.sort(reservation, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); //번호, 끝나는 시간 
		int answer = 21 * 60 - 9 * 60;
		
		for(int[] time : reservation) {
			int start = time[0];
			int end = time[1];
			
			//시간 지난 좌석 빼기 
			while(!pq.isEmpty() && pq.peek()[1] <= start) {
				pq.poll();
			}
			
			//앉을 수 있는 좌석 찾기 
			int[] dist = new int[N + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			for(int i = 1; i <= N; i++) { //좌석별 근처 가장 가까운 좌석과의 거리 구하기 
				for(int[] p : pq) {
					int num = p[0];
					dist[i] = Math.min(dist[i], Math.abs(i - num));
				}
			}
			
			int maxDist = 0;
			int maxNum = 0;
			for(int i = 1; i <= N; i++) { //가장 최대 거리를 가진 좌석 구하기 
				if(dist[i] > maxDist) {
					maxDist = dist[i];
					maxNum = i;
				}
			}
			
			//좌석 배정하기 
			pq.add(new int[] {maxNum, end});
			
			if(maxNum == P) { //배정 받은 자리가 민수 자리면, 다른 사람이 앉는만큼 빼기 
				answer -= (end - start);
			}
		}
		
		System.out.println(answer);
	}

}
