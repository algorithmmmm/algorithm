class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0; // 전체 합
        int tank = 0; // 현재 탱크의 가스 양
        int answer = 0; // 출발 인덱스
        for(int i = 0; i < gas.length; i++) {
            int remain = gas[i] - cost[i]; // 남은 가스
            tank += remain;
            sum += remain;

            if(tank < 0) { // 출발 못함
                answer = i + 1;
                tank = 0;
            }
        }

        return sum < 0 ? -1 : answer;
    }
}