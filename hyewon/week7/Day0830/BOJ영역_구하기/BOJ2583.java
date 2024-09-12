package BOJ.Day0830.BOJ영역_구하기;

import java.io.*;
import java.util.*;

public class BOJ2583 {
    static int N, M, cnt;
    static boolean[][] paper;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        paper = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int lc = Integer.parseInt(st.nextToken());
            int lr = N - Integer.parseInt(st.nextToken()) - 1;
            int rc = Integer.parseInt(st.nextToken()) - 1;
            int rr = N - Integer.parseInt(st.nextToken());
            for (int r = rr; r <= lr; r++) {
                for (int c = lc; c <= rc; c++) {
                    paper[r][c] = true;
                }
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt = 1;
                if (!paper[i][j]) answer.add(dfs(i, j));
            }
        }
        Collections.sort(answer);
        System.out.println(answer.size());
        for (int i : answer) System.out.print(i + " ");
    }

    public static int dfs(int r, int c) {
        paper[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M || paper[nr][nc]) continue;
            paper[nr][nc] = true;
            cnt = dfs(nr, nc) + 1;
        }
        return cnt;
    }
}