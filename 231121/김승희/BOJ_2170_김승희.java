import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BOJ_2170 {
    static class Tuple{
        int start, end;

        public Tuple(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);
            int temp = map.getOrDefault(a, a);
            map.put(a, Math.max(temp, b));
        }

        ArrayList<Tuple> line = new ArrayList<>();
        for (Integer key : map.keySet()) {
            int temp = map.get(key);
            line.add(new Tuple(key, temp));
        }

        Collections.sort(line, (a, b) -> Integer.compare(a.start, b.start));


        int start = line.get(0).start;
        int end = line.get(0).end;
        int acc = 0;
        for (int i = 1; i < line.size(); i++) {
            if (end >= line.get(i).start) { // 겹침
                end = Math.max(line.get(i).end, end);
            } else {    // 안겹침
                acc += (end - start);
                start = line.get(i).start;
                end = line.get(i).end;
            }
        }
        acc += (end - start);
        System.out.println(acc);
    }
}