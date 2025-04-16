package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_보석도둑 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] jewel = new int[N][2]; //보석 무게, 가격 
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			jewel[i][0] = Integer.parseInt(st.nextToken());
			jewel[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] weight = new int[K]; //각 가방에 담을 수 있는 최대 무게 
		
		for(int i = 0; i < K; i++) {
			weight[i] = Integer.parseInt(br.readLine());
		}
		
		//구현
		Arrays.sort(jewel, (o1, o2) -> o1[0] - o2[0]); //보석 무게 오름차순 정렬 
		Arrays.sort(weight); //가방 무게 오름차순 정렬  
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1); //가격 내림차순 
		long sum = 0;
		
		int idx = 0;
		for(int i = 0; i < K; i++) {
			while(idx < N && jewel[idx][0] <= weight[i]) { //무게 같을때까지 pq에 담기  
				pq.add(jewel[idx][1]);
				idx++;
			}
			
			if(!pq.isEmpty()) {
				sum += pq.poll(); //하나 빼서 합에 누적 
			}
		}
		
		System.out.println(sum);
	}

}
