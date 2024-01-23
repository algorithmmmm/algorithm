import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class BOJ_2671 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        // eof를 만날 때까지
        while (str != null && !str.equals("")){
            char[] arr = str.toCharArray();
            sb.append(check(arr) ? "SUBMARINE" : "NOISE").append("\n");
            str = br.readLine();
        }
        System.out.println(sb);
    }
    static boolean check(char[] arr) {
        int N = arr.length;
        boolean chk_100 = false;
        boolean chk_1 = false;
        for (int idx = 0; idx < N; idx++) {
            System.out.println("idx : " + idx);
            if (arr[idx] == '1') {  // 1, 100, 101 중 1개
                if (idx + 2 < N && arr[idx + 1] == '0' && arr[idx+2] == '0') { // 100
                    chk_1 = false;
                    idx += 2;
                    if (chk_100) return false;
                    chk_100 = true;
                    while (idx+1 < N && arr[idx+1] == '0') {    // 0은 반복 가능
                        idx++;
                    }
                    System.out.println("100, idx : " + idx + " chk_100 : " + chk_100);
                    continue;
                }
                if (!chk_100 && !chk_1) return false;
                chk_100 = false;
                chk_1 = true;
                System.out.println("1, idx : " + idx + " chk_100 : " + chk_100);
            } else {    // arr[idx] == '0'
                if (chk_100) return false;

                if (idx + 1 < N && arr[idx + 1] == '1') {
                    chk_1 = false;
                    idx += 1;
                    System.out.println("01, idx : " + idx + " chk_100 : " + chk_100);
                    continue;
                }
                return false;
            }
        }
        return !chk_100;
    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str = br.readLine();
//        StringBuilder sb = new StringBuilder();
//        String REGEXP = "(100+1+|01)+";
//        // eof를 만날 때까지
//        while (str != null && !str.equals("")){
//            boolean chk = Pattern.matches(REGEXP, str);
//            sb.append(chk ? "SUBMARINE" : "NOISE").append("\n");
//            str = br.readLine();
//        }
//        System.out.println(sb);
//    }
}
