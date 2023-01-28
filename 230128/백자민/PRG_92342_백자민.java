package day230128;

class Solution {
    static int[] r, a, ans;
    static boolean win;
    static int max;
    
    private static void dfs(int idx, int rest, int peach, int ryan) {
	    if(idx == 11) { //점수판 끝까지 간 경우 
	    	
	        if(rest == 0 && ryan > peach) { //남은 화살 개수가 0개이고 라이언 점수가 높은 경우 
	            win = true; //이김 표시 
	            
	            if(max < (ryan - peach)) { //점수 최댓값 갱신 
	                max = ryan - peach;
	                ans = r.clone();
	            } else if(max == (ryan - peach)) {  //최대점수랑 똑같을 경우 
	                for (int i=10;i>=0;i--) {
	                    if(r[i] > ans[i]) {  
	                        ans = r.clone();
	                        return;
	                    } else if(r[i] < ans[i]) return;
	                }
	            }
	            
	        }
	        return;
	    }
	
	
	    // 둘다 0점 -> 점수X
	    if(a[idx]==0) {
	        dfs(idx+1, rest, peach, ryan);
	    } 
	
	    // 0점 아니고 득점하려면 어피치보다 많이 쏴야 함. 화살은 최소한으로 쓰기(1개차이) 
	    if(rest>a[idx]) {
	        r[idx] = a[idx]+1;
	        dfs(idx+1, rest-a[idx]-1, peach, ryan+(10-idx));
	        r[idx] = 0;
	    }
	
	    // 득점X: 어피치랑 똑같이 쏘기 or 적게 쏘기 
	    if(a[idx]!=0) {
	        for (int i=0;i<a[idx];i++) {
	            if(rest>=i) {
	                r[idx] = i;
	                dfs(idx+1, rest-i, peach+(10-idx), ryan);
	                r[idx] = 0;
	            }
	        }
	    }
	}

    public static int[] solution(int n, int[] info) {
        a = info.clone();
        r = new int[11];

        max = 0;
        ans = new int[11];
        win = false;
        dfs(0, n, 0, 0);
        
        ans = win? ans: new int[] {-1}; //졌으면 -1담은 배열 리턴 

        return ans; 
    }

	
}
