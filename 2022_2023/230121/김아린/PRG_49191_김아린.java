import java.util.*;

class PRG_49191_김아린 {
    static ArrayList<Integer>[] adList; //인접리스트: 선수별 결과 관계 저장(A가 B이김)
    static ArrayList<Integer>[] reversedList; //반대의 결과 저장(B가 A에게 짐)
    static boolean[] visited;
    
    public int solution(int n, int[][] results) {
        adList = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++) {
            adList[i] = new ArrayList<>();
        }
        
        reversedList = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++) {
            reversedList[i] = new ArrayList<>();
        }
        
        
        for(int i = 0; i < results.length; i++) {
            int a = results[i][0];
            int b = results[i][1];
            
            adList[a].add(b); //A가 B이김
            reversedList[b].add(a); //B가 A에게 짐
        }
        
        //구현
        //자신의 몇 번째인지 알 수 있는 경우 : 들어오는 간선 개수(패배) + 본인 기준 탐색했을 때 연결되는 개수(승리) = N - 1
        int answer = 0;
        for(int i = 1; i < n + 1; i++) {
            visited = new boolean[n + 1];
            int win = bfs(adList, i); //내 기준으로 이기는 선수 숫자 구하기
                 
            visited = new boolean[n + 1];               
            int lose = bfs(reversedList, i); //내 기준으로 반대로 탐색해서 지는 선수 숫자 구하기
                 
            if(lose + win == n - 1) {
                answer++;
            }
        }
        
        //결과
        return answer;
    }
    
    public static int bfs(ArrayList<Integer>[] list, int v) {
        Queue<Integer> queue = new LinkedList<>();
         
        queue.add(v);
        visited[v] = true;
         
        int count = 0; //탐색한 선수 숫자
        while(!queue.isEmpty()) {
            int p = queue.poll();
             
            for(int n : list[p]) {
                if(!visited[n]) {
                    queue.add(n);
                    visited[n] = true;
                    count++;
                }
            }
        }
         
        return count;
    }
}
