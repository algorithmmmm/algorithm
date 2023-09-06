import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_17609 {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        T = Integer.parseInt(br.readLine());
        ArrayList<Character> input = new ArrayList<>();
        for (int t = 0; t < T; t++) {
            String temp = br.readLine();
            input.clear();
            for (int j = 0; j < temp.length(); j++) {
                input.add(temp.charAt(j));
            }
            sb.append(process(input, 0)).append("\n");
        }
        System.out.println(sb);
    }

    static ArrayList<Character> deepcopy(ArrayList<Character> list, int idx) {
        ArrayList<Character> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == idx) continue;
            temp.add(list.get(i));
        }
        return temp;
    }

    static int process(ArrayList<Character> input, int answer) {
        for (int i = 0; i < input.size() / 2; i++) {
            if (input.get(i) != input.get(input.size() - 1 - i)) {
                if (answer == 0 ) {
                    int temp1 = process(deepcopy(input, i),  1);
                    int temp2 = process(deepcopy(input, input.size() - 1 - i), 1);
                    if (temp1 == 1 || temp2 == 1) return 1;
                    return 2;
                }
                return 2;
            }
        }
        return answer;
    }
}