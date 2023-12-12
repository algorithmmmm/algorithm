import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0, right = people.length - 1;
        int count = 0;
        
        while(left <= right) {
        	if(people[left] + people[right] <= limit) { //제한 초과X
        		left++; //같이 구출
        		right--;
        	} else { //제한 초과
        		right--; //무거운 사람 먼저 구출
        	}
        	count++;
        }
        
        return count;
    }
}