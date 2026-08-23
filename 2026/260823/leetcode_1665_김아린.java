class Solution {
    public int minimumEffort(int[][] tasks) {
        // minimum - actual 오름차순 정렬
        Arrays.sort(tasks, (o1, o2) -> (o1[1] - o1[0]) - (o2[1] - o2[0]));

        int answer = 0;
        for(int[] task : tasks) {
            answer = Math.max(answer + task[0], task[1]);
        }
        return answer;
    }
}