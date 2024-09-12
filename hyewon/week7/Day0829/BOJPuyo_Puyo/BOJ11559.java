package BOJ.Day0829.BOJPuyo_Puyo;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class BOJ11559 {
    static int R = 12, C = 6, max = 0;
    static String[][] field = new String[R][C];
    static boolean[][] visited;
    static List<Point> point;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < R; i++) {
            field[i] = br.readLine().split("");
        }

        int answer = 0;
        boolean flag = true;
        while (flag) {
            visited = new boolean[R][C];
            flag = false;
            for (int i = R - 1; i >= 0; i--) {
                for (int j = 0; j < C; j++) {
                    if (field[i][j].equals(".")) continue;
                    point = new ArrayList<>();
//                    System.out.println("r : " + i + " c : " + j);
                    if (dfs(i, j, field[i][j]) >= 4) {
                        for (Point p : point) field[p.x][p.y] = ".";
                        flag = true;
                    }
//                    System.out.println();
//                    print();
                }
            }
            if (flag) {
                answer++;
                down();
            }
        }
        System.out.println(answer);
    }
    public static int dfs(int r, int c, String color) {
        visited[r][c] = true;
        point.add(new Point(r, c));
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc] || !field[nr][nc].equals(color)) continue;
//            System.out.println("nr : " + nr + " nc : " + nc + visited[nr][nc]);
//            System.out.println("cnt : " + cnt + " field color : " + field[nr][nc] + " now : " + color);
            cnt += dfs(nr, nc, color);
        }
        return cnt;
    }

    public static void down() {
        for (int i = 0; i < C; i++) {
            Stack<String> stack = new Stack<>();
            for (int j = 0; j < R; j++) {
                if (!field[j][i].equals(".")) {
                    stack.push(field[j][i]);
                    field[j][i] = ".";
                }
            }
            int cnt = R - 1;
            while (!stack.isEmpty()) {
                field[cnt--][i] = stack.pop();
            }
        }
    }

    public static void print() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
