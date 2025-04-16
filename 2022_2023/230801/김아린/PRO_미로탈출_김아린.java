import java.util.*;

class Solution {
    static int N, M; //행, 열 크기
    static char[][] graph; //그래프
    static boolean[][] visited; //방문 여부 배열
    static int[] dx = {-1, 1, 0, 0}; //상하좌우
    static int[] dy = {0, 0, -1, 1};
    
    static class Point {
        int x, y, dist; //행, 열, 걸린 시간
        
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        graph = new char[N][M];
        
        int SX = 0, SY = 0; //시작 좌표
        int LX = 0, LY = 0; //레버 좌표
        
        for(int i = 0; i < N; i++) {
            graph[i] = maps[i].toCharArray();
            
            for(int j = 0; j < M; j++) {
                if(graph[i][j] == 'S') {
                    SX = i;
                    SY = j;
                } else if(graph[i][j] == 'L') {
                    LX = i;
                    LY = j;
                }
            }
        }
        
        int answer = 0;
        
        visited = new boolean[N][M];
        int result1 = bfs(SX, SY, 'L'); //시작~레버까지 시간
        
        if(result1 == -1) {
            return -1;
        } else {
            answer += result1;
        }
        
        visited = new boolean[N][M];
        int result2 = bfs(LX, LY, 'E'); //레버~출구까지 시간
        
        if(result2 == -1) {
            return -1;
        } else {
            answer += result2;
        }
        
        return answer;
    }
    
    public int bfs(int i, int j, char goal) {
        Queue<Point> queue = new LinkedList<>();
        
        queue.add(new Point(i, j, 0));
        visited[i][j] = true;
        
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            
            int x = point.x;
            int y = point.y;
            int dist = point.dist;
            
            if(graph[x][y] == goal) {
                return dist;
            }
            
            for(int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(0 <= nx && nx < N && 0 <= ny && ny < M) { //범위 체크
                    if(!visited[nx][ny] && graph[nx][ny] != 'X') { //방문x, 벽이 아님
                        queue.add(new Point(nx, ny, dist + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
            
        }
        
        return -1;
    }
}