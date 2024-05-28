package day240528;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class BOJ_10775_백자민 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		TreeSet<Integer> gates = new TreeSet<>(); //아직 열린 게이트 번호를 저장
		for(int i=1;i<=G;i++) {
			gates.add(i);
		}
		
		int ans =0; //도킹시킬 수 있는 최대 비행기 수
		for(int p=0;p<P;p++) {
			int tmp = Integer.parseInt(br.readLine());
			Integer possibleGate = gates.floor(tmp); //가능한 게이트 중 가장 큰 게이트를 선택(최적의 선택 방법)
			
			if(possibleGate==null) { //만약 도킹할 수 있는 게이트가 없다면 공항 폐쇄
				break;
			}
			
			gates.remove(possibleGate); //가능한 게이트가 있다면 해당 게이트에 비행기 도킹
			ans++;
		}
		
		System.out.println(ans);
	}
}
