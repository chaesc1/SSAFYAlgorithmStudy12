package BOJ.Day0903.BOJN과_M_7;

import java.io.*;
import java.util.*;

public class BOJ15656 {
    static int N, M;
    static int[] nums, numbers;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        numbers = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        perm(0);
        System.out.println(sb);
    }
    public static void perm(int idx) {
        if (idx == M) {
            for (int i : numbers) sb.append(i + " ");
            sb.append("\n");
        }
        else {
            for (int i = 0; i < N; i++) {
                numbers[idx] = nums[i];
                perm(idx + 1);
            }
        }
    }
}
