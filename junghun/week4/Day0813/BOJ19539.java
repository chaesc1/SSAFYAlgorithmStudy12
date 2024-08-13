package week4.Day0813;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19539 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int odd= 0;
        int even = 0;
        int sum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            sum += a;
            odd += a % 2;
            even += a / 2;
        }

        String result = "YES";
        if(sum % 3 > 0 || odd > even) {
            result = "N0";
        }

        System.out.println(result);
    }
}
