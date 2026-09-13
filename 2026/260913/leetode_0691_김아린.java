import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>(); // 문자, 횟수

    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] stickerCounts = new int[n][26]; // 스티커 별 알파벳 카운트

        for(int i = 0; i < n; i++) {
            for(char c : stickers[i].toCharArray()) {
                stickerCounts[i][c - 'a']++;
            }
        }

        // 정렬
        char[] arr = target.toCharArray();
        Arrays.sort(arr);

        int result = dfs(new String(arr), stickerCounts); // 남은 문자열, 스티커 별 알파벳 카운트
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int dfs(String remain, int[][] stickerCounts) {
        if(remain.isEmpty()) return 0;

        if(map.containsKey(remain)) {
            return map.get(remain);
        }
        
        // 남은 글자에서 카운트
        int result = Integer.MAX_VALUE;
        int[] targetCount = new int[26];
        for (char c : remain.toCharArray()) {
            targetCount[c - 'a']++;
        }
        
        // 스티커에서 탐색
        for (int[] sticker : stickerCounts) {
            // 이 스티커가 remain의 첫 글자를 포함 안 하면 통과
            if(sticker[remain.charAt(0) - 'a'] == 0) continue;
            
            StringBuilder sb = new StringBuilder(); // 남게 될 글자
            for(int i = 0; i < 26; i++) {
                int need = targetCount[i] - sticker[i];
                if(need > 0) {
                    for(int j = 0; j < need; j++) {
                        sb.append((char)('a' + i));
                    }
                }
            }
            
            String newRemain = sb.toString();
            if(newRemain.length() < remain.length()) { // 남게 될 글자가 더 적음
                int sub = dfs(newRemain, stickerCounts); // 탐색
                if(sub != Integer.MAX_VALUE) {
                    result = Math.min(result, sub + 1);
                }
            }
        }
        
        map.put(remain, result);
        return result;
    }
}