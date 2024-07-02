package day240702;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6068_백자민 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Task> pq = new PriorityQueue<>();
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			pq.offer(new Task(t, s));
		} //end input
		
		int sum=0, minTime = Integer.MAX_VALUE; //sum:총 업무 시간, minTime:일어나야 하는 시간
		boolean isFinish = true;
		
		while(!pq.isEmpty()) { //먼저 끝내야 할 일을 먼저 처리
			Task tmp = pq.poll();
			
			sum += tmp.time; //총 업무 시간 갱신
			minTime = Math.min(tmp.endTime-sum, minTime); //일어나야 하는 시간 = 업무를 시작해야 하는 시간 중 가장 늦은 시간
			
			if(sum > tmp.endTime) { //총 업무 시간이 현재 업무 종료 시간보다 큰 경우 = 제시간에 일을 끝낼 수 없음
				isFinish = false;
				break;
			}
		}
		
		System.out.println(isFinish?minTime:-1);
	}
	
	static class Task implements Comparable<Task> {
		int time, endTime;
		
		public Task(int time, int endTime) {
			this.time = time;
			this.endTime = endTime;
		}
		
		@Override
		public int compareTo(Task o) {
			return this.endTime-o.endTime; //끝내야 하는 시간 기준 오름차순 정렬
		}
	}
}
