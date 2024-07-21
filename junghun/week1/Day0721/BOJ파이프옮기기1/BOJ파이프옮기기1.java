package BOJ.DFSBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17070 {
    static int N;
    static int[][] board;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        answer = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 2, 0);
        System.out.println(answer);
    }

    //0 -> 가로 , 1 -> 세로, 2 -> 대각선??
    private static void dfs(int x, int y, int dir) {
        if (x == N && y == N) {
            answer++;
            return;
        }

        switch (dir) {
            case 0: // 가로일 경우 -> 갈 수 있는 곳은 동쪽 . 대각선
                if (y + 1 <= N && board[x][y + 1] == 0) {
                    dfs(x, y + 1, 0);
                }
                break;
            case 1: // 세로일 경우 -> 남쪽 , 대각선 가능
                if (x + 1 <= N && board[x + 1][y] == 0) {
                    dfs(x + 1, y, 1);
                }
                break;
            case 2: // 대각선인 경우 동쪽, 남쪽, 대각선 가능
                if (y + 1 <= N && board[x][y + 1] == 0) {
                    dfs(x, y + 1, 0);
                }

                if (x + 1 <= N && board[x + 1][y] == 0) {
                    dfs(x + 1, y, 1);
                }
                break;
        }

        if (y + 1 <= N && x + 1 <= N && board[x][y + 1] == 0 && board[x + 1][y] == 0 && board[x + 1][y + 1] == 0) {
            dfs(x + 1, y + 1, 2);
        }
    }
}
