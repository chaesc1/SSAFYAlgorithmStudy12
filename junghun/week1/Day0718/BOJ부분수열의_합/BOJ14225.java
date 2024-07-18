package Day0718;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14225 {
    static int N;
    static int[] arr;
    static boolean[] isUse;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        isUse = new boolean[sum+1];
        dfs(0, 0);
        for (int i = 1; i < isUse.length; i++) {
            if (!isUse[i]) {
                System.out.println(i);
                break;
            }
        }
    }

    private static void dfs(int sum, int depth) {
        if (depth <= N) {
            isUse[sum] = true;
        }
        if (depth == N) {
            return;
        }
        dfs(sum + arr[depth], depth + 1);
        dfs(sum, depth + 1);
    }
}