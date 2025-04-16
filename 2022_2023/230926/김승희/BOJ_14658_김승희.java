import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class BOJ_14658 {
    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static Point[] star;
    static int N, M, L, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        M = Integer.parseInt(inArr[0]);     // M : 구역의 가로 길이
        N = Integer.parseInt(inArr[1]);     // N : 구역의 세로 길이
        L = Integer.parseInt(inArr[2]);     // L : 트램펄린 길이
        K = Integer.parseInt(inArr[3]);     // K : 별똥별의 개수

        star = new Point[K];            // star[k] : k번 별똥별이 떨어지는 위치
        HashSet<Integer> tempI = new HashSet<>();   // tempI : 별똥별이 떨어지는 위치들의 세로 좌표
        HashSet<Integer> tempJ = new HashSet<>();   // tempJ : 별똥별이 떨어지는 위치들의 가로 좌표
        for (int i = 0; i < K; i++) {
            // 가로위치, 세로위치 순으로 들어옴
            inArr = br.readLine().split(" ");
            star[i] = new Point(Integer.parseInt(inArr[1]), Integer.parseInt(inArr[0]));
            tempI.add(star[i].i);
            tempJ.add(star[i].j);
        }
        // end input
        ArrayList<Integer> I = set2list(tempI);
        ArrayList<Integer> J = set2list(tempJ);

        int count = 0;
        //  I랑 J의 최대 길이는 100
        // 별똥별의 개수가 최대 100개이고 이 별똥별의 세로나 가로가 다 다르다고 했을 떄
        // process에서 도는 for문의 최대는 100
        // log(100 * 100 * 100) = log(100만)
        for (int i = 0; i < I.size(); i++) {
            for (int j = 0; j < J.size(); j++) {
                count = Math.max(count, process(I.get(i), J.get(j)));
            }
        }
        System.out.println(K - count);
    }

    static int process(int si, int sj) {
        // (ei, ej) : 트램펄린의 오른쪽 아래 끝 위치
        int ei = Math.min(N, si + L);
        int ej = Math.min(M, sj + L);
        int cnt = 0;        // cnt : 트램펄린 안에 들어가는 별똥별의 개수

        // 별똥별의 개수는 최대 100
        // 별똥별을 보면서 트램펄린 안에 들어가는지 확인
        for (int k = 0; k < K; k++) {
            if (si <= star[k].i && star[k].i <= ei && sj <= star[k].j && star[k].j <= ej) {
                cnt++;
            }
        }
//        System.out.printf("------- (%d %d) -> (%d %d) --------- cnt = %d \n", si, sj, ei, ej,  cnt);
        return cnt;
    }

    static ArrayList<Integer> set2list(HashSet<Integer> set) {
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }
}