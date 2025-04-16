import java.util.*;

class Solution {
    int M, N;
    int[][] graph;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    boolean[][] visited;
    
    public class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        
        graph = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                graph[i][j] = picture[i][j];
            }
        }
        
        visited = new boolean[m][n];
        
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(picture[i][j] != 0 && !visited[i][j]) {
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j));
                    numberOfArea++;
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        return answer;
    }
    
    public int bfs(int a, int b) {
        Queue<Node> q = new LinkedList<>();
        
        q.add(new Node(a, b));
        int count = 1;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            visited[node.x][node.y] = true;
            
            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                
                if(0 <= nx && nx < M && 0 <= ny && ny < N) {
                    if(graph[node.x][node.y] == graph[nx][ny] && !visited[nx][ny]) {
                        q.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                        count++;
                    }
                }
            } 
        }
        
        return count;
    }
    
}