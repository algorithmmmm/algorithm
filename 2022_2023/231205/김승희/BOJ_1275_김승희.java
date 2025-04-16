import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static long[] number, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inarr = br.readLine().split(" ");
        N = Integer.parseInt(inarr[0]);
        int M = Integer.parseInt(inarr[1]);

        number = new long[N];
        inarr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            number[i] = Long.parseLong(inarr[i]);
        }
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (1 << (h + 1));
        tree = new long[treeSize];
        init(1, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            inarr = br.readLine().split(" ");
            int x = Integer.parseInt(inarr[0])-1;
            int y = Integer.parseInt(inarr[1])-1;
            if (x > y) {
                int temp = y;
                y = x;
                x = temp;
            }
            int a = Integer.parseInt(inarr[2])-1;
            long b = Long.parseLong(inarr[3]);
            sb.append(query(1, 0, N - 1, x, y)).append("\n");
            change(a, b);
        }

        System.out.println(sb);

    }
    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = number[start];
            return;
        }
        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node] = tree[node*2] + tree[node*2+1];
    }

    static void update(int node, int start, int end, int index, long diff) {
        if (index < start || index > end) return;
        tree[node] += diff;
        if (start != end) {
            update(node * 2, start, (start+end)/2, index, diff);
            update(node * 2+1, (start+end)/2+1, end, index, diff);
        }
    }

    static void change(int index, long value) {
        long diff = value - number[index];
        number[index] = value;
        update(1, 0, N-1, index, diff);
    }

    static long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0L;
        if (left<= start && end <= right) return tree[node];
        long lsum = query(node * 2, start, (start + end) / 2, left, right);
        long rsum = query(node * 2+1,(start + end) / 2+1, end, left, right);
        return lsum + rsum;
    }
}