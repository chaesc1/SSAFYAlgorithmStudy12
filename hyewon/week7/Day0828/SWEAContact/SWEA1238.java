package SWEA.Day0828.SWEAContact;

import java.io.*;
import java.util.*;

public class SWEA1238 {
    static int N;
    static int[][] graph;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            graph = new int[101][101];
            visited = new int[101];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from][to] = 1;
            }

            sb.append("#").append(tc).append(" ").append(bfs(V)).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);

        int depth = 1;
        visited[v] = depth;

        while (!queue.isEmpty()) {
            v = queue.poll();

            for (int i = 0; i <= 100; i++) {
                if (graph[v][i] == 1 && visited[i] == 0) {
                    queue.offer(i);
                    visited[i] = visited[v] + 1;
                }
            }
            depth = Math.max(depth, visited[v]);
        }

        for (int i = 100; i >= 0; i--) {
            if (visited[i] == depth) {
                return i;
            }
        }

        return -1;
    }
}