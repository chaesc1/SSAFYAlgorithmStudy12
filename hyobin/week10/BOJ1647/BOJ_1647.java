//프림알고리즘

import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[] visited;
    static ArrayList<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[a].add(new Edge(b, c));
            adjList[b].add(new Edge(a, c));  // 무방향 그래프
        }

        visited = new boolean[n + 1];
        System.out.println(prim());
    }

    static int prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));  // 임의의 시작점(1번 노드)

        int totalCost = 0;
        int maxEdge = 0;  // 마을을 두 개로 나누기 위해 가장 큰 간선을 기록
        int edgesUsed = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.to]) continue;  // 이미 방문한 노드는 무시
            visited[current.to] = true;
            totalCost += current.cost;
            maxEdge = Math.max(maxEdge, current.cost);  // 가장 큰 비용 간선을 저장
            edgesUsed++;

            if (edgesUsed == n) break;  // 모든 노드를 연결했으면 종료

            for (Edge next : adjList[current.to]) {
                if (!visited[next.to]) {
                    pq.add(next);  // 방문하지 않은 노드와 연결된 간선만 추가
                }
            }
        }

        // 두 마을로 나누기 위해 가장 큰 간선을 빼줌
        return totalCost - maxEdge;
    }

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
