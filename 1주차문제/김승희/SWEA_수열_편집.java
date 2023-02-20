import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class SWEA_수열_편집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();     // sb : 각 테스트케이스의 답을 저장
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            String[] inArr = br.readLine().split(" ");
            int N = Integer.parseInt(inArr[0]);     // N : 수열의 길이
            int M = Integer.parseInt(inArr[1]);     // M : 연산 횟수
            int L = Integer.parseInt(inArr[2]);     // L : 출력할 인덱스

            LinkedList<Integer> list = new LinkedList<>();  // list : 수열
            inArr = br.readLine().split(" ");
            for (String s : inArr) {
                list.add(Integer.parseInt(s));
            }
            String[] op = new String[M];    // op : 연산 정보
            for(int i = 0; i < M; i++){
                op[i] = br.readLine();
            }
            // end input
            sb.append("#").append(t).append(" ").append(answer(list, op, M, L)).append("\n");
        }
        System.out.println(sb.toString());
    }
    static int answer(LinkedList<Integer> list, String[] op, int M, int L){
        for(int i = 0; i < M; i++){
            String[] opSplit = op[i].split(" ");
            switch (opSplit[0]){
                case "I": {
                    list.add(Integer.parseInt(opSplit[1]), Integer.parseInt(opSplit[2]));
                    break;
                }
                case "D":{
                    list.remove(Integer.parseInt(opSplit[1]));
                    break;
                }
                case "C":{
                    list.set(Integer.parseInt(opSplit[1]), Integer.parseInt(opSplit[2]));
                    break;
                }
            }
        }
        if(list.size() <= L){
            return -1;
        }
        return list.get(L);
    }
}
