import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> tallerMan = new ArrayList<>();       // 자기보다 키가 큰 여자랑 춤추고 싶은 남자
        ArrayList<Integer> shorterMan = new ArrayList<>();      // 자기보다 키가 작은 여자랑 춤추고 싶은 남자
        String[] inArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(inArr[i]);
            if (height > 0) {
                tallerMan.add(height);
            } else {
                shorterMan.add(-height);
            }
        }

        ArrayList<Integer> tallerWoman = new ArrayList<>();     // 자기보다 키가 큰 남자랑 춤추고 싶은 여자
        ArrayList<Integer> shorterWoman = new ArrayList<>();    // 자기보다 키가 작은 남자랑 춤추고 싶은 여자
        inArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(inArr[i]);
            if (height > 0) {
                tallerWoman.add(height);
            } else {
                shorterWoman.add(-height);
            }
        }

        // 오름차순 정렬
        Collections.sort(tallerMan);
        Collections.sort(tallerWoman);
        Collections.sort(shorterMan);
        Collections.sort(shorterWoman);

        int answer  = 0;   // answer : 만들 수 있는 커플의 수

        // 남자 1번 유형 + 여자 2번 유형
        answer += match(tallerMan, shorterWoman);
        // 남자 2번 유형 + 여자 1번 유형
        answer += match(tallerWoman, shorterMan);

        System.out.println(answer);
    }

    // taller : 키가 큰 애를 만나고 싶음
    // shorter : 키가 작은 애를 만나고 싶음
    static int match(ArrayList<Integer> taller, ArrayList<Integer> shorter) {
        int acc = 0;
        int t = 0;
        int s = 0;

        while (t < taller.size() && s < shorter.size()) {
            if (taller.get(t) < shorter.get(s)) {   // shorter가 taller보다 키가 크면 -> 커플 만들기
                acc++;
                t++;
                s++;
            } else {    // shorter가 taller보다 키가 작거나 같으니까 -> 커플 못 만듬
                // shorter가 더 커야 되니까 다음 친구로
                s++;
            }
        }
        return acc;
    }
}
