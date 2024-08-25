package BOJ.DAY0824.BOJ단지번호붙이기;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class BOJ2667 {
    static int N, cnt;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> answer = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        int index = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt = 1;
                if(map[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j);
                    answer.add(cnt);
                }
            }
        }
        Collections.sort(answer);
        System.out.println(answer.size());
        for(int n : answer) System.out.println(n);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
            if(map[nx][ny] == 1) {
                cnt++;
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }
}
