package BOJ.DFSBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2606_Re {
    static int N, M,count;
    static int[][] adj; // 인접리스트로 표현
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        adj = new int[M + 1][M + 1];
        visited = new boolean[M + 1];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adj[start][end] = adj[end][start] = 1;
        }

        int answer = dfs(1);
        System.out.println(answer-1); // 1번 노드를 제외하고 전염된 노드를 출력 그래서 -1.
    }

    private static int dfs(int start) {
        visited[start] = true;
        count = 1;
        for (int i = 1; i <= M; i++) {
            if (adj[start][i] == 1 && !visited[i]) {
                count += dfs(i);
            }
        }

        return count;
    }
}
