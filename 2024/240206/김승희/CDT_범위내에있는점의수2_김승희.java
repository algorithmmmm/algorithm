import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inArr = br.readLine().split(" ");
        int N = Integer.parseInt(inArr[0]);
        int Q = Integer.parseInt(inArr[1]);
        inArr = br.readLine().split(" ");
        int[] number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(inArr[i]);
        }
        Arrays.sort(number);
        TreeMap<Integer, Integer> treeMap = init(number);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            inArr = br.readLine().split(" ");
            int start = Integer.parseInt(inArr[0]);
            int end = Integer.parseInt(inArr[1]);
            sb.append(inRange(treeMap, start, end)).append("\n");
        }
        System.out.println(sb);
    }

    static TreeMap<Integer, Integer> init(int[] number) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int sum = 0;
        for (int i = 0; i < number.length; i++) {
            sum++;
            treeMap.put(number[i], sum);
        }
        return treeMap;
    }

    static int inRange(TreeMap<Integer, Integer> treeMap, int start, int end) {
        Integer nStart = treeMap.lowerKey(start);       // start보다 작은 key
        Integer nEnd = treeMap.floorKey(end);           // end와 같거나 작은 key
        int valStart = 0;
        int valEnd = 0;
        if (nStart != null) {
            valStart = treeMap.get(nStart);
        }
        if (nEnd != null) {
            valEnd = treeMap.get(nEnd);
        }
        return valEnd - valStart;
    }
}