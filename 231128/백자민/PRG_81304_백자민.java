package day1128;

import java.util.*;

public class PRG_81304_백자민 {

	class Solution {
	    
	    static int INF = Integer.MAX_VALUE;
	    
	    static int[][] dist;
	    static List<Node>[] graph;
	    static Map<Integer, Integer> trapMap;
	    
	    static int check(int tmp, int next, int tmpState) {
	        boolean tmpCheck=false, nextCheck=false; //진행 방향과 간선 방향이 일치해야 함
	        
	        if(trapMap.containsKey(tmp))
	            if ((tmpState & (1<<trapMap.get(tmp))) > 0)
	                tmpCheck = true;
	        
	        if(trapMap.containsKey(next))
	            if((tmpState & (1<<trapMap.get(next))) > 0)
	                nextCheck = true;
	        
	        if(tmpCheck^nextCheck)
	            return 1;
	        return 0;
	    }
	    
	    static int dijkstra(int start, int end) {
	        PriorityQueue<Node> pq = new PriorityQueue<>();
	        pq.offer(new Node(start, 0, 0));
	        dist[start][0] = 0;
	        
	        int answer = INF;
	        
	        while(!pq.isEmpty()) {
	            Node cur = pq.poll();
	            int tmp = cur.v;
	            int tmpw = cur.w;
	            int tmpState = cur.state;
	            
	            if(tmp == end) { //목적지에 도착 
	                answer = Math.min(answer, tmpw);
	                continue;
	            }
	            
	            if(tmpw > dist[tmp][tmpState]) //최단 거리가 아닌 경우
	                continue; 
	            
	            for(Node n: graph[tmp]) {
	                int next = n.v;
	                int nextw = n.w;
	                int isReverse = n.state;
	                
	                //현재 역방향+뒤집어진 간선 -> 다음 노드로 이동 가능
	                //현재 정방향+정상 간선 -> 다음 노드로 이동 가능
	                //그 외 진행방향과 간선방향이 맞지 않는 경우 -> 이동 불가능
	                if(isReverse != check(tmp, next, tmpState)) continue;
	                
	                nextw += tmpw; //비용 누적
	                
	                int nextState = tmpState;
	                if(trapMap.containsKey(next))
	                    nextState = tmpState^(1<<trapMap.get(next));
	                
	                if(nextw >= dist[next][nextState]) continue;
	                
	                dist[next][nextState] = nextw;
	                pq.offer(new Node(next, nextw, nextState));
	            }
	        }
	        
	        return answer;
	    }
	    
	    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
	        
	        graph = new ArrayList[n+1];
	        trapMap = new HashMap<>();
	        
	        for(int i=1;i<=n;i++) {
	            graph[i] = new ArrayList<>();
	        }
	        
	        for(int i=0,size=traps.length;i<size;i++) { //비트를 1~10 범위로 한정
	            trapMap.put(traps[i], i); 
	        }
	        
	        for(int[] r: roads) {
	            int from = r[0];
	            int to = r[1];
	            int w = r[2];
	            graph[from].add(new Node(to, w, 0));
	            graph[to].add(new Node(from, w, 1));
	        } 
	        
	        dist = new int[n+1][1<<10];
	        for(int i=0;i<=n;i++) {
	            Arrays.fill(dist[i], INF);
	        }
	        
	        return dijkstra(start, end);
	    }
	    
	    static class Node implements Comparable<Node> {
	        int v,w,state;
	        
	        public Node(int v, int w, int state) {
	            this.v = v;
	            this.w = w;
	            this.state = state; //뒤집어졌는지
	        }
	        
	        @Override
	        public int compareTo(Node o) { //비용이 적은 간선부터
	            return this.w-o.w;
	        }
	    }
	}
}
