package BOJ.Day0924.BOJ전력난;

import java.io.*;
import java.util.*;

class Edge {
    int x, y, z;
    Edge(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class BOJ6497 {
    static int V, E, max = 0;
    static ArrayList<Edge> graph = new ArrayList<>();
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            if (V == 0 && E == 0) break;
            graph = new ArrayList<>();
            parent = new int[V + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            max = 0;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                graph.add(new Edge(x, y, z));
                max += z;
            }
            Collections.sort(graph, (o1, o2) -> o1.z - o2.z);
            kruskal(graph);
        }
        System.out.println(sb);
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
    public static void kruskal(ArrayList<Edge> graph) {
        int cost = 0;
        for (int i = 0; i < graph.size(); i++) {
            Edge edge = graph.get(i);
            if (find(edge.x) != find(edge.y)) {
                cost += edge.z;
                union(edge.x, edge.y);
            }
        }
        sb.append(max - cost).append("\n");
    }
}
