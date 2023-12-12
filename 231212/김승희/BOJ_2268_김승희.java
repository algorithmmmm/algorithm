import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

public class Main {
    static int N, M;
    static long[] number, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);

        number = new long[N];
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (1 << (h + 1));
        tree = new long[treeSize];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            inArr = br.readLine().split(" ");
            int op = Integer.parseInt(inArr[0]);
            int a = Integer.parseInt(inArr[1]);
            int b = Integer.parseInt(inArr[2]);

            if (op == 0) {
                if (b < a){
                    int temp = b;
                    b = a;
                    a = temp;
                }
                sb.append(query(1, 0, N - 1, a-1, b-1)).append("\n");
            } else {
                modify(a-1, b);
            }
        }

        System.out.println(sb);
    }

    static long query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) return 0;
        if (left <= start && end <= right) return tree[node];
        long lsum = query(2 * node, start, (start + end) / 2, left, right);
        long rsum = query(2 * node + 1, (start + end) / 2 + 1, end, left, right);
        return lsum + rsum;
    }

    static void update(int node, int start, int end, int index, long diff) {
        if (start > index || end < index) return;
        if (start == end) {
            tree[node] += diff;
            return;
        }
        update(2 * node, start, (start+end) / 2 , index, diff);
        update(2*node + 1, (start+end) / 2 + 1, end, index, diff);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    static void modify(int index, int value) {
        long diff = value - number[index];
        number[index] = value;
        update(1, 0, N-1, index, diff);
    }
}