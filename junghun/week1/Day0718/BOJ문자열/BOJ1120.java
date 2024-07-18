package Day0718;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1120 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();

        int result = A.length();
        for (int i = 0; i <= B.length() - A.length(); i++) {
            int cnt = 0;
            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j) != B.charAt(i+j)) {
                    cnt++;
                }
            }
            result = Math.min(result,cnt);
        }

        System.out.println(result);
    }
}
