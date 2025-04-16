import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        String[] inArr = br.readLine().split(" ");
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(inArr[i]);
            adj[num].add(i);
        }
        // end input

        System.out.println(recursion(0, 0));
    }

    // num을 root로 하는 트리에 전달할 때 걸리는 시간 구하기
    static int recursion(int num, int time) {
        int len = adj[num].size();
        if (len == 0) {     // leaf인 경우 time을 반환
            return time;
        }
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            // temp[i] : 각각의 사원에게 time+1일 때 전화를 했을 때, 걸리는 시간
            temp[i] = recursion(adj[num].get(i), time+1);
        }
        // temp 정렬
        // 가장 적게 걸리는 사원에게 가장 늦게 전화하기 위해서.
        Arrays.sort(temp);
        for (int i = 0; i < len; i++) {     // 시간이 작은 순서대로
            temp[i] += len - i - 1;         // 전화 순서는 뒤에서부터
        }
        int answer = 0;
        for (int t : temp){
            answer = Math.max(answer, t);   // 가장 오래 걸린 시간이 정답이 된다.
        }
        return answer;
    }
}
/*
10
-1 0 0 1 2 2 2 3 7 2
5

50
-1 0 1 0 1 1 3 5 4 0 1 0 3 12 0 6 1 1 3 2 19 6 15 0 23 25 26 18 14 29 15 17 17 27 4 10 8 20 13 12 11 33 25 36 8 40 0 36 26 23
7
 */