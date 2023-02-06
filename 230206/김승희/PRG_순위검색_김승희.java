import java.util.regex.Matcher;
import java.util.regex.Pattern;
class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for(int i = 0; i < query.length; i++){
            String[] cond = query[i].split(" ");
            int cnt = 0;
            for(int j= 0; j < info.length; j++){
                boolean chk = true;
                String[] temp = info[j].split(" ");
                int score = Integer.parseInt(temp[temp.length-1]);
                int standard = Integer.parseInt(cond[cond.length-1]);
                if(score < standard){
                    continue;
                }
                for(int k = 0, p = 0; k < cond.length-1; k++){
                    if(cond[k].equals("and")){
                        continue;
                    }
                    if(cond[k].equals("-") ){
                        p++;
                        continue;
                    }
                    boolean result = Pattern.matches(".*" + cond[k] + ".*", temp[p++]);
                    if(!result){
                        chk = false;
                        break;
                    }
                }
                
                if(chk){
                    cnt++;
                }
            }
            answer[i] = cnt;
        }
        return answer;
    }
}