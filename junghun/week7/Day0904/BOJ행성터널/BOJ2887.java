package week7.Day0904.BOJ행성터널;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2887 {
    static class Planet {
        int id;
        int x;
        int y;
        int z;

        public Planet(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Node implements Comparable<Node> {
        int u, v;
        int cost;

        public Node(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static ArrayList<Planet> planetList;
    static ArrayList<Node> nodeList;
    static int[] parent;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        planetList = new ArrayList<>();
        nodeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            planetList.add(new Planet(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        //  두 행성 A(xA, yA, zA)와 B(xB, yB, zB)를 터널로 연결할 때 드는 비용은 min(|xA-xB|, |yA-yB|, |zA-zB|)
        planetList.sort((a, b) -> a.x - b.x);
        for (int i = 1; i < N; i++) {
            int cost = Math.abs(planetList.get(i).x - planetList.get(i - 1).x);
            nodeList.add(new Node(planetList.get(i - 1).id, planetList.get(i).id, cost));
        }

        planetList.sort((a, b) -> a.y - b.y);
        for (int i = 1; i < N; i++) {
            int cost = Math.abs(planetList.get(i).y - planetList.get(i - 1).y);
            nodeList.add(new Node(planetList.get(i - 1).id, planetList.get(i).id, cost));
        }

        planetList.sort((a, b) -> a.z - b.z);
        for (int i = 1; i < N; i++) {
            int cost = Math.abs(planetList.get(i).z - planetList.get(i - 1).z);
            nodeList.add(new Node(planetList.get(i - 1).id, planetList.get(i).id, cost));
        }

        // 크루스칼 알고리즘
        Collections.sort(nodeList);
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        // 최소비용 조합
        int result = 0;
        int totalEdges = 0;

        for (Node node : nodeList) {
            if (find(node.u) != find(node.v)) {
                union(node.u, node.v);
                result += node.cost;
                totalEdges++;
                if (totalEdges == N - 1) {
                    break;
                }
            }
        }

        System.out.println(result);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int rootA = find(x);
        int rootB = find(y);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}
