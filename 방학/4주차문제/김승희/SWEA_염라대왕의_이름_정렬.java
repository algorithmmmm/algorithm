import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_염라대왕의_이름_정렬 {
    static class NameSort implements Comparator<String>{
        @Override
        public int compare(String a, String b) {
            if(a.length() < b.length()){    // a의 길이가 작으면 -1
                return -1;
            }else if(a.length() == b.length()){ // 둘의 길이가 같은 경우에는
                return a.compareTo(b);          // a와 b를 compareTo한 결과를 return
            }else{  // a.length() > b.length()  b의 길이가 작으면 1
                return 1;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());    // T : testcase의 수
        StringBuilder sb = new StringBuilder();         // sb : 각 testcase 별 정답을 저장
        NameSort nameSort = new NameSort();             // nameSort : 정렬 기준
        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine());     // N : 이승 명부의 이름 개수
            HashSet<String> set = new HashSet<>();          // set : 이승 명부의 이름 집합
            for(int i = 0; i < N; i++){
                set.add(br.readLine());
            }
            ArrayList<String> names = new ArrayList<>(set);     // names : set을 List 형태로 변환
            Collections.sort(names, nameSort);              // nameSort를 기준으로 정렬
            sb.append("#").append(t).append("\n");
            for (String name : names) {
                sb.append(name).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}

/*
2
5
nn
name
nb
ho
nam
6
ss
sa
sac
sab
saca
sss

#1
ho
nb
nn
nam
name
#2
sa
ss
sab
sac
sss
saca
 */