import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main {
    static int N;
    static int[] select;    // N-1크기
    static StringBuffer Ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Ans = new StringBuffer();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            select = new int[N - 1];
            permutation(0);
            Ans.append("\n");
        }
        System.out.println(Ans.toString());
    }

    static void permutation(int cnt) {
        if (cnt == N - 1) {
            answer();
            return;
        }
        // 아스키코드 순
        // 0 : ' ', 1 : +, 2 : -
        for (int i = 0; i < 3; i++) {
            select[cnt] = i;
            permutation(cnt + 1);
        }
    }
    static void answer(){
        StringBuffer sb = new StringBuffer();
        int num = 1;
        sb.append(num);
        // 앞에서부터 계산해야 되기 때문에 Queue를 사용
        Queue<Integer> number = new ArrayDeque<>();     // number : 숫자를 넣는 큐
        Queue<Character> op = new ArrayDeque<>();       // op : 연산자를 넣는 큐
        for (int i = 2; i <= N; i++) {
            switch (select[i-2]){
                case 0:     // 공백이면
                    sb.append(" ").append(i);
                    // num 업데이트
                    num *= 10;
                    num += i;
                    break;
                case 1:     // + 
                    number.add(num);    // num을 number로
                    num = i;            // 새로운 num
                    op.add('+');        // +를 op로
                    sb.append("+").append(i);
                    break;
                case 2:
                    number.add(num);    // num을 number러
                    num = i;            // 새로운 num
                    op.add('-');        // -를 op로
                    sb.append("-").append(i);
                    break;
            }
        }
        number.add(num);    // 마지막에 만들어진 num을 number로
        num = number.poll();    // 맨 처음 숫자를 꺼내서 계산 진행
        while (!op.isEmpty()) { 
            int temp = number.poll();
            switch (op.poll()) {
                case '+':
                    num += temp;
                    break;
                case '-':
                    num -= temp;
            }
        }
        if (num == 0) {
            Ans.append(sb.append("\n").toString());
        }
    }
}