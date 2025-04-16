import java.util.*;
class Solution {
    public int solution(int N, int[][] lighthouse) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < N+1; i++){
            adj.add(new ArrayList<Integer>());
        }
        int[] cnt = new int[N+1];
        for(int i = 0; i < lighthouse.length; i++){
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            cnt[a]++;
            cnt[b]++;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        
        Queue<Integer> Q = new ArrayDeque<>();
        for(int i = 1; i < N+1; i++){
            if(cnt[i] == 1){
                Q.add(i);
            }
        }
        
        while(!Q.isEmpty() && !check(cnt)){
            int a = Q.poll();
            if(cnt[a] != 1) continue;
            answer++;
            for(int i = 0; i < adj.get(a).size(); i++){
                if(cnt[adj.get(a).get(i)] < 1){
                    continue;
                }
                int temp = adj.get(a).get(i);
                cnt[temp] = 0;
                for(int j = 0; j < adj.get(temp).size(); j++){
                    cnt[adj.get(temp).get(j)]--;
                    if(cnt[adj.get(temp).get(j)] == 1){
                        Q.add(adj.get(temp).get(j));
                    }
                }
                break;
            }
        }
        
        return answer;
    }
    
    static boolean check(int[] arr){
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > 0){
                return false;
            }
        }
        return true;
    }
}