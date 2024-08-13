package SWexpertAcademy.D3;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA5653 {
    static class Cell {
        int x;
        int y;
        int power;
        int time;
        int status;

        public Cell(int power, int time, int y, int x) {
            this.power = power;
            this.status = INACTIVE;
            this.time = time;
            this.y = y;
            this.x = x;
        }
    }

    private static final int INACTIVE = 2, ACTIVE = 1, DEAD = 0;
    static int N, M, K;
    static int[][] map;
    static List<Cell> cell;
    static PriorityQueue<Cell> pq;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;

        int testcase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cell = new ArrayList<>();
            pq = new PriorityQueue<>((a, b) -> b.power - a.power);
            visited = new boolean[N + K * 2][M + K * 2];
            map = new int[N + K * 2][M + K * 2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num != 0) {
                        cell.add(new Cell(num, num, i + K, j + K));
                        visited[i + K][j + K] = true;
                    }
                }
            }

            solve();
            System.out.println("#" + tc + " " + countCell());
        }

    }

    private static int countCell() {
        int count = 0;
        for (Cell value : cell) {
            if (value.status == ACTIVE || value.status == INACTIVE) {
                count++;
            }
        }
        return count;
    }

    private static void solve() {
        for (int i = 1; i <= K; i++) {
            while (!pq.isEmpty()) {
                Cell c = pq.poll();
                int x = c.x;
                int y = c.y;

                if (!visited[y][x]) {
                    visited[y][x] = true;
                    cell.add(c);
                }
            }

            for (Cell value : cell) {
                if (value.status == INACTIVE && value.time == i) {
                    value.status = ACTIVE; // x시간 동안 활성ㅅ상태 유지하고
                    value.time = i + value.power; // 현재시간보다 x 지난후에 죽는상태로
                    for (int k = 0; k < 4; k++) {
                        int nx = value.x + dx[k];
                        int ny = value.y + dy[k];

                        pq.add(new Cell(value.power, i + 1 + value.power, ny, nx));
                    }
                } else if (value.status == ACTIVE && value.time == i) {
                    value.status = DEAD;
                    value.time = 0;
                    value.power = 0;
                }
            }
        }
    }
}
