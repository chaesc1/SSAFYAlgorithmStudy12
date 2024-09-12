package BOJ.Day0904.BOJN과_M_10;

import java.io.*;
import java.util.*;

public class BOJ15664 {
    static int N, M;
    static int[] nums, numbers;
    static boolean[] visited;
    static Set<String> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        visited = new boolean[N];
        numbers = new int[M];

        answer = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        comb(0, 0);
        answer.forEach(System.out::println);
    }
    public static void comb(int idx, int start) {
        if (idx == M) {
            StringBuilder sb = new StringBuilder();
            for (int i : numbers) sb.append(i + " ");
            answer.add(sb.toString());
        }
        else {
            for (int i = start; i < N; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                numbers[idx] = nums[i];
                comb(idx + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
