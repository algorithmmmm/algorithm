import java.util.*;
/*
BFS를 통해서 (0, 0)에서부터 (n-1, m-1)까지 지나간 칸 수를 구한다.

델타 탐색을 할 때, 
1) index가 범위를 넘어가지 않도록 확인하고, 
2) 방문했는지 확인하고, 
3) 벽이 아닌지 확인해야 한다.
*/
class Tuple{
    int i, j;
    public Tuple(int i, int j){
        this.i = i;
        this.j=j;
    }
}
class Solution {
    public int solution(int[][] maps) {
        int answer = -1;    // answer : (0,0)에서 (n-1, m-1)까지 가는 최소 칸 수
        int[] di ={-1,1,0,0};
        int[] dj ={0,0,-1,1};
        Queue<Tuple> Q = new ArrayDeque<>();    // Q : BFS에서 사용할 Q.
        boolean[][] visit = new boolean[maps.length][maps[0].length];   // visit[i][j] = (i, j)를 방문했는가
        visit[0][0]=true;
        Q.add(new Tuple(0,0));
        int cnt = 1;    // cnt : 지나간 칸 수
        boolean end = false;    // end : (n-1, m-1)에 도착했는가
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int p=0; p <size;p++){
                Tuple tp = Q.poll();
                if(tp.i == maps.length -1 && tp.j == maps[0].length-1){
                        answer=cnt;
                        end = true;
                        break;
                    }
                for(int k=0; k < 4; k++){
                    int ni = tp.i + di[k];
                    int nj = tp.j + dj[k];
                    if(ni < 0|| nj < 0 || 
                       ni>= maps.length || nj >= maps[0].length || 
                       visit[ni][nj] || maps[ni][nj]==0)
                        continue;
                    visit[ni][nj]=true;
                    Q.add(new Tuple(ni, nj));
                }
            }
            if(end) {
                break;
            }
            cnt++;
        }
        return answer;
    }
}
/*
[[1, 1]]
2
*/