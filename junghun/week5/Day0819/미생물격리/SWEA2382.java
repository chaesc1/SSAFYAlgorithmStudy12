package Day0819.미생물격리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 미생물 군집이 이동 후 약품이 칠해진 셀에 도착하면 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다.
 * <p>
 * 미생물 수가 홀수인 경우 반으로 나누어 떨어지지 않으므로, 다음과 같이 정의한다.
 * <p>
 * 살아남은 미생물 수 = 원래 미생물 수를 2로 나눈 후 소수점 이하를 버림 한 값
 */
public class SWEA2382 {
    static class Cell{
        int x;
        int y;
        int count; // 미생물수
        int dir;

        public Cell(int x, int y, int count, int dir) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.dir = dir;
        }

    }

    static class TempCell {
        int maxCount;
        int count;
        int dir;

        public TempCell(int maxCount, int count, int dir) {
            this.maxCount = maxCount;
            this.count = count;
            this.dir = dir;
        }
    }

    static TempCell[][] tempMap;

    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M, K; //셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K
    static ArrayList<Cell> cells;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cells = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                Cell cell = new Cell(x, y, count, dir - 1);
                cells.add(cell);
            }
            // 격리 시간만큼 진행
            for (int day = 0; day < M; day++) {
                tempMap = new TempCell[N][N];

                for (Cell cell : cells) {
                    int nx = cell.x + dx[cell.dir];
                    int ny = cell.y + dy[cell.dir];
                    int curDir = cell.dir;

                    cell.x = nx;
                    cell.y = ny;
                    // 약품이면
                    if (isChem(nx, ny)) {
                        cell.count /= 2; // 미생물 수 절반
                        cell.dir = changeDir(curDir);
                    }

                    if (tempMap[nx][ny] == null) {
                        tempMap[nx][ny] = new TempCell(cell.count, cell.count, cell.dir);
                    } else {
                        tempMap[nx][ny].count += cell.count;
                        if (cell.count > tempMap[nx][ny].maxCount) {
                            tempMap[nx][ny].maxCount = cell.count;
                            tempMap[nx][ny].dir = cell.dir;
                        }
                    }
                }

                cells.clear();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (tempMap[i][j] != null) {
                            cells.add(new Cell(i, j, tempMap[i][j].count, tempMap[i][j].dir));
                        }

                    }
                }
            }

            System.out.println("#" + tc + " " + cells.stream().mapToInt(cell -> cell.count).sum());
        }
    }

    // 0 1 2 3 상하좌우
    private static int changeDir(int dir) {
        if (dir % 2 == 0) {
            dir += 1;
        } else {
            dir -= 1;
        }
        return dir;
    }

    private static boolean isChem(int nx, int ny) {
        if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) {
            return true;
        }
        return false;
    }
}
