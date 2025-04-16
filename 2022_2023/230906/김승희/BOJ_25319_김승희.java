import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static class Point{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, L, minCnt;
    static HashMap<Character, ArrayList<Point>> char2Pnt;
    static int[] alphaB, alphaID;
    static char[][] Board;
    static boolean[][] Item;
    static String ID;
    static StringBuffer Ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        N = Integer.parseInt(inArr[0]);     // N : 세로길이
        M = Integer.parseInt(inArr[1]);     // M : 가로길이
        L = Integer.parseInt(inArr[2]);     // L : 아이디길이
        char2Pnt = new HashMap<>();
        Board = new char[N][];
        alphaB = new int[26];
        alphaID = new int[26];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            Board[i] = chars;
            for (int j = 0; j < M; j++) {
                alphaB[chars[j] - 'a']++;
                ArrayList<Point> list = char2Pnt.getOrDefault(chars[j], new ArrayList<>());
                list.add(new Point(i, j));
                char2Pnt.put(chars[j], list);
            }
        }
        if (L == 0) {
            System.exit(0);
        }
        ID = br.readLine();
        Ans = new StringBuffer();
        Item = new boolean[N][M];
        setMinCnt();
        process();
        System.out.println(minCnt + " " + Ans.length());
        System.out.println(Ans);
    }
    static void setMinCnt() {
        for (int i = 0; i < L; i++) {
            alphaID[ID.charAt(i) - 'a']++;
        }
        minCnt = Integer.MAX_VALUE;     // minCnt : 만들 수 있는 아이디의 최대 개수
        for (int i = 0; i < 26; i++) {
            if (alphaID[i] == 0 || alphaB[i] == 0) continue;
            minCnt = Math.min(minCnt, alphaB[i] / alphaID[i]);
        }
    }
    static void process(){
        Point start = new Point(0, 0);
        Point end = new Point(N - 1, M - 1);
        int idx = 0;
        if (Board[0][0] == ID.charAt(0)) {
            idx++;
            Item[0][0] = true;
            Ans.append("P");
        }
       // 최대 만들 수 있는 개수만큼 만들기
        for (int i = 0; i < minCnt; i++) {
            // idx번 문자부터 ID 문자열을 만들러가기
            for (int j = idx; j < L; j++) {
                // 필요한 문자를 주우러가기
                ArrayList<Point> list = char2Pnt.get(ID.charAt(j));
                for (int k = 0; k < list.size(); k++) {
                    Point pnt = list.get(k);
                    // 이미 사용했음
                    if (Item[pnt.x][pnt.y]) continue;
                    // 사용하기
                    Item[pnt.x][pnt.y] = true;
                    getPath(start, pnt);
                    Ans.append("P");
                    start = pnt;
                    break;
                }
            }
            idx = 0;
        }
        // 다만들었는데 end까지 못갔으면 가기
        if (!(start.x == end.x && start.y == end.y)) {
            getPath(start, end);
        }
    }
    static void getPath(Point start, Point end) {
        if (start.x < end.x) {
            Ans.append("D".repeat(Math.max(0, end.x - start.x)));
        } else if (start.x > end.x) {
            Ans.append("U".repeat(Math.max(0, start.x - end.x)));
        }
        if (start.y < end.y) {
            Ans.append("R".repeat(Math.max(0, end.y - start.y)));
        } else if (start.y > end.y) {
            Ans.append("L".repeat(Math.max(0, start.y - end.y)));
        }
    }
}