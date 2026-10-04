import java.util.*;

class Solution {
    public String reorganizeString(String s) {
        int[] arr = new int[26]; // 문자별 카운트
        for(char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]); // [문자, 카운트] : 카운트 내림차순 정렬

        for(int i = 0; i < 26; i++) {
            if(arr[i] > 0) {
                pq.add(new int[]{i, arr[i]});
            }
        }

        StringBuilder result = new StringBuilder();

        int[] prev = null; // 이전 문자
        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            result.append((char)('a' + p[0]));
            p[1]--;

            // 이전 문자 넣기
            if(prev != null && prev[1] > 0) {
                pq.add(prev);
            }

            // 현재 문자 저장
            prev = p;
        }

        return result.length() == s.length() ? result.toString() : "";
    }
}