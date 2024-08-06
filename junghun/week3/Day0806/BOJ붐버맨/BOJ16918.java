package Day0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918 {
    static int r, c, n;
    static char[][] map;
    static int[][] bombTime;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        bombTime = new int[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'O') {
                    bombTime[i][j] = 3; // 폭탄의 시간초..
                }
            }
        }
        time = 1;

        while (time++ < n) {
            // 짝수 초일땐 폭탄을 전부 깔아
            if (time % 2 == 0) {
                plantBomb();
            } else {
                // 터뜨릴 폭탄을 찾고 터뜨려야해
                searchBomb();
            }
            // 홀수 초일땐 동시다발적으로 터뜨려
        }

        print();
    }

    private static void print() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void plantBomb() {
        for (int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(map[i][j] == '.') {
                    map[i][j] = 'O';
                    bombTime[i][j] = time+3; // 현재시간에 폭탄3초를 더해.
                }
            }
        }
    }

    private static void searchBomb() {
        for (int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(map[i][j] == 'O' && bombTime[i][j] == time) {
                    bomb(i,j);
                }
            }
        }
    }

    private static void bomb(int x, int y) {
        map[x][y] = '.';
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx,ny) && map[nx][ny] == 'O' && bombTime[nx][ny] != time) {
                map[nx][ny] = '.';
                bombTime[nx][ny] = 0;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}
