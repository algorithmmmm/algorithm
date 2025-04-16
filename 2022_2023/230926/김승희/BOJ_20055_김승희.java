import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_20055 {
    static int N, K, stage;
    static ArrayList<Integer> Robot;
    static boolean[] Exist;
    static int[] Durability;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);     // N : 컨베이어 벨트의 길이
        K = Integer.parseInt(inArr[1]);     // K : 종료될 때, 내구도가 0인 칸의 개수

        Durability = new int[2*N];    // Durability[i] : i번 칸의 내구도
        inArr = br.readLine().split(" ");
        for (int i = 0; i < 2*N; i++) {
            Durability[i] = Integer.parseInt(inArr[i]);
        }
        // end input

        Robot = new ArrayList<>();      // Robot : 순서대로 올라온 로봇의 위치
        Exist = new boolean[2*N];     // Exist[i] : i번 칸에 로봇에 있는지
        stage = 0;              // stage : 현재 단계
        while (!count()) {
            turn();
            allMove();
            newRobot();
            stage++;
        }
        System.out.println(stage);
    }
    // 컨베이어벨트 돌기
    static void turn() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < Robot.size(); i ++){
            int robot = Robot.get(i);
            if (robot+1 == N-1) continue;
            list.add(robot + 1);
        }
        Robot = list;
        // 컨베이어 벨트 움직이기
        int prev = Durability[0];
        boolean prevB = Exist[0];
        for (int i = 1; i < 2 * N; i++) {
            int temp = Durability[i];
            boolean tempB = Exist[i];
            Durability[i] = prev;
            Exist[i] = prevB;
            prev = temp;
            prevB = tempB;
        }
        Durability[0] = prev;
        Exist[0] = prevB;
        if (Exist[N-1]) Exist[N - 1] = false;
    }
    // 컨베이어벨트 위 로봇 움직이기
    static void allMove() {
        for (int i = 0; i < Robot.size(); i++) {
            int robot = Robot.get(i);
            // 이동하려는 칸에 로봇이 없고 내구도가 1 이상인 경우
            if (!Exist[robot + 1] && Durability[robot + 1] > 0) {
                Exist[robot] = false;
                Durability[robot + 1] = Math.max(Durability[robot + 1] - 1, 0);
                if (robot+1 == N-1) continue;
                Exist[robot + 1] = true;
                Robot.set(i, robot + 1);
            }
        }
    }
    // 새로운 로봇 올리기
    static void newRobot() {
        if (Durability[0] == 0) return;
        Durability[0] = Math.max(0, Durability[0] - 1);
        Exist[0] = true;
        Robot.add(0);
    }
    static boolean count() {
        int cnt = 0;
        for (int i = 0; i < 2*N; i++) {
            if (Durability[i] == 0) cnt++;
        }
        return cnt >= K;
    }
}