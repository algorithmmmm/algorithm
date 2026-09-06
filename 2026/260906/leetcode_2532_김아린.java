import java.util.*;

class Solution {
    // 덜 호율적인 인부가 우선
    // time[i] = [righti, picki, lefti, puti]

    public int findCrossingTime(int n, int k, int[][] time) { // 옮겨야하는 박스 수, 인부 수, 시간 
        int[] efficient = new int[k];
        for(int i = 0; i < k; i++) {
            efficient[i] = time[i][0] + time[i][2];
        }

        // 왼쪽 대기 큐
        PriorityQueue<Integer> leftQueue = new PriorityQueue<>((o1, o2) -> efficient[o1] != efficient[o2] ? efficient[o2] - efficient[o1] : o2 - o1);
        // 오른쪽 대기 큐
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>((o1, o2) -> efficient[o1] != efficient[o2] ? efficient[o2] - efficient[o1] : o2 - o1);

        // 오른쪽에서 올리는 작업중 인부 큐 [idx, 현재시각]
        PriorityQueue<int[]> pickQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); 
        // 왼쪽에서 내리는 작업중 인부 큐
        PriorityQueue<int[]> putQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); 

        // 왼쪽 큐에 넣기
        for(int i = 0; i < k; i++) {
            leftQueue.add(i);
        }

        // 작업 돌리기
        int count = 0; // 왼쪽에 도착한 박스 수
        int remain = n; // 남은 박스 수
        int cur = 0; // 현재 시간
        int answer = 0; // 최종 작업 시간
        while(count < n) {
            // 다리 건널 인부 선택
            if(!rightQueue.isEmpty()) { // 오른쪽 우선
                int idx = rightQueue.poll();
                cur += time[idx][2]; // 오른쪽 -> 왼쪽 이동
                putQueue.add(new int[]{idx, cur + time[idx][3]}); // 내리기
                count++; // 박스 전달 완료
                answer = cur;
            } else if(!leftQueue.isEmpty() && remain > 0) { // 그 다음 왼쪽 우선
                int idx = leftQueue.poll();
                cur += time[idx][0]; // 왼쪽 -> 오른쪽 이동
                pickQueue.add(new int[]{idx, cur + time[idx][1]});
                remain--;
            } else { // 대기 인부 없음 = 작업중 -> 시간 옮기기
                int next;
                if (pickQueue.isEmpty()) {
                    next = putQueue.peek()[1];
                } else if (putQueue.isEmpty()) {
                    next = pickQueue.peek()[1];
                } else {
                    next = Math.min(pickQueue.peek()[1], putQueue.peek()[1]);
                }
                cur = next;
            }

            // 작업 끝난 인부 대기 큐로 이동
            while(!pickQueue.isEmpty() && pickQueue.peek()[1] <= cur) {
                rightQueue.add(pickQueue.poll()[0]);
            }
            while(!putQueue.isEmpty() && putQueue.peek()[1] <= cur) {
                leftQueue.add(putQueue.poll()[0]);
            }
        }

        return answer;
    }
}