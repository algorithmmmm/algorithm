package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_큰수만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String[] arr = new String[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			arr[i] = st.nextToken();
		}
		
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				String ab = a + b;
				String ba = b + a;
				
				return ba.compareTo(ab);
			}
		});
		
		StringBuilder sb = new StringBuilder();
		
		for(String s : arr) {
			sb.append(s);
		}
		
		if(arr[0].equals("0")) {
			System.out.println(0);
		} else {
			System.out.println(sb);
		}
	}

}
