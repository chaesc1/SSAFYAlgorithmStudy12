package BOJ.Day0902.BOJN과_M_5;

import java.io.*;
import java.util.*;

public class BOJ15654 {
    static int N, M;
    static int[] nums;
    static int[] number;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        visited = new boolean[N];
        number = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);
        comb(0);
        System.out.println(sb);

    }
    public static void comb(int idx) {
        if (idx == M) {
            for (int i : number) sb.append(i + " ");
            sb.append("\n");
        }
        else {
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    number[idx] = nums[i];
                    visited[i] = true;
                    comb(idx + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
