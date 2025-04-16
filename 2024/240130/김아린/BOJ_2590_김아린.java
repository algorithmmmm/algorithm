package algorithm;

import java.io.*;

public class BOJ_색종이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] paper = new int[6];
		
		for(int i = 0; i < 6; i++) {
			paper[i] = Integer.parseInt(br.readLine());
		}
		
		int answer = paper[5]; //길이 6cm 붙임 
		
		while(paper[4] > 0) { //길이 5cm 붙임 
			paper[4]--;
			
			int one = 36 - 25; //11개 남음
			if(paper[0] > 0) { //길이 1cm 붙임 
				paper[0] = paper[0] > one ? paper[0] - one : 0; //11개씩 빼기
			}
			
			answer++;
		}
		
		while(paper[3] > 0) { //길이 4cm 붙임 
			paper[3]--;
			
			int two = 5; //2cm 5개 가능
			int one = 36 - 16 - 4 * Math.min(two, paper[1]); //1cm 몇 개 남는지
			
			if(paper[1] > 0) { //길이 2cm 붙임 
				paper[1] = paper[1] > two ? paper[1] - two : 0; //5개씩 빼기
			}
			
			if(paper[0] > 0) { //길이 1cm 붙임 
				paper[0] = paper[0] > one ? paper[0] - one : 0; //one개씩 빼기
			}
			
			answer++;
		}
		
		while(paper[2] > 0) { //길이 3cm 붙임 
			int three = 4; //3cm 4개 가능
			int one = 36 - 9 * Math.min(three, paper[2]); //1cm 몇 개 남는지
			int two = 0;
			
			if(paper[2] >= three) {
				paper[2] -= three; //4개씩 빼기
			} else if(paper[2] == 3) { //3cm 3개 가능
				paper[2] = 0;
				
				two = 1; //2cm 1개 가능
				if(paper[1] > 0) { //길이 2cm 붙임 
					paper[1] = paper[1] > two ? paper[1] - two : 0; //1개씩 빼기
				}
				
				one -= 4 * Math.min(two, paper[1]);
			} else if(paper[2] == 2) { //3cm 2개 가능
				paper[2] = 0;
				
				two = 3; //2cm 3개 가능
				if(paper[1] > 0) { //길이 2cm 붙임 
					paper[1] = paper[1] > two ? paper[1] - two : 0; //3개씩 빼기
				}
				
				one -= 4 * Math.min(two, paper[1]);
			} else if(paper[2] == 1) { //3cm 1개 가능
				paper[2] = 0;
				
				two = 5; //2cm 5개 가능
				if(paper[1] > 0) { //길이 2cm 붙임 
					paper[1] = paper[1] > two ? paper[1] - two : 0; //5개씩 빼기
				}
				
				one -= 4 * Math.min(two, paper[1]);
			}
			
			if(paper[0] > 0) { //길이 1cm 붙임 
				paper[0] = paper[0] > one ? paper[0] - one : 0; //one개씩 빼기
			}
			
			answer++;
		}
		
		while(paper[1] > 0) { //길이 2cm 붙임 
			int two = 9; //2cm 9개 가능
			int one = 36 - 4 * Math.min(two, paper[1]);
			
			paper[1] = paper[1] > two ? paper[1] - two : 0; //9개씩 빼기
			
			if(paper[0] > 0) { //길이 1cm 붙임 
				paper[0] = paper[0] > one ? paper[0] - one : 0; //one개씩 빼기
			}
			
			answer++;
		}
		
		while(paper[0] > 0) { //길이 1cm 붙임 
			int one = 36; //1cm 36개 가능
			paper[0] = paper[0] > one ? paper[0] - one : 0; //36개씩 빼기
			answer++;
		}
		
		System.out.println(answer);
	}

}
