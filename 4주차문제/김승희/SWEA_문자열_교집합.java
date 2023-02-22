import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class SWEA_문자열_교집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // T : testcase의 수;
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= T; i++){
            String[] inArr = br.readLine().split(" ");
            int N = Integer.parseInt(inArr[0]);     // N : 집합 A의 크기
            int M = Integer.parseInt(inArr[1]);     // M : 집합 B의 크기
            String[] A = br.readLine().split(" ");  // A : 문자열 집합 A
            String[] B = br.readLine().split(" ");  // B : 문자열 집합 B
            sb.append("#").append(i).append(" ").append(intersection(A, B)).append("\n");
        }
        System.out.println(sb.toString());
    }

    /**
     *
     * @param A : 문자열 집합 A
     * @param B : 문자열 집합 B
     * @return  A와 B의 교집합에 속하는 원소의 수
     */
    static int intersection(String[] A, String[] B) {
        HashMap<String, Integer> hash = new HashMap<>();    // hash : key는 문자열, value는 개수
        for (String a : A) {        // 문자열 집합 A의 원소를 key로 하고 1을 value로 해서 hash에 넣는다.
            hash.put(a, 1);
        }
        int sum = 0;    // sum : 교집합에 속하는 원소의 수
        for (String b : B) {        // 문자열 집합 B의 원소가 hash에 있는지 확인한다.
            sum += hash.getOrDefault(b, 0);     // hash에 존재하면 1을 반환하고 그렇지 않으면 0을 반환
        }
        return sum;
    }
}

/*
2
2 3
ab a
b ac ba
3 3
aa bb cc
bb cc aa

#1 0
#2 3
 */