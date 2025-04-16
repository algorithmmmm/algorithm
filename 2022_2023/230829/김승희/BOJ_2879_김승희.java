import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static class Tuple implements Comparable<Tuple>{
        int num, idx;
        boolean positive;

        public Tuple(int num, int idx, boolean positive) {
            this.num = num;
            this.idx = idx;
            this.positive = positive;
        }

        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(this.num, o.num);
        }
    }
    static int N;
    static int[] fix;
    static PriorityQueue<Tuple> PQ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] inArr = br.readLine().split(" ");
        String[] inArr2 = br.readLine().split(" ");
        fix = new int[N];
        PQ = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            fix[i] = Integer.parseInt(inArr2[i]) - Integer.parseInt(inArr[i]);
            PQ.add(new Tuple(Math.abs(fix[i]), i, fix[i] > 0));
        }
        int cnt = 0;
        while (!PQ.isEmpty()) {
            Tuple tp = PQ.poll();
            if (tp.num == 0 || fix[tp.idx] == 0 || Math.abs(fix[tp.idx]) < tp.num) {
                continue;
            }
            cnt += tp.num;
            expand(tp);
        }
        System.out.println(cnt);
    }

    static void expand(Tuple tp) {
//        왼쪽으로
        int idx = tp.idx -1;
        while (idx >= 0){
            if (fix[idx] == 0 || (fix[idx] < 0) == tp.positive) break;
            fix[idx] -= fix[tp.idx];
            PQ.add(new Tuple(Math.abs(fix[idx]), idx, tp.positive));
            idx--;
        }
//        오른쪽으로
        idx = tp.idx + 1;
        while (idx < N) {
            if (fix[idx] == 0 || (fix[idx] < 0) == tp.positive) break;
            fix[idx] -= fix[tp.idx];
            PQ.add(new Tuple(Math.abs(fix[idx]), idx, tp.positive));
            idx++;
        }
        fix[tp.idx] = 0;
    }
}