import java.util.*;

/*
 * 1. 좌측상단에서 BFS 탐색 시작
 * 2. 탐색시 큐에 좌표값과 지나온 칸 수 정보를 가진 객체 넣음
 * 3. 우측 하단(상대팀 진영)에 도착하면 칸 수 리턴
 * 4. 상대팀 진영에 도착할 수 없으면 -1 리턴
 */

class PRO_게임맵최단거리_김아린 {
    static int[] dx = {-1, -1, 0, 0}; //상하좌우 좌표
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited; //방문했는지 체크하는 배열
    
    static class Player { //캐릭터의 위치, 지나온 칸 수
        int x;
        int y;
        int count;
        
        public Player(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length]; //방문 배열 초기화
        
        int answer = bfs(maps, 0, 0); //좌측상단에서 bfs 탐색 시작
        
        return answer;
    }
    
    public int bfs(int[][] maps, int r, int c) {
        Queue<Player> queue = new LinkedList<Player>();
        
        queue.add(new Player(r, c, 1));
        visited[r][c] = true;
        
        while(!queue.isEmpty()) {
            Player player = queue.poll();
            
            if(player.x == maps.length - 1 && player.y == maps[0].length - 1) { //상대팀 진영에 도착
                return player.count; //지나온 칸수가 정답
            }
            
            for(int i = 0; i < 4; i++) { //상하좌우 탐색
                int nx = player.x + dx[i];
                int ny = player.y + dy[i];
                int count = player.count;
                
                if(0 <= nx && nx < maps.length && 0 <= ny && ny < maps[0].length) { //게임 맵 안에 있고
                    if(maps[nx][ny] == 1 && !visited[nx][ny]) { //벽이 없는 자리 & 방문한적 x
                        queue.add(new Player(nx, ny, count + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        
        return -1; //상대 팀 진영에 도착할 수 없음
    }
}