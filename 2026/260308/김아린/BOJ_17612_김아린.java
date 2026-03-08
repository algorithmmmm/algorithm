package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_쇼핑몰 {
	
	static class Member {
		int id, wp, co; // (고객번호, 누적 시간, 계산대 번호)
		
		Member(int id, int wp, int co) {
			this.id = id;
			this.wp = wp;
			this.co = co;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 고객 수
        int K = Integer.parseInt(st.nextToken()); // 계산대 수
        
        // 입장 큐 : 누적 시간 오름차순, 같으면 카트 번호 오름차순 정렬
        Queue<Member> pq = new PriorityQueue<>((o1, o2) -> o1.wp == o2.wp ? o1.co - o2.co : o1.wp - o2.wp);
        // 결과 큐 : 누적 시간 오름차순, 같으면 카트 번호 내림차순 정렬
        Queue<Member> result = new PriorityQueue<>((o1, o2) -> o1.wp == o2.wp ? o2.co - o1.co : o1.wp - o2.wp);
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int id = Integer.parseInt(st.nextToken()); // 고객 번호
        	int w = Integer.parseInt(st.nextToken()); // 구입한 물건의 수
        	
        	if(i < K) {
        		pq.add(new Member(id, w, i + 1));
        	} else {
        		Member member = pq.poll();
        		pq.add(new Member(id, member.wp + w, member.co));
        	 	result.add(new Member(member.id, member.wp, member.co));
        	}
        }
        
        // 남은거 결과에 넣기
        while(!pq.isEmpty()) {
        	Member member = pq.poll();
        	result.add(new Member(member.id, member.wp, member.co));
        }
        
        // 출력
        long answer = 0;
        int i = 1;
        while(!result.isEmpty()) {
        	int id = result.poll().id;
        	answer += (long)i * id;
        	i++;
        }
        
        System.out.println(answer);
	}

}
