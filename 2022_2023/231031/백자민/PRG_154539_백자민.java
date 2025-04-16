package day1031;

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Item> st = new Stack<>();
        
        for(int i=0,size=numbers.length;i<size;i++) {
            
            int tmp = numbers[i];
            
            if(!st.isEmpty() && st.peek().num<tmp) {
                while(st.peek().num<tmp) {
                    Item cur = st.pop();
                    answer[cur.idx] = tmp;
                    
                    if(st.isEmpty()) break;
                }
            }
            
            st.push(new Item(i,tmp));
            
        }
        
        if(!st.isEmpty()) {
            while(!st.isEmpty()) {
                Item cur = st.pop();
                answer[cur.idx] = -1;
            }
        }
        
        
        return answer;
    }
    
    static class Item {
        int idx, num;
        public Item(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
}
