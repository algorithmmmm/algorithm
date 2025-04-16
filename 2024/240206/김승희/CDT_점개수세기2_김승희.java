import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] sum;
    static TreeMap<Integer, Integer> xMapper, yMapper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int N = Integer.parseInt(inArr[0]);
        int Q = Integer.parseInt(inArr[1]);
        PriorityQueue<Integer> xPQ = new PriorityQueue<>();
        PriorityQueue<Integer> yPQ = new PriorityQueue<>();
        int[][] pnts = new int[N][2];
        for (int i = 0; i < N; i++) {
            inArr = br.readLine().split(" ");
            pnts[i][0] = Integer.parseInt(inArr[0]);
            pnts[i][1] = Integer.parseInt(inArr[1]);
            xPQ.add(pnts[i][0]);
            yPQ.add(pnts[i][1]);
        }
        xMapper = gridCompression(xPQ);
        yMapper = gridCompression(yPQ);
        sum = prefixSum(xMapper.size(), yMapper.size(), pnts);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            inArr = br.readLine().split(" ");
            int x1 = Integer.parseInt(inArr[0]);
            int y1 = Integer.parseInt(inArr[1]);
            int x2 = Integer.parseInt(inArr[2]);
            int y2 = Integer.parseInt(inArr[3]);
            int ans = inRange(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2));
            sb.append(ans);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // (x1, y1) : 좌상단
    // (x2, y2) : 우하단
    static int inRange(Integer x1, Integer y1, Integer x2, Integer y2) {
        // key 값 구하기
        x1 = Optional.ofNullable(xMapper.ceilingKey(x1)).orElse(xMapper.size()+1);
        x2 = Optional.ofNullable(xMapper.floorKey(x2)).orElse(0);
        y1 = Optional.ofNullable(yMapper.ceilingKey(y1)).orElse(yMapper.size()+1);
        y2 = Optional.ofNullable(yMapper.floorKey(y2)).orElse(0);

        // value 값 구하기. 압축된 좌표값
        x1 = xMapper.getOrDefault(x1, xMapper.size()+1);
        x2 = xMapper.getOrDefault(x2, 0);
        y1 = yMapper.getOrDefault(y1, yMapper.size()+1);
        y2 = yMapper.getOrDefault(y2, 0);

        int answer = sum[x2][y2];
        if (y1 != 0) {
            answer -= sum[x2][y1 - 1];
        }
        if (x1 != 0) {
            answer -= sum[x1 - 1][y2];
        }
        if (x1 != 0 && y1 != 0) {
            answer += sum[x1 - 1][y1 - 1];
        }
        return answer;
    }

    static boolean[][] isExist(int xLen, int yLen, int[][] pnts) {
        boolean[][] check = new boolean[xLen + 1][yLen + 1];
        for (int[] pnt : pnts) {
            int x = xMapper.get(pnt[0]);
            int y = yMapper.get(pnt[1]);
            check[x][y] = true;
        }
        return check;
    }

    static int[][] prefixSum(int xLen, int yLen, int[][] pnts) {
        int[][] sum = new int[xLen + 1][yLen + 1];
        boolean[][] check = isExist(xLen, yLen, pnts);

        // j가 0인 경우
        for (int i = 1; i < xLen + 1; i++) {
            sum[i][0] = sum[i - 1][0];
            if (check[i][0]) sum[i][0]++;
        }
        // i가 0인 경우
        for (int j = 1; j < yLen + 1; j++) {
            sum[0][j] = sum[0][j - 1];
            if (check[0][j]) sum[0][j]++;
        }

        // prefixSum 구하기
        for (int i = 1; i < xLen + 1; i++) {
            for (int j = 1; j < yLen + 1; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                if (check[i][j]) sum[i][j]++;
            }
        }

        return sum;
    }

    static TreeMap<Integer, Integer> gridCompression(PriorityQueue<Integer> PQ) {
        // key : 원래 좌표, value : 압축 좌표
        TreeMap<Integer, Integer> mapper = new TreeMap<>();
        int value = 1;
        while (!PQ.isEmpty()) {
            int key = PQ.poll();
            mapper.put(key, value++);
        }
        return mapper;
    }
}