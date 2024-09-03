package week7.Day0903.BOJ일요일아침의데이트;

import java.io.BufferedReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//목표는 S에서 F까지 가는데, 쓰레기로 차있는 칸을 되도록이면 적게 지나가는 것
public class BOJ1445 {
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int garbage; // 통과해가는 쓰레기
        int nearGarbage; // 지나치는 쓰레기

        public Node(int x, int y, int garbage, int nearGarbage) {
            this.x = x;
            this.y = y;
            this.garbage = garbage;
            this.nearGarbage = nearGarbage;
        }

        // 쓰레기를 지나치는 개수와 인접한 쓰레기의 개수를 기반으로
        @Override
        public int compareTo(Node o) {
            if (this.garbage == o.garbage) {
                return this.nearGarbage - o.nearGarbage;
            }
            return this.garbage - o.garbage;
        }
    }

    static int N, M;
    static int startX, startY, endX, endY;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        // F는 꽃이 있는 위치를 나타내고, g는 쓰레기가 있는 위치
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'F') {
                    endX = i;
                    endY = j;
                }
                if (map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        dijkstra(startX, startY);
    }

    private static void dijkstra(int startX, int startY) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startX, startY, 0, 0));
        visited[startX][startY] = true;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int x = now.x;
            int y = now.y;
            int garbage = now.garbage;
            int nearGarbage = now.nearGarbage;

            if (x == endX && y == endY) {
                System.out.println(garbage + " " + nearGarbage);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nextGarbage = garbage;
                int nextNearGarbage = nearGarbage;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

                if (map[nx][ny] == 'g') {
                    nextGarbage++;
                } else if (map[nx][ny] == '.') {
                    for (int j = 0; j < 4; j++) {
                        int adjX = nx + dx[j];
                        int adjY = ny + dy[j];
                        if (adjX < 0 || adjX >= N || adjY < 0 || adjY >= M) continue;
                        if (map[adjX][adjY] == 'g') {
                            nextNearGarbage++;
                            break;
                        }
                    }
                }
                visited[nx][ny] = true;
                pq.offer(new Node(nx, ny, nextGarbage, nextNearGarbage));
            }
        }

    }
}
