package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_줄임말 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine(); // 줄임말
        String T = br.readLine();
        
        // T에 있는 각 문자 위치 리스트
        List<Integer>[] arr = new ArrayList[26];
        for(int i = 0; i < 26; i++) {
        	arr[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < T.length(); i++) {
        	int c = T.charAt(i) - 'a';
        	arr[c].add(i);
        }
        
        int answer = 1;
        int idx = -1; // 문자 현재 위치(T)
        for(int i = 0; i < S.length(); i++) {
        	int c = S.charAt(i) - 'a';
        	
        	// 해당 문자가 T에 없는 경우
        	if(arr[c].isEmpty()) {
        		answer = -1;
        		break;
        	}
        	
        	// 현재 위치 이후의 가장 작은 인덱스 찾기
        	int nextIdx = binarySearch(arr[c], idx);
        	
        	if(nextIdx == -1) { // 없음
        		// 문자열 T 더 붙이기
        		answer++;
        		idx = arr[c].get(0);
        	}else {
        		idx = nextIdx;
        	}
        	
        }
        
        System.out.println(answer);
        
	}

	private static int binarySearch(List<Integer> list, int target) {
		int left = 0, right = list.size();
		
        while(left < right) {
            int mid = (left + right) / 2;
            if(list.get(mid) > target) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        
        // 찾고자 하는 값이 존재하지 않는 경우는 -1
        return left < list.size() ? list.get(left) : -1;
	}

}
