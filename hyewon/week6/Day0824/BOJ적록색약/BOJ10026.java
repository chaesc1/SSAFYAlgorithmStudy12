package BOJ.DAY0824.BOJ적록색약;

import java.io.*;
import java.util.*;

public class BOJ10026 {
    static int N, cnt;
    static String[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        grid = new String[N][N];
        visited = new boolean[N][N];
        List<Integer> answer = new ArrayList<>();

        for(int i = 0; i < N; i++) grid[i] = br.readLine().split("");

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt = 1;
                if(!visited[i][j]) {
                    notRGBlind(i, j, grid[i][j]);
                    answer.add(cnt);
                }
            }
        }
        sb.append(answer.size()).append(" ");

        answer.clear();
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt = 1;
                if(!visited[i][j]) {
                    RGBlind(i, j, grid[i][j]);
                    answer.add(cnt);
                }
            }
        }
        sb.append(answer.size());
        System.out.println(sb);
    }

    public static void notRGBlind(int x, int y, String color) {
        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
            if(grid[nx][ny].equals(color)) {
                cnt++;
                visited[nx][ny] = true;
                notRGBlind(nx, ny, color);
            }
        }
    }
    public static void RGBlind(int x, int y, String color) {
        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
            if(color.equals("R") || color.equals("G")) {
                if(grid[nx][ny].equals("R") || grid[nx][ny].equals("G")) {
                    cnt++;
                    visited[nx][ny] = true;
                    RGBlind(nx, ny, color);
                }
            }
            else {
                if(grid[nx][ny].equals(color)) {
                    cnt++;
                    visited[nx][ny] = true;
                    RGBlind(nx, ny, color);
                }
            }
        }
    }
}
