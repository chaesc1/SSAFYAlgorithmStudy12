package BOJ.Day0828.BOJ농장_관리;

import java.io.*;
import java.util.*;

public class BOJ1245 {
    static int N, M;
    static int[][] farm;
    static boolean[][] visited;
    static boolean flag = true;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        farm = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                farm[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    flag = true;
                    DFS(i, j);
                    if (flag) answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static void DFS(int r, int c) {
        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

            if (farm[r][c] < farm[nr][nc]) flag = false;

            if (visited[nr][nc] || farm[r][c] != farm[nr][nc]) continue;
            visited[nr][nc] = true;
            DFS(nr, nc);
        }
    }
}