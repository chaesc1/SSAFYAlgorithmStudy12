package Day0722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2660 {
    static int N; // 회원 수
    static int[][] arr;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    arr[i][j] = INF;
                }
            }
        }

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (x == -1 && y == -1) break;
            arr[x][y] = arr[y][x] = 1;
        }
        // floyd warshall
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        int[] scoreArr = new int[N + 1];
        int leader = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int score = 0;
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] != INF) {
                    score = Math.max(score, arr[i][j]);
                }
            }
            scoreArr[i] = score;
            leader = Math.min(leader, scoreArr[i]);
        }
        StringBuilder title = new StringBuilder();
        title.append(leader + " ");

        StringBuilder body = new StringBuilder();
        int leaderNum = 0;
        for (int i = 1; i <= N; i++) {
            if (leader == scoreArr[i]) {
                leaderNum++;
                body.append(i).append(" ");
            }
        }
        title.append(leaderNum + " ");

        System.out.println(title);
        System.out.println(body);
    }
}
