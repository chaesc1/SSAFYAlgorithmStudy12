package BOJ.Day0921.BOJ네트워크_연결;

import java.io.*;
import java.util.*;

public class BOJ1922 {
    static int N, M;
    static int[] parent;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        graph = new int[M][3];

        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);
        kruskal(graph);
    }
    public static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
    public static void kruskal(int[][] graph) {
        int cost = 0;
        for (int i = 0; i < graph.length; i++) {
            if (find(graph[i][0]) != find(graph[i][1])) {
                cost += graph[i][2];
                union(graph[i][0], graph[i][1]);
            }
        }
        System.out.println(cost);
    }
}
