package BOJ.Day0919.BOJ주사위_굴리기;

import java.io.*;
import java.util.*;

public class BOJ14499 {
    static int N, M, r, c, command;
    static int[][] map;
    static int[] dice = new int[7];
    static int[] dr = {0, 0, -1, 1}; // 동 서 북 남
    static int[] dc = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        command = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < command; i++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }
    }
    public static void move(int d) {
        int nr = r + dr[d - 1];
        int nc = c + dc[d - 1];
        if (nr < 0 || nc < 0 || nr >= N || nc >= M) return;
        change(d, nr, nc);
        System.out.println(dice[1]);
        r = nr;
        c = nc;
    }
    public static void change(int d, int r, int c) {
        int tmp = dice[1];
        switch (d) {
            case 1: // 동
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = tmp;
                break;
            case 2: // 서
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
                break;
            case 3: // 북
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
                break;
            case 4: // 남
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
                break;
        }
        if (map[r][c] == 0) map[r][c] = dice[6];
        else {
            dice[6] = map[r][c];
            map[r][c] = 0;
        }
    }
}
