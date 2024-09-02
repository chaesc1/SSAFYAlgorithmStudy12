package SWEA.Day0829.SWEA디저트_카페;

import java.io.*;
import java.util.*;

public class SWEA2105 {
    static int N, answer;
    static int[][] cafe;
    static boolean[][] visited;
    static boolean[] dessertVisited;

    // 대각선 방향 (↘, ↙, ↖, ↗)
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cafe = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    cafe[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = -1;
            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    visited = new boolean[N][N];
                    dessertVisited = new boolean[101];
                    dfs(i, j, i, j, 0, 1);
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    public static void dfs(int r, int c, int startR, int startC, int dir, int count) {
        visited[r][c] = true;
        dessertVisited[cafe[r][c]] = true;

        for (int i = dir; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr == startR && nc == startC && count >= 4) {
                answer = Math.max(answer, count);
                return;
            }

            if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc] || dessertVisited[cafe[nr][nc]]) {
                visited[r][c] = false;
                continue;
            }
            dfs(nr, nc, startR, startC, i, count + 1);
            visited[nr][nc] = false;
            dessertVisited[cafe[nr][nc]] = false;
        }
    }
}
