import java.util.*;

class Solution {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        graph = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            char[] temp = maps[i].toCharArray();
            
            for(int j = 0; j < M; j++) {
                if(temp[j] == 'X') {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = temp[j] - '0';
                }
            }
        }
        
        List<Integer> list = new ArrayList<>();
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && graph[i][j] != 0) {
                    list.add(bfs(i, j));
                }
            }
        }
        
        Collections.sort(list);
        
        if(list.size() == 0) {
            return new int[] {-1};
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int bfs(int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        int sum = 0;
        
        queue.add(new Point(i, j));
        visited[i][j] = true;
        sum += graph[i][j];
        
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = point.x + dx[d];
                int ny = point.y + dy[d];
                
                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nx][ny] && graph[nx][ny] != 0) {
                        queue.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        sum += graph[nx][ny];
                    }
                }
            }
        }
        
        return sum; 
    }
    
}