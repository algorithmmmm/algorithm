import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();

        // key : alphabet, value : count
        HashMap<Character, Integer> map = new HashMap<>();
        int head = 0;
        int tail = 0;
        map.put(input[tail], 1);
        int answer = 0;
        while(head <= tail) {
            if (map.size() > N) {           // 인식할 수 있는 알파벳 종류를 넘었으면
                while (map.size() != N) {   // 인식할 수 있는 알파벳 종류를 N개로 조정
                    int temp = map.get(input[head]) - 1;
                    if (temp == 0) {
                        map.remove(input[head]);
                    } else {
                        map.put(input[head], temp);
                    }
                    head++;
                }
            }
            // N개가 넘으면 위에서 조정하고 내려오기 때문에
            // 이 위치에서는 최대 N개일 수 밖에 없다.
            // 문제 조건이 최대 N개를 인식하는 것이기 때문에 N보다 작은 경우의 문자열도 count 해줘야 한다.
            // 2
            // aaaaa
            // 로 입력이 주어진 경우 답은 5여야 한다.
            answer = Math.max(answer, tail - head + 1);
            tail++;
            if (tail == input.length) break;
            map.put(input[tail], map.getOrDefault(input[tail], 0) + 1);
        }
        System.out.println(answer);
    }
}