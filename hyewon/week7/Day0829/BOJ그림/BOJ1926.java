package BOJ.Day0829.BOJ그림;

import java.io.*;
import java.util.*;

public class BOJ1926 {
    static int n, m, max;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        paper = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (paper[i][j] == 1 && !visited[i][j]) {
                    max = 1;
                    dfs(i, j);
                    l.add(max);
                    cnt++;
                }
            }
        }
        if (cnt == 0) System.out.println(cnt + "\n" + 0);
        else System.out.println(cnt + "\n" + Collections.max(l));
    }

    public static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc]) continue;
            if (paper[nr][nc] == 1) {
                visited[nr][nc] = true;
                dfs(nr, nc);
                max++;
            }
        }
    }
}
