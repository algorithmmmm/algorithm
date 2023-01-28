package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16987_김아린 {
	static int N; //계란의 수
	static Egg[] eggs; //일렬로 놓인 계란들
	static int max; //깰 수 있는 계란의 최대 개수
	
	static class Egg {
		int d; //내구도
		int w; //무게
		
		public Egg(int d, int w) {
			super();
			this.d = d;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N]; 
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			eggs[i] = new Egg(d, w);
		}
		
		max = 0;
		hit(0); //시작: 가장 왼쪽의 계란으로 치기
		
		System.out.println(max);
	}

	public static void hit(int idx) {
		if(idx == N) { //종료 조건: 최근 든 계란이 가장 오른쪽에 위치할 경우
			int count = 0; //깨진 계란 개수
			
			for(Egg egg : eggs) { //깨진 계란 탐색
				if(egg.d <= 0) count++;
			}
			
			max = Math.max(max, count); //최댓값 갱신
			return;
		}
		
//		if(eggs[idx].d <= 0) { //여기에 두면 tc2번 통과 안됨
//			hit(idx + 1);
//		}
		
		for(int i = 0; i < N; i++) { //모든 계란을 탐색
			if(eggs[idx].d <= 0) { //손에 든 계란이 깨져있음
				break;
			}
			
			if(eggs[i].d > 0 && i != idx) { //깨지지않은 계란이면 친다 & 자신을 치면 안됨!
				eggs[idx].d -= eggs[i].w;
				eggs[i].d -= eggs[idx].w;
				
				hit(idx + 1); //최근 든 계란의 오른쪽 계란으로 치기
				
				eggs[idx].d += eggs[i].w;
				eggs[i].d += eggs[idx].w;
			}
		}
		
		hit(idx + 1); //치지 않고 넘어가기(오른쪽 계란으로 치기)
	}

}
