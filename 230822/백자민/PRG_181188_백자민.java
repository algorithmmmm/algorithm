import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        PriorityQueue<Bullet> pq = new PriorityQueue<>();
        
        for(int[] t: targets) {
            pq.offer(new Bullet(t[0], t[1]));
        }
        
        int cnt = 1;
        int last = pq.poll().e-1;
        while(!pq.isEmpty()) {
            Bullet tmp = pq.poll();

            if(last<=tmp.e && last >=tmp.s) continue;
            
            cnt++;
            last = tmp.e-1;
        }
        
        return cnt;
    }
    
    static class Bullet implements Comparable<Bullet> {
        int s,e;
        
        public Bullet(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Bullet b) {
            return this.e-b.e; 
        }
    }
    
}
