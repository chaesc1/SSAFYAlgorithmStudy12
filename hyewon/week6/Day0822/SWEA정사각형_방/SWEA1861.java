package BOJ.Day0822.SWEA정사각형_방;

import java.io.*;
import java.util.*;

public class SWEA1861 {
    static int N, max, start = 0, cnt;
    static int[][] room;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            room = new int[N][N];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] result = new int[N * N + 1];
            int resultMax = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    cnt = 1;
                    max = 0;
                    search(i, j);
                    result[room[i][j]] = max;
                    if(resultMax < max) resultMax = max;
                }
            }

            for(int i = 0; i < result.length; i++) {
                if(result[i] == resultMax) {
                    System.out.println("#" + tc + " " + i + " " + resultMax);
                    break;
                }
            }
        }
    }

    public static void search(int x, int y) {
        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(room[nx][ny] == room[x][y] + 1) {
                cnt++;
                search(nx, ny);
            }
            if(max < cnt) {
                max = cnt;
            }
        }
    }
}

