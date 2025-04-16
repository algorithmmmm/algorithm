package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_영어읽기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<String, Integer> map = new HashMap<>(); //정렬된 단어, 개수
		
		//단어
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			String voca = br.readLine();
			String sortedVoca = transformVoca(voca); //문자 변형하기
			map.put(sortedVoca, map.getOrDefault(sortedVoca, 0) + 1); //해당 문자 개수와 함께 map에 저장
		}
		
		//문장
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			int answer = 1; //정답: 경우의 수
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			while(st.hasMoreTokens()) {
				String voca = st.nextToken();
				String sortedVoca = transformVoca(voca); //문자 변형하기
				answer *= map.getOrDefault(sortedVoca, 0); //경우의 수에 곱하기
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static String transformVoca(String voca) { //문자를 첫글자와 마지막 글자를 빼고 정렬
		char[] chs = voca.toCharArray();
		
		if(chs.length > 1) {
			Arrays.sort(chs, 1, chs.length - 1);
		}
		
		String sortedVoca = String.valueOf(chs);
		
		return sortedVoca;
	}

}
