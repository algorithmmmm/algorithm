import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_이진수_표현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());        // T : testcase의 수
        StringBuilder sb = new StringBuilder();         // sb : 각 testcase의 정답을 모아둔 곳
        for(int t = 1; t <= T; t++){
            String[] inArr = br.readLine().split(" ");
            sb.append("#").append(t);
            if(OnOff(Integer.parseInt(inArr[0]), Integer.parseInt(inArr[1]))){
                sb.append(" ON\n");
            }else{
                sb.append(" OFF\n");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     *
     * @param N : 마지막 N bit
     * @param M : 이진수 M
     * @return 마지막 N bit가 모두 1이면 true
     */
    static boolean OnOff(int N, int M){
        int check = (1 << N)-1;     // check : 각 값이 모두 1인 N 길이의 이진수
        if((M & check) == check){
            return true;
        }
        return false;
    }
}

/*
5
4 15
4 16
4 31
3 7
3 25

#1 ON
#2 OFF
#3 ON
#4 ON
#5 OFF
 */