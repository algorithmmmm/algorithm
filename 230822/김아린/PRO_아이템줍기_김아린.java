import java.util.*;

class Solution {
    static int[][] graph; //그래프 : 0, 1(내부), 2(테두리)
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    
    static class Point {
        int i, j; //y좌표(행), x좌표(열)
        
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        graph = new int[50*2+1][50*2+1];
        
        //그래프 영역 채우기
        for(int[] r : rectangle) {
            int x1 = r[0]*2; //좌측 하단 x
            int y1 = r[1]*2; //좌측 하단 y
            int x2 = r[2]*2; //우측 상단 x
            int y2 = r[3]*2; //우측 상단 y
            
            for(int i = y1; i <= y2; i++) {
                for(int j = x1; j <= x2; j++) {
                    if(i == y1 || i == y2 || j == x1 || j == x2) { //좌표가 테두리
                        if(graph[i][j] == 0) { //빈 곳
                            graph[i][j] = 2; //테두리 그리기
                        }
                    } else { //좌표가 내부
                        graph[i][j] = 1; //내부 칠하기        
                    }
                }
            }
        }
        
        //그래프 탐색
        visited = new boolean[50*2+1][50*2+1];
        
        int answer = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2; //2배 늘렸으니 2로 나누기
        return answer;
    }
    
    public int bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Point> queue = new LinkedList<>();
        
        queue.add(new Point(characterY, characterX));
        visited[characterY][characterX] = true;
        
        int count = 0; //이동 카운트
        while(!queue.isEmpty()) {
            int size = queue.size(); //너비 탐색을 위한 사이즈
            
            for(int s = 0; s < size; s++) { //사이즈만큼 끊어서 탐색
                Point point = queue.poll();
                
                if(point.i == itemY && point.j == itemX) { //도착
                    return count;
                }
                
                for(int d = 0; d < 4; d++) {
                    int ni = point.i + di[d];
                    int nj = point.j + dj[d];
                    
                    if(1 <= ni && ni <= 100 && 1 <= nj && nj <= 100) {
                        if(!visited[ni][nj] && graph[ni][nj] == 2) {
                            queue.add(new Point(ni, nj));
                            visited[ni][nj] = true;
                        }
                    }
                }
            }
            
            count++;
        } 
        
        return count;
    }
}