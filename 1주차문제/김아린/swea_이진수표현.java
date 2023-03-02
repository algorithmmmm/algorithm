package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * M의 이진수 표현의 마지막 N비트가 모두 1로 켜져 있는지 아닌지 판별
 */
public class swea_이진수표현 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int mask = (1 << N) - 1; //마지막 N비트가 1로 켜짐 ex) 4 : 10000 - 1 = 01111
			int result = M & mask; // ex) 47 & 1111 = 101111 & 001111 = 001111
			
			String answer = (mask == result) ? "ON" : "OFF";
			
			System.out.println("#" + t + " " + answer);
		}
	}

}
