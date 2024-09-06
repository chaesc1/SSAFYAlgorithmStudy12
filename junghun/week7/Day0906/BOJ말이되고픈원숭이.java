package week7.Day0906;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ말이되고픈원숭이 {
    // 원숭이는 머나먼 여행길을 떠난다.
    // 격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다.
    // 인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 말처럼 움직이는 경우
    static int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int count;

    static class Point {
        int x;
        int y;
        int k;
        int depth;

        public Point(int x, int y, int k, int depth) {
            this.x = x;
            this.y = y;
            this.k = k; // 말 이동으로 얼마 움직였는지
            this.depth = depth; // 현재까지의 이동 횟수
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());

    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, K, 0));
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();
            if(current.x == H-1 && current.y == W-1) {
                return current.depth;
            }

            for(int i=0; i<4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < H && ny < W && !visited[nx][ny][current.k] && map[nx][ny] == 0) {
                    visited[nx][ny][current.k] = true;
                    q.offer(new Point(nx, ny, current.k, current.depth + 1));
                }
            }
            // 말 이동을 움직인경우
            if (current.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = current.x + hx[i];
                    int ny = current.y + hy[i];

                    if (nx >= 0 && ny >= 0 && nx < H && ny < W && !visited[nx][ny][current.k - 1] && map[nx][ny] == 0) {
                        visited[nx][ny][current.k - 1] = true;
                        q.offer(new Point(nx, ny, current.k - 1, current.depth + 1));
                    }
                }
            }
        }

        return -1;
    }
}
