package BOJ.Day0904.BOJN과_M_11;

import java.io.*;
import java.util.*;

public class BOJ15665 {
    static int N, M;
    static int[] numbers, nums;
    static Set<String> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        numbers = new int[M];
        answer = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        perm(0);
        answer.forEach(System.out::println);
    }
    public static void perm(int idx) {
        if (idx == M) {
            StringBuilder sb = new StringBuilder();
            for (int i : numbers) sb.append(i + " ");
            answer.add(sb.toString());
        }
        else {
            for (int i = 0; i < N; i++) {
                numbers[idx] = nums[i];
                perm(idx + 1);
            }
        }
    }
}
