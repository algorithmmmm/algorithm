package day0719;
import java.util.*;

public class PRG_118669_백자민 {

	class Solution {
	    
	    static List<List<Node>> graph;
	    
	    static int[] answer;
	    
	    static void find(int n, int[] gates, int[] summits) { //출입구 -> 산봉우리로 가는 코스
	        Queue<Node> q = new LinkedList<>();
	        int[] intensity = new int[n+1];
	        Arrays.fill(intensity, Integer.MAX_VALUE);
	        
	        for(int g: gates) {
	            q.offer(new Node(g, 0));
	            intensity[g] = 0; //시작지점 초기화
	        }

	        while(!q.isEmpty()) {
	            Node tmp = q.poll();
	            
	            if(tmp.w > intensity[tmp.e]) continue;

	            for(int i=0, size=graph.get(tmp.e).size();i<size;i++) {
	                Node tn = graph.get(tmp.e).get(i);
	                
	                int dist = Math.max(intensity[tmp.e], tn.w); //지금까지 코스에서 intensity 구하기
	                
	                if(intensity[tn.e]>dist) {
	                    intensity[tn.e] = dist;
	                    q.add(new Node(tn.e, dist));
	                }
	            }
	        }
	        
	        answer[0] = Integer.MAX_VALUE; //산봉우리 번호
	        answer[1] = Integer.MAX_VALUE; //최소 intensity 값
	        
	        Arrays.sort(summits);
	        
	        for(int summit: summits) {
	            if(intensity[summit]<answer[1]) {
	                answer[0] = summit;
	                answer[1] = intensity[summit];
	            }
	        }
	        
	    }
	    
	    static boolean isContain(int node, int[] arr) {
	        for(int a: arr) {
	            if(a == node)
	                return true;
	        }
	        return false;
	    }
	    
	    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
	        graph = new ArrayList<>();
	        for(int i=0;i<=n;i++) {
	            graph.add(new ArrayList<Node>());
	        }
	        
	        for(int[] path: paths) {
	            int s = path[0];
	            int e = path[1];
	            int d = path[2];
	            
	            if(isContain(s, gates) || isContain(e, summits)) { //출입구->산봉우리
	                graph.get(s).add(new Node(e, d));
	            } else if(isContain(e, gates) || isContain(s, summits)) { //산봉우리->출입구
	                graph.get(e).add(new Node(s,d));
	            } else {
	                graph.get(s).add(new Node(e, d));
	                graph.get(e).add(new Node(s,d));
	            }
	        }
	        answer = new int[2];
	        find(n, gates, summits);
	        
	        return answer;
	    }
	    
	    static class Node {
	        int e, w;
	        
	        Node(int e, int w) {
	            this.e = e;
	            this.w = w;
	        }
	    }
	}
}
