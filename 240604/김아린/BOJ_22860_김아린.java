package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_폴더정리small {
	static HashMap<String, ArrayList<String>> dirMap;
	static HashMap<String, Integer> isFolderMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//폴더 연결하기 
		dirMap = new HashMap<>(); //이름, 하위 폴더 or 파일 
		isFolderMap = new HashMap<>(); //이름, 폴더 여부 
		
		for(int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			String P = st.nextToken();
			String F = st.nextToken();
			int isFolder = Integer.parseInt(st.nextToken());
			
			if(!dirMap.containsKey(P)) {
				dirMap.put(P, new ArrayList<>());
			}
			dirMap.get(P).add(F);
			
			if(!isFolderMap.containsKey(F)) {
				isFolderMap.put(F, isFolder);
			}
		}
		
		//폴더 탐색하기
		StringBuilder sb = new StringBuilder();
		
		int Q = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), "/");
			
			String folder = "";
			while(st.hasMoreTokens()) {
				folder = st.nextToken();
			}
			
			int[] answer = bfs(folder);
			sb.append(answer[0] + " " + answer[1] + "\n");
		}
		
		System.out.println(sb);
	}

	public static int[] bfs(String start) {
		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		
		HashSet<String> fileSet = new HashSet<>(); //파일 종류 저장 
		int fileCnt = 0; //파일의 총 개수
		
		while(!queue.isEmpty()) {
			String p = queue.poll();
			
			if(dirMap.get(p) == null) continue; //하위에 없으면 큐에 넣지 않음 
			
			for(String name : dirMap.get(p)) {
				if(isFolderMap.get(name) == 0) { //파일이면 
					fileCnt++; //파일 카운트 증가 
					if(!fileSet.contains(p)) fileSet.add(name); //새로운 종류이면 새로 추가 
				} else { //폴더이면 큐에 넣어서 탐색 
					queue.add(name);
				}
			}
		}
		
		return new int[] {fileSet.size(), fileCnt};
	}

}
