import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_새로운_불면증_치료법 {
    static int END = (1 << 10) -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());        // T : testcase의 수
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine());    // N : 첫 번쨰 양의 번호
            sb.append("#").append(t).append(" ").append(Count(N)).append("\n");
        }
        System.out.println(sb.toString());
    }
    static int Count(int N){
        int idx = 0;
        int check = 0;
        while(check != END){
            idx++;
            char[] ch = String.valueOf(N * idx).toCharArray();
            for (char c : ch) {
                check = check |  (1 <<(c -'0'));
            }
        }
        return idx * N;
    }
}
