
import java.util.*;

public class 단어변환 {
    static HashMap<String, ArrayList<String>> adj;
    static HashMap<String, int[]> alpha;
    static HashMap<String, Boolean> visit;

    static class Tuple {
        String str;
        int cnt;

        public Tuple(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }

    public int solution(String begin, String target, String[] words) {
        // 각 단어가 가지는 알파벳 개수
        initAlpha(begin, words);
        // 각 단어와 한글자 다른 단어들의 list
        initAdj(begin, words);
        // adj가지고 BFS
        int answer = Math.max(0, BFS(begin, target));
        return answer;
    }

    public int BFS(String begin, String target) {
        Queue<Tuple> Q = new ArrayDeque<>();
        Q.add(new Tuple(begin, 0));
        visit.put(begin, true);
        while (!Q.isEmpty()) {
            Tuple pnt = Q.poll();
            ArrayList<String> temp = adj.get(pnt.str);
            for (int i = 0; i < temp.size(); i++) {
                String str = temp.get(i);
                if (visit.get(str)) continue;
                if (str.equals(target)) return pnt.cnt + 1;
                Q.add(new Tuple(str, pnt.cnt + 1));
                visit.put(str, true);
            }
        }
        return -1;
    }

    public void initAlpha(String begin, String[] words) {
        alpha = new HashMap<>();
        alpha.put(begin, getAlpha(begin));
        for (int i = 0; i < words.length; i++) {
            alpha.put(words[i], getAlpha(words[i]));
        }
    }

    public void initAdj(String begin, String[] words) {
        adj = new HashMap<>();
        visit = new HashMap<>();
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (check(begin, words[i])) temp.add(words[i]);
        }
        adj.put(begin, temp);
        visit.put(begin, false);
        for (int i = 0; i < words.length; i++) {
            ArrayList<String> temp1 = new ArrayList<>();
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;
                if (check(words[i], words[j]))
                    temp1.add(words[j]);
            }
            adj.put(words[i], temp1);
            visit.put(words[i], false);
        }
    }

    public boolean check(String a, String b) {
        if (a.length() != b.length()) return false;
        if (!alphaCheck(alpha.get(a), alpha.get(b))) return false;
        boolean flag = false;
        for (int j = 0; j < a.length(); j++) {
            if (a.charAt(j) != b.charAt(j)) {
                if (flag) {
                    return false;
                }
                flag = true;
            }
        }
        return true;
    }

    public int[] getAlpha(String str) {
        // alpha array
        int[] alpha = new int[26];
        for (int i = 0; i < str.length(); i++) {
            alpha[str.charAt(i) - 'a']++;
        }
        return alpha;
    }

    public boolean alphaCheck(int[] bAlpha, int[] tAlpha) {
        int flag = 0;
        for (int i = 0; i < 26; i++) {
            int temp = Math.abs(bAlpha[i] - tAlpha[i]);
            if (temp == 0) {
                continue;
            } else if (temp == 1) {
                if (flag == 2) return false;
                flag++;
            } else {
                return false;
            }
        }
        return true;
    }
}