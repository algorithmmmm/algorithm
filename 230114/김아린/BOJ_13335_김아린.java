import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335_김아린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken()); //다리를 건너는 트럭의 수
		int w = Integer.parseInt(st.nextToken()); //다리의 길이
		int L = Integer.parseInt(st.nextToken()); //다리의 최대하중
		
		st = new StringTokenizer(br.readLine(), " ");
		
		Queue<Integer> trucks = new LinkedList<>(); //트럭 큐
		
		for(int i = 0; i < n; i++) {
			int ut = Integer.parseInt(st.nextToken()); //단위 시간
			trucks.add(ut);
		}
		
		//구현
		Queue<Integer> bridge = new LinkedList<>(); //다리 큐
		
		for(int i = 0; i < w; i++) { //처음에 다리 길이만큼 빈공간 채우기
			bridge.add(0);
		}
		
		int time = 0; //다리 건너는 시간
		int curWeight = 0; //다리에 올라가 있는 트럭들 무게
		
		while(!bridge.isEmpty()) {
			time++; //1초 증가
			
			curWeight -= bridge.poll(); //다리에서 나감
			
			if(!trucks.isEmpty()) {
				int p = trucks.peek();
				if(curWeight + p <= L) { //다리 위에 올라 갈 수 있으면
					int ut = trucks.poll();
					bridge.add(ut); //다리 진입
					curWeight += ut; //다리 무게 증가
				}else {
					bridge.add(0); //빈공간
				}
			}
		}
		
		//출력
		System.out.println(time);
	}

}
