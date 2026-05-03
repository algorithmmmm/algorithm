import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] arr = new int[26]; // 작업별 개수
        for (char task : tasks) {
            arr[task - 'A']++;
        }

        // 개수 내림차순 우선순위 큐
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int a : arr) {
            if(a > 0) {
                queue.offer(a);
            }
        }

        int time = 0;
        while(!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int length = n + 1; // 묶음 길이
            int count = 0;

            // 묶음 길이만큼 처리
            while(!queue.isEmpty() && length > 0) {
                int p = queue.poll();
                if(p - 1 > 0) { // 다시 큐에 넣기
                    temp.add(p - 1);
                }
                count++;
                length--;
            }

            // 남은거 다시 넣기
            for(int t : temp) {
                queue.offer(t);
            }

            // 끝부분 아니면 묶음 수, 끝부분이면 처리 수 더하기
            time += queue.isEmpty() ? count : n + 1;
        }

        return time;
    }
}