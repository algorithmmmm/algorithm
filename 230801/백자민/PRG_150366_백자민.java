package day0801;
import java.util.*;
import java.io.*;

public class PRG_150366_백자민 {

	class Solution {
	    
	    static int[] parent;
	    static String[] value;
	    
	    static ArrayList<String> answer;
	    
	    static int find(int x) {
	        if(x==parent[x])
	            return x;
	        
	        return parent[x] = find(parent[x]);
	    }
	    
	    static void union(int x, int y) {
	        x = find(x);
	        y = find(y);
	        
	        if(x!=y) 
	            parent[y] = x;
	    }
	    
	    static int changeNum(int i, int j) {
	        return (50*(i-1))+j;
	    }
	    
	    public String[] solution(String[] commands) {
	        answer = new ArrayList<>();
	        parent = new int[2501];
	        value = new String[2501];
	        
	        for(int i=1;i<=2500;i++) {
	            parent[i] = i;
	            value[i] = "";
	        }
	        
	        StringTokenizer st;
	        int r,c,root;
	        for(String cm: commands) {
	            st = new StringTokenizer(cm);
	            
	            switch(st.nextToken()) {
	                case "UPDATE":
	                    if(st.countTokens()==2) { //UPDATE value1 value2
	                        String origin = st.nextToken();
	                        String after = st.nextToken();
	                        
	                        for(int i=1;i<=2500;i++) {
	                            if(value[i].equals(origin))
	                                value[i] = after;
	                        }
	                    } else { //UPDATE r c value
	                        r = Integer.parseInt(st.nextToken());
	                        c = Integer.parseInt(st.nextToken());
	                        String after = st.nextToken();
	                        value[find(changeNum(r,c))] = after;
	                    }
	                    break;
	                    
	                case "MERGE":
	                    int r1 = Integer.parseInt(st.nextToken());
	                    int c1 = Integer.parseInt(st.nextToken());
	                    int r2 = Integer.parseInt(st.nextToken());
	                    int c2 = Integer.parseInt(st.nextToken());
	                
	                    int root1 = find(changeNum(r1,c1));
	                    int root2 = find(changeNum(r2,c2));
	                    
	                    if(root1 == root2) continue; //이미 같은 셀
	                    
	                    String mergeStr = value[root1].equals("")?value[root2]:value[root1];

	                    value[root1] = "";
	                    value[root2] = "";
	                    
	                    union(root1, root2);
	                    value[root1] = mergeStr;
	                    
	                    break;
	                    
	                case "UNMERGE":
	                    r = Integer.parseInt(st.nextToken());
	                    c = Integer.parseInt(st.nextToken());
	                    int n = changeNum(r,c);
	                    root = find(n);

	                    String tmp = value[root];
	                    value[root] = "";
	                    value[n] = tmp;
	                    
	                    ArrayList<Integer> dlist = new ArrayList<>();
	                    for(int i=1;i<=2500;i++) {
	                        if(find(i) == root)
	                            dlist.add(i);
	                    }
	                    
	                    for(int d: dlist) {
	                        parent[d] = d;
	                    }
	                    break;
	                    
	                case "PRINT":
	                    r = Integer.parseInt(st.nextToken());
	                    c = Integer.parseInt(st.nextToken());
	                    root = find(changeNum(r,c));
	                    
	                    if(value[root].equals(""))
	                        answer.add("EMPTY");
	                    else
	                        answer.add(value[root]);
	                    break;
	            }
	            
	            
	        }

	        return answer.toArray(new String[0]);
	    }
	}
}
