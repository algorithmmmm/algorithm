import java.util.*;

class Solution {
    static ArrayList<Integer>[] graph; //인접리스트
    int answer = 1;
    
    public int solution(int[] info, int[][] edges) {
        //인접리스트 생성
        graph = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        //그래프 연결(단방향)
        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        
        //탐색
        ArrayList<Integer> next = new ArrayList<>(); //다음에 방문할 정점을 담은 리스트
        
        dfs(0, 0, 0, next, info); //현재 정점, 양의 수, 늑대의 수, 다음에 방문할 정점 리스트
        
        return answer;
    }
    
    public void dfs(int v, int sheep, int wolf, ArrayList<Integer> next, int[] info) {
        int count_sheep = sheep;
        int count_wolf = wolf;
        
        //양인지 늑대인지 확인해서 카운트 올리기
        if(info[v] == 0) {
            count_sheep++;
        } else {
            count_wolf++;
        }
        
        //늑대의 수가 같거나 많으면 탐색 종료
        if(count_wolf >= count_sheep) {
            return;
        }
        
        //양 최댓값 갱신
        answer = Math.max(answer, count_sheep);
        
        //다음에 방문할 정점을 담은 리스트에 자식 노드 추가
        for(int i : graph[v]) {
            next.add(i);
        }
        
        //방문할 정점 탐색
        for(int i : next) {
            //리스트 복사
            ArrayList<Integer> newNext = new ArrayList<>();
            for(int j : next) {
                newNext.add(j);
            }
            
            //자기 자신은 제외
            int idx = newNext.indexOf(i);
            newNext.remove(idx);
            
            dfs(i, count_sheep, count_wolf, newNext, info);
        }
        
    }
}