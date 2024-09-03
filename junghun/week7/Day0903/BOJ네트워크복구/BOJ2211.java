package week7.Day0903.BOJ네트워크복구;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2211 {
    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static ArrayList<Node>[] adjList;
    static int N, M;
    static int[] dist;
    static int[] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            adjList[start].add(new Node(end, time));
            adjList[end].add(new Node(start, time));
        }

        // 1번 컴퓨터는 보안 시스템을 설치할 슈퍼컴퓨터이다. 모든 통신은 완전쌍방향 방식
        dijkstra(1);
        //첫째 줄에 복구할 회선의 개수 K를 출력한다. 다음 K개의 줄에는 복구한 회선을 나타내는 두 정수 A,
        System.out.println(N - 1);
        for (int i = 2; i <= N; i++) {
            System.out.println(i + " " + path[i]);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N + 1];
        path = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int currentVertex = now.vertex;

            for (Node neighbor : adjList[currentVertex]) {
                if (dist[neighbor.vertex] > dist[currentVertex] + neighbor.cost) {
                    dist[neighbor.vertex] = dist[currentVertex] + neighbor.cost;
                    path[neighbor.vertex] = currentVertex;
                    pq.offer(new Node(neighbor.vertex, dist[neighbor.vertex]));
                }
            }
        }
    }
}
