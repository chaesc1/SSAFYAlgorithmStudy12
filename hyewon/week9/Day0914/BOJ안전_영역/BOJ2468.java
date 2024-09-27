package BOJ.Day0916.BOJ안전_영역;

import java.io.*;
import java.util.*;

public class BOJ2468 {
    static int N, cnt = 0;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> answer = new ArrayList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > max) max = map[i][j];
            }
        }

        for (int t = 0; t < max; t++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= t) visited[i][j] = true;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > t && !visited[i][j]) {
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            answer.add(cnt);
            cnt = 0;
            for (int i = 0; i < N; i++) Arrays.fill(visited[i], false);
        }

        Integer[] num = answer.toArray(new Integer[answer.size()]);
        Arrays.sort(num);
        System.out.println(num[num.length - 1]);
    }
    public static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
            dfs(nr, nc);
        }
    }
}
