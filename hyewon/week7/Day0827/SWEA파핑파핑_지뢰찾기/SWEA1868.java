package SWEA.Day0827.SWEA파핑파핑_지뢰찾기;

import java.io.*;

public class SWEA1868 {
    static int N, answer;
    static String[][] board;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            board = new String[N][N];
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++) board[i] = br.readLine().split("");

            // 지뢰 체크
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(board[i][j].equals("*")) visited[i][j] = true;
                    else if(board[i][j].equals(".")) check(i, j);
                }
            }

            // 0부터 시작하는 구역 체크 (최소 클릭 구하기 위해)
            for (int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(board[i][j].equals("0") && !visited[i][j]) {
                        dfs(i, j);
                        answer++;
                    }
                }
            }

            // 연쇄 클릭이 안되는 구역 체크
            for (int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) answer++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
    // 지뢰 체크
    public static void check(int r, int c) {
        int cnt = 0;
        for(int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if(board[nr][nc].equals("*")) cnt++;
        }
        board[r][c] = Integer.toString(cnt);
    }

    public static void dfs(int r, int c) {
        visited[r][c] = true;
        for(int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            if(board[nr][nc].equals("0")) dfs(nr, nc);
        }
    }
}
