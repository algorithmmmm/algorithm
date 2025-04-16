import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BOJ_10800_2 {
    static class Ball {
        int size;
        ArrayList<int[]> list;

        public Ball(int size) {
            this.size = size;
            this.list = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Ball> map = new HashMap<>();
        int[] Color = new int[N+1];        // Color[i] : i 크기의 공의 총 크기 합
        int Total = 0;      // Total : 모든 공의 총 크기 합
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            int c = Integer.parseInt(inArr[0]);
            int s = Integer.parseInt(inArr[1]);
            Ball ball = map.getOrDefault(s, new Ball(s));
            ball.list.add(new int[]{i, c});
            map.put(s, ball);
            Color[c] += s;
            Total += s;
        }

        PriorityQueue<Ball> PQ = new PriorityQueue<>((b1, b2) -> Integer.compare(b2.size, b1.size));
        PQ.addAll(map.values());

        int[] Score = new int[N];
        while (!PQ.isEmpty()) {
            // 현재 가장 크기가 큰 공
            Ball ball = PQ.poll();
            // 현재 크기랑 같은 공은 점수 획득 못함
            Total -= ball.size * ball.list.size();
            // 현재 크기랑 같은 공은 점수 획득 못하니까 Color에서도 뺴주기
            for (int i = 0; i < ball.list.size(); i++) {
                int[] temp = ball.list.get(i);
                Color[temp[1]] -= ball.size;
            }

            for (int i = 0; i < ball.list.size(); i++) {
                int[] temp = ball.list.get(i);
                Score[temp[0]] = Total - Color[temp[1]];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(Score[i]).append("\n");
        }
        System.out.println(sb);
    }
}
