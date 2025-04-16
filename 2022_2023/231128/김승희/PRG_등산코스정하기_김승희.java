import java.util.*;
class Solution {
    static int N;
    static int[] spot;
    static ArrayList<ArrayList<int[]>> adj;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;
        spot = new int[n+1];
        
        for(int i = 0; i < gates.length; i++){
            spot[gates[i]] = 1;    // 출입구는 1
        }
        for(int i = 0; i < summits.length; i++){
            spot[summits[i]] = 2;  // 산봉우리는 2
        }
        
        adj = new ArrayList<>();    // adj.get(a) a에서 [0]으로 가는데 [1]만큼 시간 걸림
        for(int i = 0; i < n+1; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int i = 0; i < paths.length; i++){
            int a = paths[i][0];
            int b = paths[i][1];
            int c = paths[i][2];
            
            // 출입구 -> 쉼터, 산봉우리
            // 쉼터 -> 산봉우리
            // a -> b 
            if((spot[a] == 1 && spot[b] != 1) || (spot[a] == 0 && spot[b] == 2)){
                adj.get(a).add(new int[]{b, c});
                continue;
            }
            // b -> a
            if((spot[b] == 1 && spot[a] != 1) || (spot[b] == 0 && spot[a] == 2)){
                adj.get(b).add(new int[]{a, c});
                continue;
            }
            // 쉼터 <-> 쉼터
            // a <-> b
            adj.get(a).add(new int[]{b, c});
            adj.get(b).add(new int[]{a, c});
        }
        // init
        
        
        int[] answer = dijkstra(gates, summits);
        return answer;
    }
    static int[] dijkstra(int[] gates, int[] summits){
        // intensity는 
        int[] dist = new int[N+1];      // dist[i] : i번 스팟까지 intensity의 최댓값
        for(int i = 0; i < N+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<int[]> PQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        for(int i = 0; i < gates.length; i++){
            PQ.add(new int[]{gates[i], 0});
            dist[gates[i]] = 0;
        }
        
        while(!PQ.isEmpty()){
            int[] now = PQ.poll();
            // 만약 이미 최소면 갱신 안해도 된다.
            if(dist[now[0]] < now[1]) continue;
            for(int i = 0; i < adj.get(now[0]).size(); i++){
                // now의 주변 스팟
                int[] near = adj.get(now[0]).get(i);
                // now까지의 intensity랑 near오는데 걸리는 시간 중 큰 값
                int value = Math.max(near[1], dist[now[0]]);
                // near에 이미 현재와 같은 intensity나 더 작은 경우로 왔었으면 안와도 됨
                if(dist[near[0]] <= value) continue;
                dist[near[0]] = value;              
                PQ.add(new int[]{near[0], dist[near[0]]});
            }
        }
        return minValue(dist, summits);
    }
    static int[] minValue(int[] dist, int[] summits){
        int[] value = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
        for(int summit : summits){
            if(value[1] > dist[summit]){
                value[1] = dist[summit];
                value[0] = summit;
            }else if(value[1] == dist[summit]){
                value[0] = Math.min(value[0], summit);
            }
        }
        return value;
    }
}