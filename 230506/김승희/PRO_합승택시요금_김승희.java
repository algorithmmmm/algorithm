public class 합승택시요금 {
    public int solution(int n, int start, int a, int b, int[][] fares) {
        int[][] dist = distance(n);
        for(int i = 0; i < fares.length; i++){
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];
            dist[c][d] = f;
            dist[d][c] = f;
        }

        // 플로이드 워셜 알고리즘
        for(int k = 1; k < n+1; k++){
            for(int s = 1; s < n+1; s++){
                for(int e = 1; e < n+1; e++){
                    if(dist[s][k] == Integer.MAX_VALUE || dist[k][e] == Integer.MAX_VALUE) continue;
                    dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                }
            }
        }
        int answer = dist[start][a] + dist[start][b];
        for(int k = 1; k < n+1; k++){
            if(dist[start][k] == Integer.MAX_VALUE || dist[k][a] == Integer.MAX_VALUE || dist[k][b] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, dist[start][k] + dist[k][a] + dist[k][b]);
        }
        return answer;
    }
    // dist 배열 초기화
    public int[][] distance(int n){
        int[][] dist = new int[n+1][n+1];
        for(int i = 0; i < n+1; i++){
            for(int j = 0; j < n+1; j++){
                if(i == j){
                    dist[i][j] = 0;
                }else{
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        return dist;
    }
}