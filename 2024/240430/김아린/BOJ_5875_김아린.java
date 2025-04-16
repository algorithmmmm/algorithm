package algorithm;

import java.io.*;

public class BOJ_오타 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] arr = br.readLine().toCharArray();
		
		int sum = 0; //누적합 
		int openCnt = 0; // ( 개수 
		int closeCnt = 0; // ) 개수 
		int answer = 0;
		boolean finished = false;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == '(') {
				sum++;
				openCnt++;
			} else {
				sum--;
				closeCnt++;
			}
			
			if(sum == -1) { // ) 가 많음  
				answer = closeCnt;
				finished = true;
				break;
			}
			
			if(sum == 1) {
				openCnt = 0;
			}
		}
		
		if(!finished) answer = openCnt;
		
		System.out.println(answer);
	}

}
