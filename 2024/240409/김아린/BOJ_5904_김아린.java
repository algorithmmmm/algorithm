package algorithm;

import java.io.*;

public class BOJ_Moo게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		//1. N번째 글자를 찾을 수 있는 k 구하기 
		//S(0)의 길이 = 3, S(k)의 길이 = S(k - 1)의 길이 * 2 + (k + 4)
		int k = 0;
		int length = 3;
		
		while(length < N) {
			length = length * 2 + (k + 4);
			k++;
		}
		
		//2. S(k) 수열에서 위치 찾기
		System.out.println(find(k, length, N)); //k, 수열 전체 길이, 찾는 위치 
	}
	
	public static String find(int k, int length, int N) {
		int midIdx = (length - (k + 3)) / 2;
		
		if(1 <= N && N <= midIdx) { //왼쪽 존재
			return find(k - 1, midIdx, N);
		}else if(midIdx < N && N <= midIdx + k + 3) { //가운데 존재
			return N == midIdx + 1 ? "m" : "o";
		} else { //오른쪽 존재 
			return find(k - 1, midIdx, N - midIdx - (k + 3));
		}
	}

}
