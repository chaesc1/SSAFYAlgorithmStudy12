package Day0814.SWEA준환이의_양팔저울;

import java.io.BufferedReader;
import java.util.StringTokenizer;

public class SWEA3234 {
    static StringTokenizer st = null;
    static int N,result;
    static int[] weights;
    static int[] sorted;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int i = 1; i <= testcase; i++) {
            N = Integer.parseInt(br.readLine());
            weights = new int[N];
            sorted = new int[N];
            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                weights[j] = Integer.parseInt(st.nextToken());
            }

            result = 0;

            permutation(0);
            sb.append("#").append(i).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void permutation(int depth) {
        if (depth == N) {
            solve(0,0,0);
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sorted[depth] = weights[i];
            permutation(depth + 1);
            visited[i] = false;
        }

    }

    private static void solve(int left, int right, int depth) {
        if (left < right) return;
        if (depth == N) {
            result++;
            return;
        }

        solve(left+sorted[depth], right, depth+1);
        solve(left, right+sorted[depth], depth+1);
    }
}
