import java.util.*;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>(); //합 저장(중복x)
        
        for(int i = 1; i <= elements.length; i++) { //길이가 i인 연속 부분 수열 구하기
            int[] arr = new int[elements.length + i - 1]; //원형 수열
            
            for(int j = 0; j < elements.length; j++) {
                arr[j] = elements[j];
            }
            
            for(int j = 0; j < i - 1; j++) { //i - 1 만큼 추가로 넣기
                arr[elements.length + j] = elements[j];
            }
            
            //합 구하기
            int sum = 0;
            for(int k = 0; k < i; k++) { //구간합 초기 합
                sum += arr[k];
            }
            set.add(sum);
            
            for(int k = 1; k < elements.length; k++) { //구간 별 합 구하기
                sum -= arr[k - 1]; //이전 수 빼기
                sum += arr[k + (i - 1)]; //새로 들어오는 수 더하기
                
                set.add(sum);
            }
        }
        
        int answer = set.size();
        return answer;
    }
}