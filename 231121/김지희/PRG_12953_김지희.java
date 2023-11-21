package programmers;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        
        for(int i=1; i<arr.length; i++){
            int gcd = getGCD(arr[i-1], arr[i]);
            arr[i] = arr[i-1] * arr[i] / gcd;
        }
        answer = arr[arr.length-1];
        return answer;
    }
    
    public static int getGCD(int a, int b){
        if(a%b==0){
            return b;
        }
        return getGCD(b, a%b);
    }
}
