import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int[] sdp, ldp;
    static boolean[] svisit, lvisit;
    static boolean[][] schk, lchk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] Small = new ArrayList[N + 1];   // Smail[i] : i보다 작은 것들
        ArrayList<Integer>[] Large = new ArrayList[N + 1];   // Large[i] : i보다 큰 것들
        for (int i = 0; i < N + 1; i++) {
            Small[i] = new ArrayList<>();
            Large[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] inArr = br.readLine().split(" ");
            int a = Integer.parseInt(inArr[0]);
            int b = Integer.parseInt(inArr[1]);

            Small[a].add(b);
            Large[b].add(a);
        }
        // end input

        sdp = new int[N + 1];
        ldp = new int[N + 1];
        schk = new boolean[N + 1][N + 1];
        lchk = new boolean[N + 1][N + 1];
        svisit = new boolean[N + 1];
        lvisit = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            recursion(i, schk, svisit, Small);
            sdp[i] = count(schk[i]);
            recursion(i, lchk, lvisit, Large);
            ldp[i] = count(lchk[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            int answer = N - 1 - sdp[i] - ldp[i];
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static boolean[] recursion(int num, boolean[][] chk, boolean[] visit, ArrayList<Integer>[] arr) {
        if (arr[num].size() == 0) return chk[num];
        visit[num] = true;
        for (int n : arr[num]) {
            if (visit[n]) {
                chk[num] = or(chk[num], chk[n]);
            } else {
                chk[num] = or(chk[num], recursion(n, chk, visit, arr));
            }
            chk[num][n] = true;
        }
        return chk[num];
    }

    static boolean[] or(boolean[] a, boolean[] b) {
        for (int i = 0; i < a.length; i++) {
            if (b[i]) a[i] = true;
        }
        return a;
    }

    static int count(boolean[] chk) {
        int cnt = 0;
        for (int i = 1; i < chk.length; i++) {
            if (chk[i]) cnt++;
        }
        return cnt;
    }
}