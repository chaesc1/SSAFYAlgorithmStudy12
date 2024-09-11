package BOJ.Day0906.BOJ연결_요소의_개수;

import java.io.*;
import java.util.*;

public class BOJ11724 {
    static int N, M, answer = 0;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            dfs(i);
            answer++;
        }
        System.out.println(answer);
    }
    public static void dfs(int start) {
        visited[start] = true;
        for (int i : graph[start]) {
            if (visited[i]) continue;
            dfs(i);
        }
    }
}
