package day240604;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class BOJ_22860_백자민 {
	
	static int N,M;
	
	static HashSet<String> fileList;
	
	static HashMap<String, ArrayList<String>> folderMap;
	
	static int find(String folder) {
		int cnt = 0; //파일의 개수 카운팅
		fileList = new HashSet<>(); //파일의 종류 저장
		
		Queue<String> q = new LinkedList<>();
		q.offer(folder); //시작폴더부터 bfs 탐색
		
		while(!q.isEmpty()) { 
			String tmp = q.poll();
			
			for(String s: folderMap.get(tmp)) {
				if(folderMap.containsKey(s)) { //만약 하위에 폴더가 있다면 큐에 넣고 탐색
					q.offer(s);
					continue;
				}
				
				//하위에 있는 것이 파일일 경우 
				fileList.add(s);
				cnt++;
			}
		}
		
		return cnt;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		folderMap = new HashMap<>(); //폴더 관계를 저장할 맵
		
		for(int i=0,size=N+M;i<size;i++) {
			st = new StringTokenizer(br.readLine());
			String p = st.nextToken();
			String f = st.nextToken();
			int c = Integer.parseInt(st.nextToken());
			
			folderMap.putIfAbsent(p, new ArrayList<>());
			if(c==1 && !folderMap.containsKey(f)) //폴더일 경우 하위 폴더도 key로 등록
				folderMap.put(f, new ArrayList<>());
			
			folderMap.get(p).add(f); //폴더 p에 f추가
		}
		
		int Q = Integer.parseInt(br.readLine());
		for(int q=0;q<Q;q++) {
			String[] path = br.readLine().trim().split("/"); //경로에 있는 폴더 이름만 저장
			
			int cnt = find(path[path.length-1]); //명령어 중 가장 하위에 있는 폴더에서 탐색
			System.out.println(fileList.size()+" "+cnt);
		}
	}
}
