//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class 촛불_이벤트 {
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		int T = Integer.parseInt(br.readLine());
//		
//		for(int t=1;t<=T;t++) {
//			long N = Long.parseLong(br.readLine());
//			long K = -1;
//			
//			long left = 0;
//			long right = 10000000000L;
//			
//			while(left<=right) {
//				long mid = (left+right)/2;
//				long temp = (mid*(mid+1))/2;
//				
////				if(temp==N) {
////					K = mid;
////					break;
////				} else if(temp>N) {
////					right = mid-1;
////				} else {
////					left = mid+1;
////				}
//				if(temp<=N) {
//					K = mid;
//					left = mid+1;
//				} else {
//					right = mid-1;
//				}
//			}
//			
//			if(N!=(K*(K+1))/2) K=-1;
//			
//			sb.append("#"+t+" "+K+"\n");
//		}
//		
//		System.out.println(sb.toString());
//	}
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 촛불_이벤트 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			long N = Long.parseLong(br.readLine());
			long K = -1;
			
			long left = 0;
			long right = (1l<<31) -1;
			long mid = 0;
			
			while(left<=right) {
				mid = (left+right)/2;
				long temp = (mid*(mid+1))/2;
				
				if(temp==N) {
					K = mid;
					break;
				} else if(temp>N) {
					right = mid-1;
				} else {
					left = mid+1;
				}
			}
			sb.append("#"+t+" "+K+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
