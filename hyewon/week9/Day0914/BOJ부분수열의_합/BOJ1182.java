package BOJ.Day0916.BOJ부분수열의_합;

import java.io.*;
import java.util.*;

public class BOJ1182 {
    static int N, S, answer = 0;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        if (S == 0) System.out.println(answer - 1);
        else System.out.println(answer);
    }
    public static void dfs(int depth, int sum) {
        if (depth == N) {
            if (sum == S) answer++;
            return;
        }
        dfs(depth + 1, sum + nums[depth]);
        dfs(depth + 1, sum);
    }
}
