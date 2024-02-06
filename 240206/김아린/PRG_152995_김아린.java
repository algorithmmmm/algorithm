import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        List<int[]> list = new ArrayList<>(); //원호보다 점수 합이 높은 사원 리스트(인덱스, 합)
        
        int[] wanho = scores[0]; //원호의 점수
        list.add(new int[] {0, wanho[0] + wanho[1]});
        
        for(int i = 1; i < scores.length; i++) {
            if(wanho[0] + wanho[1] < scores[i][0] + scores[i][1]) { //원호보다 점수 합이 높으면 리스트에 추가
                list.add(new int[] {i, scores[i][0] + scores[i][1]});
            }
        }
        
        list.sort((o1, o2) -> o2[1] - o1[1]); //점수 합 내림차순 정렬
        
        int answer = 1; //등수
        
        for(int i = 0; i < list.size(); i++) {
            int[] arr1 = list.get(i);
            int idx1 = arr1[0]; //인덱스
            
            if(idx1 == 0) break; //원호이면 중단
            
            if(scores[idx1][0] > wanho[0] && scores[idx1][1] > wanho[1]) { //원호보다 둘다 점수 높은 동료가 있음
                answer = -1; //원호는 인센티브 못받음
                break;
            }
            
            boolean deserved = true; //동료가 인센받을 수 있는지
            for(int j = 0; j < i; j++) { //앞에 있는 점수 높은 모든 동료 확인
                int[] arr2 = list.get(j);
                int idx2 = arr2[0];
                
                if(scores[idx2][0] > scores[idx1][0] && scores[idx2][1] > scores[idx1][1]) { //둘다 점수 높은 동료가 있음
                    deserved = false; //인센 못 받음
                    break;
                }
            }
            
            if(deserved) answer++; //원호 앞에 인센 받을 수 있는 사람있으므로 등수 밀려남
        }
        
        return answer;
    }
}