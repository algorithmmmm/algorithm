package day240305;

import java.util.*;

public class BOJ_1300_백자민 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        long l=1,r=K,ans=0;

        while(l<=r) {
            long mid = (l+r)/2;
            long cnt=0;

            for(int i=1;i<=N;i++) {
                cnt += Math.min(mid/i, N);
            }

            if(K<=cnt) {
                r = mid-1;
                ans = mid;
            } else {
                l = mid+1;
            }

        }

        System.out.println(ans);
    }
}
