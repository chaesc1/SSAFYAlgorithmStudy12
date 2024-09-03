package BOJ.Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ22865 {
    static class Node implements Comparable<Node> {
        int end;
        int cost;


        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M;
    static int A, B, C;
    static ArrayList<Node>[] adjList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[start].add(new Node(end, cost));
            adjList[end].add(new Node(start, cost));
        }

        int[] distA = dijkstra(A);
        int[] distB = dijkstra(B);
        int[] distC = dijkstra(C);
        //[2147483647, 0, 1, 4, 2, 4, 5]
        //[2147483647, 1, 0, 4, 3, 3, 5]
        //[2147483647, 4, 3, 7, 6, 0, 2]

        //가장 먼 곳은 선택할 집에서 거리가 가장 가까운 친구의 집까지의 거리를 기준으로 거리가 가장 먼 곳
        int vertexNum = 0;
        int maxDist = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            int minDist = Math.min(distA[i], Math.min(distB[i], distC[i]));
            // 가장 가까운 친구의 집까지의 거리가 현재 최대 거리보다 크다면 해당 위치를 갱신
            if (minDist > maxDist) {
                maxDist = minDist;
                vertexNum = i;
            }
        }
        System.out.println(vertexNum);
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowEnd = now.end; // 현재 위치
            int nowCost = now.cost; // 현재 위치까지 오는데 걸리는 비용

            if (dist[nowEnd] < nowCost) {
                continue;
            }

            for (Node next : adjList[nowEnd]) {
                int nextCost = nowCost + next.cost;

                if (nextCost < dist[next.end]) {
                    dist[next.end] = nextCost;
                    pq.add(new Node(next.end, nextCost));
                }
            }
        }

        return dist;
    }
}
