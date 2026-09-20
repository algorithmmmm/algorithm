class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] idx = new int[n];

        for(int i = 0; i < n; i++) {
            idx[row[i]] = i; // 인덱스 저장
        }

        int count = 0;
        for(int i = 0; i < n; i += 2) {
            int person = row[i] ^ 1; // 짝
            int j = idx[person]; // 짝의 위치
            int next = row[i + 1]; // 옆사람

            if(next != person) {
                // 옆사람 <-> 짝
                row[i + 1] = person;
                row[j] = next;
                idx[person] = i + 1;
                idx[next] = j;

                count++;
            }
        }
        return count;
    }
}