package day221229;

/*
 * dfs 탐색을 하면서 최댓값을 갱신한다. 
 * 
 * */

public class PRG_87946_백자민 {
    public int answer = 0;
    public boolean[] visit;
    
    public void dfs(int cnt, int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && dungeons[i][0] <= k) { //아직 방문하지 않았고 들어갈 수 있는 던전인 경우 
                visit[i] = true;
                dfs(cnt+1, k-dungeons[i][1], dungeons); //다음 던전으로 출발 
                visit[i] = false;
            }
        }

        answer = Math.max(answer, cnt);
    }

    public int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length]; //방문체크를 하기 위한 배열 

        dfs(0, k, dungeons);

        return answer;
    }
}
