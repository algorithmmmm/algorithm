import java.util.*;

class PRG_피로도_김아린 {
    static int answer; //탐험할 수 있는 최대 던전 수 
    
    public int solution(int k, int[][] dungeons) {
        answer  = 0;
        boolean[] visited = new boolean[dungeons.length]; //던전 방문 체크
        
        permutation(k, dungeons, visited, 0); //순열
        
        return answer;
    }
    
    public void permutation(int k, int[][] dungeons, boolean[] visited, int depth) {
        // if(depth == dungeons.length) {
        //     return;
        // }
        //answer을 밑에서 갱신해주기 때문에 위에 종료 조건 넣으면 안됨
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) { //방문x, 최소 필요 피로도 이상이면
                visited[i] = true;
                permutation(k - dungeons[i][1], dungeons, visited, depth + 1); //피로도 소모시키고, 깊이+1해서 탐색
                visited[i] = false;
            }
        }
        
        answer = Math.max(answer, depth); //탐험할 수 있는 최대 던전 수 갱신
    }
}