package BOJ.Day0903.BOJN과_M_8;

import java.io.*;
import java.util.*;

public class BOJ15657 {
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

        comb(0);
        System.out.println(sb);
    }
    public static void comb(int idx) {
        if (idx == M) {
            for (int i : numbers) sb.append(i + " ");
            sb.append("\n");
            return;
        }
        else {
            for (int i = 0; i < N; i++) {
                if (idx == 0 || numbers[idx - 1] <= nums[i]) {
                    numbers[idx] = nums[i];
                    comb(idx + 1);
                }
            }
        }
    }
}
