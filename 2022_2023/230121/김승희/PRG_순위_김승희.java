
import java.util.*;
class Solution {
    static boolean[] visit;
    public int solution(int n, int[][] results) {
        int answer = 0;
        // adjWin[i][j] = i가 j를 이겼다.
        // adjLose[i][j] = j가 i를 이겼다.
        ArrayList<ArrayList<Integer>> adjWin = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adjLose = new ArrayList<>();
        
        for(int i=0; i < n+1; i++){
            adjWin.add(new ArrayList<Integer>());
            adjLose.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < results.length; i++){
            adjWin.get(results[i][0]).add(results[i][1]);
            adjLose.get(results[i][1]).add(results[i][0]);
        }
        
        for(int i = 1; i < n+1; i++){
            visit = new boolean[n+1];
            BFS(i, adjWin);
            
            BFS(i, adjLose);
            if(check(visit)){
                answer++;
            }
        }
        
        return answer;
    }
    static void BFS(int start, ArrayList<ArrayList<Integer>> adj){
        visit[start] = true;
        Queue<Integer> Q = new ArrayDeque<>();
        Q.add(start);
        while(!Q.isEmpty()){
            int pop = Q.poll();
            for(int i = 0; i < adj.get(pop).size(); i++){
                int temp = adj.get(pop).get(i);
                if(visit[temp]) continue;
                visit[temp] = true;
                Q.add(temp);
            }
        }
    }
    static boolean check(boolean[] visit){
        for(int i = 1; i < visit.length; i++){
            if(!visit[i]) return false;
        }
        return true;
    }
    
}