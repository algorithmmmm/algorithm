package day1121;

import java.util.*;

public class PRG_118670_백자민 {

	class Solution {
	    
	    static int R,C;
	    
	    static Deque<Integer> left, right;
	    static Deque<Deque<Integer>> center;
	    
	    static void rotate() {
	        if(center.peek().size()>0) {
	            //left deque 연산
	            center.peek().offerFirst(left.pollFirst()); // 1번
	            left.offerLast(center.peekLast().pollFirst()); // 2번

	            //right deque 연산
	            right.offerFirst(center.peek().pollLast()); // 3번
	            center.peekLast().offerLast(right.pollLast()); //4번
	        } else {
	            right.offerFirst(left.pollFirst());
	            left.offerLast(right.pollLast());
	        }
	    }
	    
	    static void shift() {
	        left.offerFirst(left.pollLast());
	        right.offerFirst(right.pollLast());
	        center.offerFirst(center.pollLast());
	    }
	    
	    static void init(int[][] rc) {
	        for(int i=R-1;i>=0;i--) {
	            left.offerFirst(rc[i][0]);
	            right.offerFirst(rc[i][C-1]);
	        }
	        
	        for(int i=R-1;i>=0;i--) {
	            Deque<Integer> tmp = new ArrayDeque<>();
	            for(int j=C-2;j>0;j--) {
	                tmp.offerFirst(rc[i][j]);
	            }
	            center.offerFirst(tmp);
	        }
	    }
	    
	    static int[][] print() {
	        int[][] answer = new int[R][C];
	        
	        for(int i=0;i<R;i++) {
	            answer[i][0] = left.pollFirst();
	            answer[i][C-1] = right.pollFirst();
	            
	            for(int j=1;j<C-1;j++) {
	                answer[i][j] = center.peek().pollFirst();
	            }
	            center.pollFirst();
	        }
	        
	        return answer;
	    }
	    
	    public int[][] solution(int[][] rc, String[] operations) {
	        
	        left = new ArrayDeque<>();
	        right = new ArrayDeque<>();
	        center = new ArrayDeque<>();
	        
	        R = rc.length;
	        C = rc[0].length;
	        
	        init(rc); //초기 세팅
	        
	        for(String o: operations) {
	            if(o.equals("Rotate"))
	                rotate();
	            else
	                shift();
	        }
	        
	        return print();
	    }
	}
}
