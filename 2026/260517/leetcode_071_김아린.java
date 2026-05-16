class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        for(String str : path.split("/")) {
            if(str.equals("..")) { // 상위 디렉토리
                if(!stack.isEmpty()) {
                    stack.pop(); // 상위로 이동
                }
            } else if(str.equals(".") || str.isEmpty()) { // 현재 디렉도리, 중복 슬래쉬로 인한 빈 문자열
                continue;
            } else {
                stack.push(str);
            }
        }

        // 경로 재조합
        StringBuilder sb = new StringBuilder();
        for(String str : stack) {
            sb.append("/" + str);
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }
}