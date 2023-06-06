import java.util.*;

class Solution {
    public String[] solution(String[] files) {
   	
    	Arrays.sort(files, new Comparator<String>() {
			@Override
			public int compare(String f1, String f2) {
				String head1 = f1.split("[0-9]")[0].toLowerCase(); //해드 추출
				String head2 = f2.split("[0-9]")[0].toLowerCase();
				
				String numTail1 = f1.substring(head1.length()); //숫자+테일 추출
				String numTail2 = f2.substring(head2.length());
				
				if(head1.compareTo(head2) == 0) { //해드가 같으면
					String num1 = "";
					String num2 = "";
					
					for (int i = 0; i < numTail1.length(); i++) { //숫자 추출
					    char ch = numTail1.charAt(i);
					    if (48 <= ch && ch <= 57) {
					    	num1 += ch;
					    } else {
					    	break;
					    }
					}
					
					for (int i = 0; i < numTail2.length(); i++) { 
					    char ch = numTail2.charAt(i);
					    if (48 <= ch && ch <= 57) {
					    	num2 += ch;
					    } else {
					    	break;
					    }
					}
					
					return (Integer.parseInt(num1) - Integer.parseInt(num2)); //숫자로 정렬
				} else {
					return head1.compareTo(head2); //해드로 정렬하기
				}
			}
    	});
    	
        return files;
    }

}