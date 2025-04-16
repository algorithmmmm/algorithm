import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
    static int N;
    static int[] NUM;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        NUM = new int[N+1];
        TreeSet<Integer> set = new TreeSet<>();     // set : 입력으로 들어온 숫자들의 set
        for (int i = 1; i < N+1; i++){
            NUM[i] = Integer.parseInt(br.readLine());
            set.add(NUM[i]);
        }
        // end input

        if (set.size() == 1) {  // set의 크기가 1이라면 입력으로 들어온 숫자가 1개
            System.out.println(1);
            // 입력으로 들어온 숫자가 1개이기 떄문에 어차피 선택하는 숫자는 1개
            System.out.println(NUM[1]);
        } else if (set.size() == N) {   // set의 크기가 N이라면 다 선택하면 된다.
            System.out.println(N);
            for (int i = 1; i < N + 1; i++) {
                System.out.println(i);
            }
        }else{
            // newSet : set에 들어있는 숫자를 index로 해서 NUM 배열의 값을 가져온 새로운 TreeSet
            TreeSet<Integer> newSet = update(set);
            while (set.size() != newSet.size()) {   // set과 newSet의 크기가 같을 떄까지 반복
                set = newSet;
                newSet = update(set);
            }
            // set과 newSet의 크기가 같다면 정답을 구한 것
            System.out.println(set.size());
            for (Integer i : set) {     // TreeSet이라 정렬을 보장하기 떄문에 그냥 출력
                System.out.println(i);
            }
        }
    }
    static TreeSet<Integer> update(TreeSet<Integer> set){
        TreeSet<Integer> ans = new TreeSet<>();
        for (Integer i : set) {
            ans.add(NUM[i]);
        }
        return ans;
    }
}