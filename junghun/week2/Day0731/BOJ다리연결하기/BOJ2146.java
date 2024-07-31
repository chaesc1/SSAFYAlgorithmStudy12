package Day0731.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2146 {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬 아이디 부여하고  dfs
        int islandNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    dfs(i, j, islandNum);
                    islandNum++;
                }
            }
        }
        // 연결 bfs
        for (int i = 1; i <= islandNum; i++) {
            int[][] board = map.clone();
            bfs(board, i);
        }

        System.out.println(answer);
    }

    private static void bfs(int[][] board, int islandNum) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] == islandNum) {
                    q.offer(new int[]{i,j,0});
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isValid(nx,ny)) continue;

                // 다른 섬이면
                if (board[nx][ny] != islandNum && map[nx][ny] != 0) {
                    if (answer > cnt && cnt != 0) {
                        answer = cnt;
                    }
                } else {
                    if (!visited[nx][ny] && board[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx,ny,cnt+1});
                    }
                }
            }
        }
    }

    private static void dfs(int x, int y, int islandNum) {
        visited[x][y] = true;
        map[x][y] = islandNum;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isValid(nx, ny)) continue;
            if (map[nx][ny] != 0 && !visited[nx][ny]) dfs(nx, ny, islandNum);
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
