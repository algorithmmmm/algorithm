package week0513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_19583_백자민 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int count = 0;

        String startT = st.nextToken();
        String endT = st.nextToken();
        String finalT = st.nextToken();

        String input = br.readLine();

        HashSet<String> user = new HashSet<>();
        HashSet<String> attendance = new HashSet<>();

        while(!input.isEmpty() && input!= null) {
            st = new StringTokenizer(input, " ");
            String timeValue = st.nextToken();
            String userName = st.nextToken();

            if(timeValue.compareTo(startT) <=0) {
                user.add(userName);
            }
            else if(timeValue.compareTo(endT) >= 0 && timeValue.compareTo(finalT) <= 0) {
                attendance.add(userName);
            }

            input = br.readLine();
        }

        for(String s : user) {
            if(attendance.contains(s)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
