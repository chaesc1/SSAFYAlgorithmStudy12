package week4.Day0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA14510 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= testCase; tc++) {
            int N = Integer.parseInt(br.readLine());
            //홀수 번째 날은 키가 1 자라고 짝수 번째 날은 키가 2 자란다
            int[] tree = new int[N];
            int max = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, tree[i]);
            }

            int even = 0;
            int odd = 0;
            for (int i = 0; i < N; i++) {
                int diff = max - tree[i];
                even += diff / 2;
                odd += diff % 2;
            }
            if (even > odd) {
                while (Math.abs(even - odd) > 1) {
                    even--;
                    odd += 2;
                }
            }
            int result = 0;
            if (even > odd) {
                result = even * 2;
            } else if (even < odd) {
                result = odd * 2 - 1;
            } else {
                result = even + odd;
            }
            System.out.println("#" + tc + " " + result);
        }
    }
}
