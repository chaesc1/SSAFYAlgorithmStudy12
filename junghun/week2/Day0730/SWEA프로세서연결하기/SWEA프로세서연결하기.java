package Day0729.algo;

import java.io.*;
import java.util.*;

public class SWEA프로세서연결하기 {
    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int[][] map;
    static ArrayList<Pos> list; // 연결가능한 cell 좌표 정보
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int core, count;
    static int answer;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            // map 입력
            list = new ArrayList<>();
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 셀이고 테두리 부분 아니면
                    if (i != 0 && i != N - 1 && j != 0 && j != N - 1 && map[i][j] == 1) {
                        list.add(new Pos(i, j));
                    }
                }
            }

            core = 0;
            answer = 144;
            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void dfs(int depth, int c, int line) {
        if (depth == list.size()) {
            if (core < c) {
                core = c;
                answer = line;
            } else if (core == c) {
                if (answer > line) {
                    answer = line;
                }
            }
            return;
        }
        // 4방
        for (int i = 0; i < 4; i++) {
            // 연결 할 수 있는지 체크
            if (isOk(list.get(depth), i)) {
                process(list.get(depth), i, 2); // 전선 깔린걸 2로 표시
                dfs(depth + 1, c + 1, line + count);
                process(list.get(depth), i, 0);
            }
        }
        dfs(depth + 1, c, line);
    }

    private static void process(Pos pos, int dir, int val) {
        count = 0;

        int x = pos.x + dx[dir];
        int y = pos.y + dy[dir];

        while (x >= 0 && x < N && y >= 0 && y < N) {
            map[x][y] = val;
            count++;
            x += dx[dir];
            y += dy[dir];
        }
    }

    private static boolean isOk(Pos pos, int i) {
        int x = pos.x + dx[i];
        int y = pos.y + dy[i];

        while (x >= 0 && x < N && y >= 0 && y < N) {
            if(map[x][y] != 0) return false;
            x += dx[i];
            y += dy[i];
        }
        return true;
    }

}
