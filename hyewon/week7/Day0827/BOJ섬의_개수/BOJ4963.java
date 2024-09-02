package BOJ.Day0827.BOJ섬의_개수;
import java.io.*;
import java.util.*;

public class BOJ4963{
    static int w, h, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            answer = 0;

            if(w == 0 && h == 0) break;
            map = new int[h][w];
            visited = new boolean[h][w];

            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        dfs(i, j);
                        answer++;
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
    public static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= h || nc >= w || visited[nr][nc]) continue;
            if (map[nr][nc] == 1) dfs(nr, nc);
        }
    }
}
