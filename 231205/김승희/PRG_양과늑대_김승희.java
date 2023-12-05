import java.util.*;
class Solution {
    static int answer;
    static ArrayList<ArrayList<Integer>> adj; 

    public int solution(int[] info, int[][] edges) {
        adj = new ArrayList<>();
        for(int i = 0; i < info.length; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        
        answer = 0;
        int[][] visit = new int[info.length][2];
        recursion(0, 1, 0, visit, info);
        return answer;
    }
    static void recursion(int node, int sheep, int wolf, int[][] visit, int[] info){
        // 방문 체크
        visit[node][0] = sheep;
        visit[node][1] = wolf;
        answer = Math.max(answer, sheep);
        for(int i = 0; i < adj.get(node).size(); i++){
            int near = adj.get(node).get(i);
            if(info[near] == 0 && (visit[near][0] == 0 && visit[near][1] == 0)) {   // 양인 경우
                recursion(near, sheep+1, wolf, deepcopy(visit), info);
            }else if(info[near] == 1 && (visit[near][0] == 0 && visit[near][1] == 0)){  // 늑대인 경우
                if(sheep > wolf + 1){
                    recursion(near, sheep, wolf+1, deepcopy(visit), info);
                }
            }else{
                // 방문 체크
                if(visit[near][0] == sheep && visit[near][1] == wolf) continue;
                recursion(near, sheep, wolf, deepcopy(visit), info);
            }
        }
        
    }
    
    static int[][] deepcopy(int[][] arr){
        int[][] copy = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }
}