package BOJ.Day0905.BOJ빙산;

import java.io.*;
import java.util.*;

public class BOJ2573 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int years = 0;
        while (true) {
            int icebergCount = countIcebergs();
            if (icebergCount >= 2) {
                System.out.println(years);
                return;
            }

            if (icebergCount == 0) {
                System.out.println(0);
                return;
            }

            meltIce();
            years++;
        }
    }

    // 현재 빙산의 덩어리 개수를 계산
    public static int countIcebergs() {
        visited = new boolean[N][M];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    // BFS를 사용하여 빙산의 덩어리를 탐색
    public static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = x + dr[i];
                int nc = y + dc[i];

                if (nr >= 0 && nc >= 0 && nr < N && nc < M && !visited[nr][nc] && map[nr][nc] > 0) {
                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});
                }
            }
        }
    }

    // 빙산이 녹는 과정을 시뮬레이션
    public static void meltIce() {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0) {
                            cnt++;
                        }
                    }
                    newMap[i][j] = Math.max(map[i][j] - cnt, 0);
                }
            }
        }
        map = newMap;
    }
}
