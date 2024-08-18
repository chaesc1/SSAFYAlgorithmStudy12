package Day0816.SWEA등산로조성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1949 {
    static int N, K,answer;
    static int[][] board;
    static boolean[][] visited;
    static int maxHeight;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testcase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testcase; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            visited = new boolean[N][N];

            maxHeight = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, board[i][j]);
                }
            }
            answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == maxHeight) {
                        // i,j 에서부터 깊이우선 탐색으로 가장 긴 거리 구하면 되는 문제
                        visited[i][j] = true;
                        dfs(i, j, maxHeight, 1, 0);
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    //긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다.
    private static void dfs(int x, int y, int cur, int len, int count) {
        answer = Math.max(answer, len);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visited[nx][ny]) continue;

            if (board[nx][ny] < cur) {
                visited[nx][ny] = true;
                dfs(nx, ny, board[nx][ny], len + 1, count);
                visited[nx][ny] = false;
            } else {
                // 한번도 깎지 않았으면
                if (count == 0) {
                    if (board[nx][ny] - K < cur) {
                        visited[nx][ny] = true;
                        dfs(nx, ny, cur - 1, len + 1, count + 1);
                        visited[nx][ny] = false;
                    }
                }
            }
        }

    }
}
