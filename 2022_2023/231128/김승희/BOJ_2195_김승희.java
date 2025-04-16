import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] str;
    static int idx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        str = br.readLine().toCharArray();
        idx = 0;
        int cnt = 0;
        while (idx != str.length) {
            check(origin);
            cnt++;
        }
        System.out.println(cnt);
    }
    static void check(String origin){
        int temp = 0;
        for (int i = 0; i < origin.length(); i++) {
            if (origin.charAt(i) == str[idx]) {
                temp = Math.max(getLen(origin, i), temp);
            }
        }
        idx += temp;
    }

    static int getLen(String origin, int start) {
        for (int i = 0; i <= Math.max(origin.length(), str.length); i++) {
            if (start + i == origin.length() || idx + i == str.length) {
                return i;
            }
            if (origin.charAt(start + i) != str[idx + i]) {
                return i;
            }
        }
        return 1;
    }

}