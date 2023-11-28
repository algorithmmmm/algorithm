import java.util.*;

class Solution {
    static String[] userId;
    static String[] bannedId;
    static HashSet<HashSet<String>> result; //제재 아이디 결과
    
    public int solution(String[] user_id, String[] banned_id) {
        userId = user_id;
        bannedId = banned_id;
        result = new HashSet<>();
        
        permutation(new HashSet<>(), 0);
        
        return result.size();
    }
    
    public void permutation(HashSet<String> set, int depth) {
        if(depth == bannedId.length) {
            result.add(set); //결과에 담음(중복x)
            return;
        }
        
        for(int i = 0; i < userId.length; i++) {
            if (set.contains(userId[i])) { //이미 방문함
                continue;
            }
            
            if(isBanned(userId[i], bannedId[depth])) { //제재 아이디인지 확인
                set.add(userId[i]);
                permutation(new HashSet<>(set), depth + 1); //복사해서 탐색
                set.remove(userId[i]);
            }
        }
    }
    
    public boolean isBanned(String uId, String bId) {
        if (uId.length() != bId.length()) { //길이 다름
            return false;
        }

        for (int i = 0; i < uId.length(); i++) {
            if (uId.charAt(i) != bId.charAt(i) && bId.charAt(i) != '*') { //일치하지 않음
                return false;
            }
        }

        return true;
    }
}