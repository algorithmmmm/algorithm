import java.util.*;

class Solution {
    public int findContentChildren(int[] g, int[] s) { // 아이, 쿠키
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0; // 아이 포인터
        int j = 0; // 쿠키 포인터
        while(i < g.length && j < s.length) {
            if(s[j] >= g[i]) {
                i++; // 다음 아이로
            }
            j++;
        }

        return i;
    }
}