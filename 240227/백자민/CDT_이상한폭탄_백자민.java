package day240227;

import java.util.*;

public class CDT_이상한폭탄_백자민 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] arr = new int[N];
        int[] dp = new int[N];
        HashMap<Integer, Integer> map = new HashMap<>(); 

        for(int i=0;i<N;i++) {
            arr[i] = sc.nextInt();

            if(map.containsKey(arr[i])) { //터질 폭탄
                dp[i] = map.get(arr[i]);
            } else {
                dp[i] = -1;
            }

            map.put(arr[i], i);
        }

        int ans=-1;
        for(int i=0;i<N;i++) {
            if(dp[i]>0 && Math.abs(dp[i]-i)<=K)
                ans = Math.max(ans, arr[i]);
        }

        System.out.println(ans);
    }
}
