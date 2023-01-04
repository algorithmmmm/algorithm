import java.util.*;
/*
큐의 합을 기억해두었다가, 
합이 더 큰 큐에서 pop해서 더 작은 큐에 insert하는 방식으로 풀었다.
*/
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        ArrayDeque<Long> Q1 = Qinit(queue1);    // Q1 : queue1의 값을 담은 큐
        long cnt1= Q1.pollLast();   // cnt1 : queue1의 모든 원소의 합
        ArrayDeque<Long> Q2 = Qinit(queue2);    //// Q2 : queue2의 값을 담은 큐
        long cnt2= Q2.pollLast();   // cnt2 : queue2의 모든 원소의 합
        long sum = cnt1 + cnt2;     // sum : 두 큐의 전체 합
        if(sum % 2 == 1){ // 전체 합이 홀수인 경우
            return -1;
        }
        int answer = 0;     // answer : 최소 연산의 수
        int cnt = 0;        // cnt : 연산의 수
        while(cnt != 10_000_000){
            // Q1의 합이 더 크면 Q1에서 꺼내서 Q2에 넣기
            if(cnt1 > cnt2){
                long a = Q1.poll();
                if(Q1.isEmpty()){
                    return -1;
                }
                cnt1 -= a;
                Q2.add(a);
                cnt2 += a;
            }else if(cnt1 == cnt2){ // 두 큐의 합이 같은 경우
                break;
            }else{  // cnt1 < cnt2
                // Q2의 합이 더 크면 Q2에서 꺼내서 Q1에 넣기
                long a = Q2.poll();
                if(Q2.isEmpty()){
                    return -1;
                }
                cnt2 -= a;
                Q1.add(a);
                cnt1 += a;
            }
            answer++;
            cnt++;
        }
        // 각 큐의 원소 합이 같지 않은 경우
        if(cnt == 10_000_000){
            answer = -1;
        }
        return answer;
    }
    static ArrayDeque<Long> Qinit(int[] arr){
        ArrayDeque<Long> Q = new ArrayDeque<>();
        long sum = 0;
        for(int i = 0; i < arr.length; i++){
            Q.add((long)arr[i]);
            sum += arr[i];
        }
        Q.add(sum);
        return Q;
    }
}