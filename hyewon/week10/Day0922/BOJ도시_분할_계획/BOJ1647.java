package BOJ.Day0922.BOJ도시_분할_계획;

import java.io.*;
import java.util.*;

public class BOJ1647 {
    static int N, M;
    static int[] parent;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        map = new int[M][3];

        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(map, (o1, o2) -> o1[2] - o2[2]);
        kruskal(map);
    }
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
    public static void kruskal(int[][] map) {
        int cost = 0;
        int maxEdge = 0;
        for (int i = 0; i < map.length; i++) {
            if (find(map[i][0]) != find(map[i][1])) {
                union(map[i][0], map[i][1]);
                cost += map[i][2];
                maxEdge = map[i][2];
            }
        }
        System.out.println(cost - maxEdge);
    }
}
