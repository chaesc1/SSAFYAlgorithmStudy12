package BOJ.Day0905.BOJ양치기_꿍;

import java.io.*;
import java.util.*;

public class BOJ3187 {
    static int R, C, sheepCnt, wolfCnt, sheepAnswer = 0, wolfAnswer = 0;
    static String[][] field;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        field = new String[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) field[i] = br.readLine().split("");

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (field[i][j].equals("#") || visited[i][j]) continue;
                sheepCnt = 0; wolfCnt = 0;
                dfs(i, j);
                if (sheepCnt > wolfCnt) sheepAnswer += sheepCnt;
                else wolfAnswer += wolfCnt;
            }
        }
        System.out.println(sheepAnswer + " " + wolfAnswer);
    }
    public static void dfs(int r, int c) {
        visited[r][c] = true;
        if (field[r][c].equals("v")) wolfCnt++;
        else if (field[r][c].equals("k")) sheepCnt++;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc] || field[nr][nc].equals("#")) continue;
            visited[nr][nc] = true;
            dfs(nr, nc);
        }
    }
}
