package BOJ.Day0911.BOJ로봇_청소기;

import java.io.*;
import java.util.*;

public class BOJ14503 {
    static int N, M;
    static int r, c, d, answer = 0;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken()); // 북, 동, 남, 서
        map = new int[N][M]; // 청소 X : 0 / 청소 O : 1
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(r, c, d);
        System.out.println(answer);
    }
    public static void dfs(int r, int c, int d) {
        int nr, nc;

        if (map[r][c] == 0) {
            map[r][c] = -1;
            answer++;
        }
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            nr = r + dr[d];
            nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
            if (map[nr][nc] == 0) {
                dfs(nr, nc, d);
                return;
            }
        }
        int back = (d + 2) % 4;
        nr = r + dr[back];
        nc = c + dc[back];
        if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1) return;
        dfs(nr, nc, d);
    }
}
