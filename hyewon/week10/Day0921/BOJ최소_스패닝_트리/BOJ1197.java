package BOJ.Day0921.BOJ최소_스패닝_트리;

import java.io.*;
import java.util.*;

public class BOJ1197 {
    static int V, E;
    static int[][] graph;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];
        graph = new int[E][3];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken()); // 가중치
        }

        Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);
        mst_kruskal(graph);

    }
    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
    public static void mst_kruskal(int[][] graph) { // 가중치 기준 정렬되어있다는 가정하에 사용
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
