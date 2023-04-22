import java.util.*;

class Solution {
    static boolean[] visited; //공항 방문 여부
	static ArrayList<String> routes; //가능한 모든 경로 리스트
    
    public String[] solution(String[][] tickets) {
		visited = new boolean[tickets.length + 1];
		routes = new ArrayList<String>();

		dfs(tickets, "ICN", "ICN", 0); //tickets, 출발 공항, 경로, depth
		
		Collections.sort(routes); //알파벳 순서가 앞서는 경로로 정렬
		String[] answer = routes.get(0).split(" "); //가장 앞서는 경로가 정답

        return answer;
    }

	public void dfs(String[][] tickets, String airport, String route, int depth) {
		if(depth == tickets.length) { //모든 도시 방문했으면
			routes.add(route); //가능한 경로에 저장
			return;
		}
		
		for(int i = 0; i < tickets.length; i++) {
			if(!visited[i] && tickets[i][0].equals(airport)) { //a공항에서 b공항으로 가는 항공권이 있음
				visited[i] = true;
				dfs(tickets, tickets[i][1], route + " " + tickets[i][1], depth + 1); //문자열에 누적해서 탐색함
				visited[i] = false;
			}
		}
		
	}
}