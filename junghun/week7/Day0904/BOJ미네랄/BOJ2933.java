package week7.Day0904.BOJ미네랄;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/*
1.입력받기:
    R, C: 맵의 행과 열 크기.
    맵의 상태: 미네랄('x')과 빈공간('.')으로 구성.
    막대를 던지는 높이를 저장하는 리스트.

2.막대 던지기:
    왼쪽에서 오른쪽으로, 오른쪽에서 왼쪽으로 번갈아가며 막대를 던지며 미네랄을 파괴.

3.클러스터 확인 및 떨어뜨리기:
    파괴된 미네랄을 기준으로 나눠진 클러스터가 공중에 떠 있는지 확인.
    공중에 떠 있다면 클러스터를 내려놓음.
 */
public class BOJ2933 {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> shoot;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int count = Integer.parseInt(br.readLine());
        shoot = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            shoot.add(Integer.parseInt(st.nextToken()));
        }

        // 막대 던지기
        for (int i = 0; i < shoot.size(); i++) {
            int height = R - shoot.get(i); // 높이는 아래에서 올라오는 구조 R - Height
            if (i % 2 == 0) {
                // 왼쪽에서 던지기
                for (int j = 0; j < C; j++) {
                    if (map[height][j] == 'x') {
                        map[height][j] = '.'; // 미네랄 파괴
                        break;
                    }
                }
            } else {
                // 오른쪽에서 던지기
                for (int j = C - 1; j >= 0; j--) {
                    if (map[height][j] == 'x') {
                        map[height][j] = '.';
                        break;
                    }
                }
            }
            // 클러스터 떨어트리기
            dropCluster();
        }

        // 최종 맵 출력
        printMap();
    }

    // 맵 출력하는 함수
    private static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    //  네 방향 중 하나로 인접한 미네랄이 포함된 두 칸은 같은 클러스터
    private static void dropCluster() {
        boolean[][] isVisited = new boolean[R][C];
        ArrayList<int[]> floatingCluster = new ArrayList<>(); // 떠있는 클러스터

        // 바닥과 연결되어 붙어있는 클러스트는 방문표시
        for (int i = 0; i < C; i++) {
            if (map[R - 1][i] == 'x' && !isVisited[R - 1][i]) {
                isVisited[R - 1][i] = true;
                dfs(R - 1, i, isVisited);
            }
        }

        // 공중에 떠있는 클러스터 탐색
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && !isVisited[i][j]) {
                    floatingCluster.add(new int[]{i, j});
                    map[i][j] = '.';
                }
            }
        }

        if (floatingCluster.isEmpty()) {
            return;
        }

        // 공중에 떠있는 클러스터를 아래로 떨어트리기
        boolean drop = true;
        while (drop) {
            for (int[] cluster : floatingCluster) {
                int nx = cluster[1];
                int ny = cluster[0] + 1;
                if (ny >= R || (map[ny][nx] == 'x' && isVisited[ny][nx])) {
                    drop = false;
                    break;
                }
            }

            if (drop) {
                for (int i = 0; i < floatingCluster.size(); i++) {
                    floatingCluster.get(i)[0]++;
                }
            }
        }
        // 새로운 위치에 다시 놓아
        for (int[] cluster : floatingCluster) {
            map[cluster[0]][cluster[1]] = 'x';
        }
    }

    private static void dfs(int x, int y, boolean[][] isVisited) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        isVisited[x][y] = true;

        while (!stack.isEmpty()) {
            int[] now = stack.pop();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C || isVisited[nx][ny] || map[nx][ny] == '.') {
                    continue;
                }

                stack.push(new int[]{nx, ny});
                isVisited[nx][ny] = true;
            }
        }
    }

}
