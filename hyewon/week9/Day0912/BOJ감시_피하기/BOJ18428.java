package BOJ.Day0912.BOJ감시_피하기;

import java.io.*;
import java.util.*;

public class BOJ18428 {

    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static String[][] originalMap;
    static ArrayList<Node> student = new ArrayList<>();
    static int n;
    static boolean isPossible = false; // 감시를 피할 수 있는지 여부 확인을 위한 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        originalMap = new String[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                originalMap[i][j] = st.nextToken();
                if (originalMap[i][j].equals("S")) student.add(new Node(i, j));
            }
        }

        dfs(0);

        // 모든 경우의 수 탐색 후에도 안전하지 않으면 "NO" 출력
        if (!isPossible) {
            System.out.println("NO");
        }
    }

    static void dfs(int wallCnt) {
        if (wallCnt == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (originalMap[i][j].equals("X")) {
                    originalMap[i][j] = "O";
                    dfs(wallCnt + 1);
                    originalMap[i][j] = "X";
                }
            }
        }
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        String[][] copyMap = new String[n][n];
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            copyMap[i] = originalMap[i].clone();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (originalMap[i][j].equals("T")) {
                    q.add(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x;
            int y = now.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                while (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (copyMap[nx][ny].equals("O")) break;
                    if (copyMap[nx][ny].equals("S")) return; // 학생을 발견하면 종료
                    nx += dx[k];
                    ny += dy[k];
                }
            }
        }

        // 모든 감시자가 학생을 찾지 못한 경우, 감시를 피할 수 있음
        isPossible = true;
        System.out.println("YES");
        System.exit(0);
    }

    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
