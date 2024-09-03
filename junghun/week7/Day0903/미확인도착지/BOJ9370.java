package week7.Day0903.미확인도착지;

import java.io.BufferedReader;
import java.util.*;

public class BOJ9370 {
    static class Node implements Comparable<Node> {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; // 오름차순으로
        }
    }

    static ArrayList<Node>[] adjList;
    static int n, m, t;
    static int s, g, h;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            //교차로, 도로, 목적지 후보의 개수
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[n + 1];
            dist = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            //s는 예술가들의 출발지이고, g, h는 문제 설명
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            // start - g - h 는 고정 경로고
            // 목적지 후보 t 중에서 어디가 최단 경로로 갈 수 있는지 출력 불가능하면 출력안하고
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                adjList[start].add(new Node(end, cost));
                adjList[end].add(new Node(start, cost));
            }

            // 목적지 후보
            //도로는 목적지 후보들 중 적어도 1개로 향하는 최단 경로의 일부
            ArrayList<Integer> destinations = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                destinations.add(Integer.parseInt(br.readLine()));
            }

            int[] distFromS = dijkstra(s);
            int[] distFromG = dijkstra(g);
            int[] distFromH = dijkstra(h);

            ArrayList<Integer> result = new ArrayList<>();
            for (int destination : destinations) {
                if (distFromS[destination] == INF) continue;
                // s -> g -> h -> dest
                int distSGH = distFromS[g] + distFromG[h] + distFromH[destination];
                // s -> h -> g -> dest
                int distSHG = distFromS[h] + distFromH[g] + distFromG[destination];
                // s -> dest
                int distS = distFromS[destination];

                if (distSGH == distS || distSHG == distS) {
                    result.add(destination);
                }
            }

            Collections.sort(result);
            for (int i : result) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int curVertex = now.end;

            for (Node next : adjList[curVertex]) {
                if (dist[next.end] > dist[curVertex] + next.cost) {
                    dist[next.end] = dist[curVertex] + next.cost;
                    pq.offer(new Node(next.end, dist[next.end]));
                }

            }
        }
        return dist;
    }
}
