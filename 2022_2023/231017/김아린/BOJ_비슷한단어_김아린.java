import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		List<String> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			if(!list.contains(str)) { //중복 제거
				list.add(str);
			}
		}
		
		int maxM = 0;
		int sIdx = 0;
		int tIdx = 0;
		
		for(int i = 0; i < list.size() - 1; i++) {
			for(int j = i + 1; j < list.size(); j++) {
				int m = getM(list.get(i), list.get(j));
				
				if(m > maxM) {
					maxM = m;
					sIdx = i;
					tIdx = j;
				}
			}
		}
		
		//출력
		System.out.println(list.get(sIdx));
		System.out.println(list.get(tIdx));
	}

	public static int getM(String str1, String str2) {
		int minLen = Math.min(str1.length(), str2.length());
		
		int count = 0;
		for(int i = 0; i < minLen; i++) {
			if(str1.charAt(i) != str2.charAt(i)) break;
			
			count++;
		}
				
		return count;
	}

}

//최적화 버전 -> 57% 오답
public class BOJ_비슷한단어2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		List<String> list = new ArrayList<>(); //인덱스 맞는 단어 저장
		HashMap<String, Integer> map = new HashMap<>(); //영단어, 인덱스
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			map.put(str, i);
			list.add(str);
		}
		
		//맵 정렬(key)
		List<String> keyList = new ArrayList<>(map.keySet());
		keyList.sort((s1, s2) -> s1.compareTo(s2));
		
		//앞에서부터 단어 2개씩 비교
		int maxM = 0;
		int sIdx = N;
		int tIdx = N;
		
		for(int i = 0; i < N - 1; i++) {
			String str1 = keyList.get(i);
			String str2 = keyList.get(i + 1);
			
//			System.out.println(str1 + " " + str2);
			
			int m = getM(str1, str2);
//			System.out.println(m);
			
			int idx1 = map.get(str1);
			int idx2 = map.get(str2);
			
			if(m > maxM) { //M이 최대
				maxM = m;
				sIdx = Math.min(idx1, idx2);
				tIdx = Math.max(idx1, idx2);
			} else if(m == maxM) { //같을 때, 반례 주의
				HashSet<Integer> set = new HashSet<>();
				set.add(sIdx);
				set.add(tIdx);
				set.add(idx1);
				set.add(idx2);
				
				List<Integer> tmp = new ArrayList<>(set);
				Collections.sort(tmp);
				
				sIdx = tmp.get(0);
				tIdx = tmp.get(1);
			}
		}
		
//		System.out.println(maxM + " " + sIdx + " " + tIdx);
		
		//출력
		System.out.println(list.get(sIdx));
		System.out.println(list.get(tIdx));
	}
	
	public static int getM(String str1, String str2) {
		int minLen = Math.min(str1.length(), str2.length());
		
		int count = 0;
		for(int i = 0; i < minLen; i++) {
			if(str1.charAt(i) != str2.charAt(i)) break;
			
			count++;
		}
				
		return count;
	}

}