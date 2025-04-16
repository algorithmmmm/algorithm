import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    static TreeMap<Integer, Integer> number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        number = new TreeMap<>(); // key : 자연수, value ; 개수
        for (int i = 0; i < N; i++) {
            String[] inArr = br.readLine().split(" ");
            int x = Integer.parseInt(inArr[0]);
            int y = Integer.parseInt(inArr[1]);
            number.put(y, x);   // y가 x개 있다.
        }
        // end input]

        // M/2개의 쌍을 만들어서 두 자연수의 합이 가장 큰 쌍의 합을 가장 작게 하기 위해서는
        // 가장 큰 수와 가장 작은 수끼리 쌍을 만들어나가면서 두 자연수의 합이 가장 큰 것을 찾으면 된다.
        long sum = 0;

        while (!number.isEmpty()) {
            sum = Math.max(sum, getSum(number.firstKey(), number.lastKey()));
        }
        System.out.println(sum);
    }

    static long getSum(int small, int big) {
        int smallValue = number.get(small);
        int bigValue = number.get(big);
        int minValue = Math.min(smallValue, bigValue);
        putNumber(small, smallValue, minValue);
        putNumber(big, bigValue, minValue);
        return small + big;
    }

    static void putNumber(int key, int value, int cnt) {
        if (value - cnt == 0) {
            number.remove(key);
        } else {
            number.put(key, value - cnt);
        }
    }
}