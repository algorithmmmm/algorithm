import java.util.*;

class Solution {
    static List<Node>[] graph; //등산로
    static int[] intensity; //봉우리별 강도의 최소
    
    static class Node {
        int idx, cost;
        
        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n + 1]; //연결리스트 생성
        for(int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        //출입구, 산봉우리 인덱스 저장
        HashSet<Integer> gateSet = new HashSet<>();
        for(int gate : gates) {
            gateSet.add(gate);
        }
        
        HashSet<Integer> sumitSet = new HashSet<>();
        for(int summit : summits) {
            sumitSet.add(summit);
        }
        
        for(int[] path : paths) { //그래프에 값 넣기
            int a = path[0]; //출발지
            int b = path[1]; //도착지
            int c = path[2]; //강도
            
            if(gateSet.contains(a) || sumitSet.contains(b)) {
                graph[a].add(new Node(b, c)); //나가는 방향 저장
            } else if(gateSet.contains(b) || sumitSet.contains(a)) {
                graph[b].add(new Node(a, c)); //들어오는 방향 저장
            } else { //양방향 저장
                graph[a].add(new Node(b, c));
                graph[b].add(new Node(a, c));
            }
        }
        
        //다익스트라
        intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE); //봉우리별 강도 무한으로 초기화
        
        dijkstra(gates);
        
        //정답 구하기
        int submitIdx = 0; //산봉우리의 번호
        int min = Integer.MAX_VALUE; //intensity의 최솟값
        
        Arrays.sort(summits); //정렬 안하면 모든 tc 통과x
        
        for(int summit : summits) {
            if(intensity[summit] < min) { //최솟값 발견
                min = intensity[summit];
                submitIdx = summit;
            }
        }
        
        return new int[] {submitIdx, min};
    }
    
    public void dijkstra(int[] gates) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost); //강도 오름차순 정렬
        
        for(int gate : gates) { //큐에 출입구 다 넣음
            queue.add(new Node(gate, 0));
            intensity[gate] = 0;
        }
        
        while(!queue.isEmpty()) {
            Node now = queue.poll(); //현재 봉우리
            
            if(intensity[now.idx] < now.cost) continue;
            
            for(Node next : graph[now.idx]) { //갈 수 있는 봉우리
				if(intensity[next.idx] > Math.max(intensity[now.idx], next.cost)) {
					intensity[next.idx] = Math.max(intensity[now.idx], next.cost);
					queue.add(next);
				}
			}
        }
    }
}