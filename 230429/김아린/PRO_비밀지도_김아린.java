class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n]; //정답 배열
        for(int i = 0; i < n; i++) {
            //정수 -> 이진수 변환
            String binary1 = Integer.toBinaryString(arr1[i]);
            String binary2 = Integer.toBinaryString(arr2[i]);
            
            char[] chs1 = binary1.toCharArray();
            char[] chs2 = binary2.toCharArray();
            
            StringBuilder sb = new StringBuilder();
            for(int j =0; j < n; j++) {
                if(chs1[j] == '0' && chs2[j] == '0') { //모두 공백
                    sb.append(' '); //공백
                } else {
                    sb.append('#'); //벽
                }
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}