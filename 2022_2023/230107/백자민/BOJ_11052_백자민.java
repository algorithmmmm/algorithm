package day230107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * knapsack 방식을 이용해서 원하는 카드 개수를 만족할 때 금액의 합의 최댓값을 구한다.
 * */

public class BOJ_11052_백자민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] cards = new int[1001];
		for(int i=1;i<=N;i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=i/2;j++) {
				cards[i] = Math.max(cards[i], cards[i-j]+cards[j]);
			}
		}
		
		System.out.println(cards[N]);
	}
}
