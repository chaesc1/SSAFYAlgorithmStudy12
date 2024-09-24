package BOJ.Day0920.BOJ뱀;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.List;

public class BOJ3190 {
    static int N, K, L, answer = 0;
    static int[][] map;
    static boolean[][] visited;
    static Deque<Point> snack = new LinkedList<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        snack.addFirst(new Point(0, 0));

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 2;
        }

        List<Command> com = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            com.add(new Command(second, d));
        }

        int direct = 0;
        int idx = 0;
        while (true) {
            answer++;
            if (!move(direct)) break;

            if (idx < com.size() && com.get(idx).second == answer) {
                if (com.get(idx).dir.equals("D")) direct = (direct + 1) % 4;
                else direct = (direct + 3) % 4;
                idx++;
            }
        }
        System.out.println(answer);
    }

    public static boolean move(int d) {
        Point head = snack.getFirst();
        int nr = head.y + dr[d];
        int nc = head.x + dc[d];

        if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) return false;

        snack.addFirst(new Point(nc, nr));
        visited[nr][nc] = true;

        if (map[nr][nc] == 2) {
            map[nr][nc] = 0;
        } else {
            Point tail = snack.pollLast();
            visited[tail.y][tail.x] = false;
        }
        return true;
    }

    static class Command {
        int second;
        String dir;
        public Command(int second, String dir) {
            this.second = second;
            this.dir = dir;
        }
    }
}
