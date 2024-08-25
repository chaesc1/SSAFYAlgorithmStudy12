package BOJ.Day0825.BOJ알파벳;

import java.io.*;
import java.util.*;

public class BOJ1987 {
    static int R, C, max = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] board;
    static boolean[][] visited;
    static Set<Character> checked = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) board[i] = br.readLine().toCharArray();

        visited[0][0] = true;
        checked.add(board[0][0]);
        dfs(0, 0, 1);
        System.out.println(max);
    }

    public static void dfs(int r, int c, int depth) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if(!checked.contains(board[nr][nc])) {
                checked.add(board[nr][nc]);
                visited[nr][nc] = true;
                dfs(nr, nc, depth + 1);
                visited[nr][nc] = false;
                checked.remove(board[nr][nc]);
            }
        }
        max = Math.max(max, depth);
    }
}

