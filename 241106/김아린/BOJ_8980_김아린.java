package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_택배 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //마을 수
		int C = Integer.parseInt(st.nextToken()); //트럭 용량
		int M = Integer.parseInt(br.readLine()); //박스 정보의 개수
		
		int[][] box = new int[M + 1][3];
		
		for(int i = 1; i <= M ; i++) {
			st = new StringTokenizer(br.readLine());
			box[i][0] = Integer.parseInt(st.nextToken());
			box[i][1] = Integer.parseInt(st.nextToken());
			box[i][2] = Integer.parseInt(st.nextToken());
		}
		
		//구현
		//받는 마을 오름차순 정렬, 같으면 보내는 마을 오름차순 정렬 
		Arrays.sort(box, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
		
		//마을별 남은 용량 저장 배열
		int[] cap = new int[N + 1];
		Arrays.fill(cap, C);
		
		int answer = 0;
		for(int i = 1; i <= M ; i++) {
			int t1 = box[i][0];
			int t2 = box[i][1];
			int num = box[i][2];
			
			//보낼 수 있는 최선의 택배 용량 구하기
			int bestCap = 10000;
			for(int j = t1; j < t2; j++) {
				bestCap = Math.min(bestCap, cap[j]);
			}
			
			int besong = Math.min(bestCap, num); //최종적으로 배송할 수 있는 용량
			
			//보내는 마을~받는 마을 사이 남은 용량 빼기
			for(int j = t1; j < t2; j++) {
				cap[j] -= besong;
			}
			
			//택배 배송 완료
			answer += besong;
		}
		
		System.out.println(answer);
	}

}
