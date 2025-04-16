import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[] number;
    static int[] tree;
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String str = br.readLine();
        // eof를 만날 때까지
//        while(!(str = br.readLine()).equals("")) {
        while (str != null && !str.equals("")){
            process(str);
            sb.append("\n");
            str = br.readLine();
        }
        System.out.println(sb);
    }
    static void process(String str) throws IOException {
        String[] inArr = str.split(" ");
        N = Integer.parseInt(inArr[0]);
        M = Integer.parseInt(inArr[1]);

        inArr = br.readLine().split(" ");
        number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(inArr[i]);
        }

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (1 << (h + 1));
        tree = new int[treeSize];
        init(1, 0, N - 1);
        for (int i = 0; i < M; i++) {
            inArr = br.readLine().split(" ");
            String op = inArr[0];
            int a = Integer.parseInt(inArr[1])-1;   // 0 index로 맞추기
            int b = Integer.parseInt(inArr[2]);
            if (op.equals("C")) {
                number[a] = (int) b;
                modify(1, 0, N - 1, a, b);
            } else if (op.equals("P")) {
                b--;    // 0 index로 맞추기
                long ans = multiple(1, 0, N - 1, a, b);
                char ch = ans == 0 ? '0' : (ans > 0 ? '+' : '-');   // 0이면 0, 양수면 +, 음수면 -
                sb.append(ch);
            }
        }
    }

    // 양수면 1, 음수면 -1, 0이면 0을 반환
    static int n2val(int num) {
        if (num == 0) return 0;
        return num > 0 ? 1 : -1;
    }

    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = n2val(number[start]);
            return;
        }
        init(node * 2, start, (start + end) / 2);
        init(node * 2 + 1, (start + end) / 2 + 1, end);
        tree[node] = n2val(tree[node*2]) * n2val(tree[node * 2 + 1]);
    }

    static void modify(int node, int start, int end, int index, int value) {
        if (index < start || end < index) return ;
        if (start == end){
            tree[node] = n2val(value); 
            return;
        }
        // 왼쪽 오른쪽 새로 구하기
        modify(node * 2, start, (start + end) / 2, index, value);
        modify(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
        // 새로 구한 값 사용해서 현재 노드의 값 구하기
        tree[node] = n2val(tree[node*2]) * n2val(tree[node * 2 + 1]);
    }

    static int multiple(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 1;  // 더하기는 0을 더하면 되는데, 곱하기니까 1을 반환
        // 만약 left-right 안에 start-end가 있으면 현재 노드의 값을 모두 포함해야 한다.
        if (start >= left && right >= end) return tree[node];
        // 왼쪽 오른쪽 구하기
        int lmul = multiple(node * 2, start, (start + end) / 2, left, right);
        int rmul = multiple(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return n2val(lmul) * n2val(rmul);
    }
}