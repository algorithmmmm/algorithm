package day230114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335_백자민 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		
		Queue<Integer> bridge = new LinkedList<>();
		for(int i=0;i<w;i++) bridge.add(0); //초기화
		
		Queue<Integer> truck = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int t=0;t<n;t++) truck.offer(Integer.parseInt(st.nextToken()));
		
		int time = 0;
		int maxWeight = 0;
		while(!bridge.isEmpty()) { //다리 위에 트럭이 없을 때까
			maxWeight -= bridge.poll();
			if(!truck.isEmpty()) {
				if(truck.peek()+maxWeight<=l) {
//					System.out.println("트럭 추가: "+truck.peek());
					maxWeight += truck.peek();
					bridge.offer(truck.poll());
				}
				else {
//					System.out.println("무너진다아: "+truck.peek());
					bridge.offer(0);
				}
			}
			time++;
			
		}
		System.out.println(time);
	}
}
