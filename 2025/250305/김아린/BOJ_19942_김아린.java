package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_다이어트 {
	static int N;
	static int[] goal;
	static int[][] arr;
	static boolean[] checked;
	static int min;
	static List<Integer> result_list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][5];
		
		goal = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			goal[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 구현
		min = Integer.MAX_VALUE;
		result_list = new ArrayList<>();
		
		for(int m = 1; m <= N; m++) {
			checked = new boolean[N];
			combination(0, 0, m);
		}
		
		// 출력
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
			for(int idx : result_list) {
				System.out.print(idx + " ");
			}
		}
		
	}
	
	public static void combination(int start, int depth, int m) {
		if(depth == m) {	
			calculate();
			return;
		}
		
		for(int i = start; i < N; i++) {
			checked[i] = true;
			combination(i + 1, depth + 1, m);
			checked[i] = false;
		}
	}

	public static void calculate() {
		// 각 영양성분, 비용 합 구하기
		int[] sum = new int[5];
		List<Integer> tmp_list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			if(checked[i]) {
				for(int j = 0; j < 5; j++) {
					sum[j] += arr[i][j];
				}
				tmp_list.add(i + 1);
			}
		}
		
		// 가능한 조합이면 결과 갱신
		boolean isPossible = true;
		for(int i = 0; i < 4; i++) {
			if(sum[i] < goal[i]) {
				isPossible = false;
				break;
			}
		}
		
		if(isPossible && sum[4] < min) {
            min = sum[4];
            result_list = new ArrayList<>(tmp_list);
        } else if (isPossible && sum[4] == min) {
            if(compareLists(tmp_list, result_list) < 0) {
                result_list = new ArrayList<>(tmp_list);
            }
        }
	}
	
	// 사전순으로 빠른지 비교
	public static int compareLists(List<Integer> list1, List<Integer> list2) {
        int size = Math.min(list1.size(), list2.size());
        for(int i = 0; i < size; i++) {
            if(list1.get(i) < list2.get(i)) return -1;
            if(list1.get(i) > list2.get(i)) return 1;
        }
        if(list1.size() < list2.size()) return -1;
        if(list1.size() > list2.size()) return 1;
        return 0;
    }

}
