package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_암벽등반 {

    static Set<String> rocks = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            rocks.add(x + "," + y);
        }

        System.out.println(bfs(T));
    }

    public static int bfs(int T) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new int[]{0, 0});
        visited.add("0,0");

        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int s = 0; s < size; s++) {
                int[] p = queue.poll();
                int x = p[0], y = p[1];

                if(y == T) return count;

                for(int dx = -2; dx <= 2; dx++) {
                    for(int dy = -2; dy <= 2; dy++) {
                        int nx = x + dx;
                        int ny = y + dy;
                        String key = nx + "," + ny;

                        if(rocks.contains(key) && !visited.contains(key)) {
                            visited.add(key);
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            count++;
        }

        return -1;
    }
}