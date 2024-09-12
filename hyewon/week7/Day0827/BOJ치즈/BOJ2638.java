package BOJ.Day0827.BOJ치즈;

import java.io.*;
import java.util.*;

public class BOJ2638 {
    static int N, M;
    static int[][] paper;
    static boolean[][] checked; // 치즈 내부 공간 체크
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = true;
        int cnt = 0;
        while (flag) {
            checked = new boolean[N][M];
            dfs(0, 0);

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(paper[i][j] == 1) counting(i, j);
                }
            }

            int chesse = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(paper[i][j] > 1) {
                        paper[i][j] = 0;
                        chesse++;
                    }
                }
            }
            if(chesse == 0) {
                flag = false;
                break;
            }
            cnt++;
        }
        System.out.println(cnt);
    }

    public static void counting(int r, int c) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
            if(paper[nr][nc] == 0 && checked[nr][nc]) cnt++;
        }
        if(cnt >= 2) paper[r][c] = cnt;
    }
    // 치즈 외부 공간 파악
    public static void dfs(int r, int c) {
        checked[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nc < 0 || nr >= N || nc >= M || checked[nr][nc]) continue;
            if(paper[nr][nc] == 0) {
                dfs(nr, nc);
                checked[nr][nc] = true;
            }
        }
    }
}
