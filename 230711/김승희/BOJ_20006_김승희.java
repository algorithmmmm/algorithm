import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    static int N, M;
    static ArrayList<Room> list;

    static class Player implements Comparable<Player>{
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }
    static class Room {
        int level;
        TreeSet<Player> players;

        public Room(int level) {
            this.level = level;
            players = new TreeSet<>();
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            int level = Integer.parseInt(inArr[0]);
            if (!check(level, inArr[1])) {      // 현재 플레이어가 게임 방에 들어갈 수 없다면
                // 방을 생성한다.
                Room room = new Room(level);
                room.players.add(new Player(level, inArr[1]));
                list.add(room);
            }
        }
        // end input

        pretty();
    }
    // 정답을 출력하는 함수
    static void pretty() {
        StringBuilder sb = new StringBuilder();
        for (Room room : list) {
            if (room.players.size() == M) { // 게임 방의 정원이 꽉 찼으면 게임이 시작된 방
                sb.append("Started!");
            }else {     // 게임 방의 정원이 남아있으면 대기하는 방
                sb.append("Waiting!");
            }
            sb.append("\n");
            for (Player player : room.players) {
                sb.append(player.level).append(" ").append(player.name).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    // player를 게임 방에 넣을 수 있으면 true. 그렇지 않으면 false
    static boolean check(int level, String name) {
        if (list.isEmpty()) return false;
        for (Room room : list) {
            // 게임 방이 꽉 찼으면 continue
            if (room.players.size() == M) continue;
            // 게임 방에 들어갈 수 있으면 들어가기
            if (room.level - 10 <= level && level <= room.level + 10) {
                room.players.add(new Player(level, name));
                return true;
            }
        }
        return false;
    }
}