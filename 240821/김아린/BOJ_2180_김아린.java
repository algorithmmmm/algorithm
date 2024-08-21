package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_소방서의고민 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, (o1, o2) -> {
		    int a = o1[0], b = o1[1];
		    int c = o2[0], d = o2[1];

		    //기울기가 0인 것이 기울기가 1이상인 것보다 뒤
		    if(a == 0 && c != 0) return 1;
		    if(c == 0 && a != 0) return -1;
		    if(a == 0 && c == 0) return Integer.compare(b, d);

		    // b/a가 같음 : a가 큰 것이 뒤
		    double d1 = (double)b / a;
		    double d2 = (double)d / c;
		    if (Double.compare(d1, d2) == 0) {
		        return Integer.compare(a, c);
		    }

		    // 그 외 : b/a가 큰 것이 뒤
		    return Double.compare(d1, d2);
		});
		
		long t = 0;
		for(int i = 0; i < N; i++) {
			System.out.println(arr[i][0] + " " + arr[i][1]);
			t += (arr[i][0] * t) + arr[i][1];
			t %= 40000;
		}
		
		System.out.println(t);
	}

}
