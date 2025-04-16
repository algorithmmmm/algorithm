class Solution {
    public String solution(String s) {
        char[] chs = s.toCharArray();
        
        for(int i = 0; i < chs.length; i++) {
            if(i == 0) {
                if(!Character.isDigit(chs[i])) {
                    chs[i] = Character.toUpperCase(chs[i]);
                }
            } else {
                if(chs[i - 1] == ' ' && Character.isLowerCase(chs[i])) {
                    chs[i] = Character.toUpperCase(chs[i]);
                }
            }
        }
                   
        String answer = new String(chs);
        System.out.println(answer);
        return answer;
    }
}