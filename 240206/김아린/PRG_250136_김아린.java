import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] oil;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        oil = new int[land[0].length]; //석유량 저장
        visited = new boolean[land.length][land[0].length];
        
        for(int i = 0; i < land[0].length; i++) { //모든 열
            for(int j = 0; j < land.length; j++) { //모든 행
                if(!visited[j][i] && land[j][i] == 1) { //방문x, 석유있음
                    bfs(j, i, land); //해당 지점에서 탐색 시작
                }
            }
            answer = Math.max(answer, oil[i]); //최댓값 갱신
        }
        
        return answer;
    }
    
    static void bfs(int j, int i, int[][] land) {
        Queue<int[]> queue = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>(); //방문한 열 저장
        
        queue.add(new int[] {j, i});
        visited[j][i] = true;
        set.add(i);
        
        int count = 1; //석유량
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0];
            int y = p[1];
            
            for(int d = 0; d < 4; d++) { //상하좌우 탐색
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(0 <= nx && nx < land.length && 0 <= ny && ny < land[0].length) { //그래프 범위안에 있음
                    if(!visited[nx][ny] && land[nx][ny] == 1) { //방문x, 석유있음
                        queue.add(new int[] {nx, ny});
                        visited[nx][ny] = true;
                        set.add(ny);
                        count++;
                    }
                }
            }
            
        }
        
        //set에 있는 열 꺼내서 합에 석유량 누적
        for(int n : set) {
            oil[n] += count;
        }
    }
}