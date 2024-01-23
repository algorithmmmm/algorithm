package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_IPv6 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		if(input.contains("::")) { // ::가 있음
			input = input.replace("::", ":group:"); // ::를 치환함
		}
			
		List<String> list = new ArrayList<>(Arrays.asList(input.split(":"))); // :로 분리함
		List<String> fullIpList = new ArrayList<>(); //축약되지 않은 형태의 주소 리스트
		
		for(String str : list) {
			if(str.isEmpty()) continue; //str이 ""이면 최종 주소에 포함x
			
			while(str.length() <4) { //앞에 0 붙여서 4자리로 만들기
				str = "0" + str;
			}
			
			fullIpList.add(str); //축약 푼 문자열 추가
		}
		
		if(fullIpList.contains("group")) { //축약 구간이 있음
			int idx = fullIpList.indexOf("group");
			
			fullIpList.remove(idx); //대체어 뺴고
			
			while (fullIpList.size() < 8) { //8자리 될때까지 축약된 0 구간 추가
				fullIpList.add(idx, "0000");
            }
		}
		
		//IP 주소 형태로 출력
		System.out.println(String.join(":", fullIpList));
	}
}
