import java.util.*;

class Solution {
    static char[][] graph; //보드 그래프
    static int N, M; //행, 열 크기
    static boolean[][] visited; //방문 여부 채크
    static int[] dx = {-1, 1, 0, 0}; //상하좌우
    static int[] dy = {0, 0, -1, 1};
    
    static class Dot {
        int x, y, dist; //행, 열, 이동 횟수
        
        public Dot(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int solution(String[] board) {
        N = board.length; 
        M = board[0].length();
        graph = new char[N][M];
        
        int startX = 0, startY = 0;
        
        for(int i = 0; i < N; i++) {
            String str = board[i];
            
            for(int j = 0; j < M; j++) {
                graph[i][j] = str.charAt(j);
                if(graph[i][j] == 'R') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        visited = new boolean[N][M];
        
        return bfs(startX, startY);
    }
    
    public int bfs(int startX, int startY) {
        Queue<Dot> queue = new LinkedList<>();
        int min = Integer.MAX_VALUE; //정답 : 최소 이동 횟수
        
        queue.add(new Dot(startX, startY, 0));
        visited[startX][startY] = true;
        
        boolean flag = false; //도달할 수 있는지
        while(!queue.isEmpty()) {
            Dot dot = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                int nx = dot.x + dx[d];
                int ny = dot.y + dy[d];
                
                while((0 <= nx && nx < N && 0 <= ny && ny < M) && (graph[nx][ny] != 'D')) { //장애물이나 맨 끝에 부딪힐 때까지 미끄러져 이동
                    nx += dx[d];
                    ny += dy[d];
                }
                
                if(graph[nx - dx[d]][ny - dy[d]] == 'G') { //목표 도달
                    min = Math.min(min, dot.dist + 1); //최소 이동 횟수 갱신
                    flag = true;
                    break;
                }
                
                //이동함
                if(!visited[nx - dx[d]][ny - dy[d]]) {
                    queue.add(new Dot(nx - dx[d], ny - dy[d], dot.dist + 1));
                    visited[nx - dx[d]][ny - dy[d]] = true;
                }
            }
            
        }
        
        if(flag) { //도달할 수 있음
        	return min;
        } else { //없음
        	return -1;
        }
    }
}