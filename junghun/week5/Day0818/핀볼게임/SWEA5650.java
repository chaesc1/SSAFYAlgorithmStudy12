package Day0818.핀볼게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SWEA5650 {
    static class WormHole {
        int x; // x 좌표
        int y; // y 좌표
        int num; // 번호

        public WormHole(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static int N;
    static int[][] board;
    // 시계방향 방향전환
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    // 웜홀을 어떻게 저장하지?
    static HashMap<Integer, ArrayList<WormHole>> wormholeMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine().trim());
            board = new int[N][N];
            wormholeMap = new HashMap<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int cell = Integer.parseInt(st.nextToken());
                    board[i][j] = cell;
                    if (cell >= 6) {
                        if (!wormholeMap.containsKey(cell)) {
                            wormholeMap.put(cell, new ArrayList<>());
                        }
                        wormholeMap.get(cell).add(new WormHole(i, j, cell));
                    }
                }
            }

            System.out.println("#" + tc + " " + pinball());
        }

    }

    private static int pinball() {
        int score = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    continue;
                }
                // 시작
                for (int k = 0; k < 4; k++) {
                    score = Math.max(score, gameStart(i, j, k));
                }
            }
        }
        return score;
    }

    private static int gameStart(int x, int y, int dir) {
        int score = 0;
        int nx = x;
        int ny = y;
        while (true) {
            nx += dx[dir];
            ny +=  dy[dir];

            if (x == nx && y == ny) {
                return score;
            }

            // 벽을 만나면 -> 방향반전
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                dir = (dir + 2) % 4;
                score++;
                continue;
            }
            // 블록 만나면
            if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
                // 꺾이는 각도 구현
                dir = changeDir(board[nx][ny], dir);
                score++;
            } else if (board[nx][ny] == -1) {
                return score;
            } // 웜홀에 대한 처리 시작
            else if (board[nx][ny] > 5) {
                int cell = board[nx][ny];
                ArrayList<WormHole> holes = wormholeMap.get(cell);

                for (WormHole hole : holes) {
                    if (hole.x != nx || hole.y != ny) {
                        nx = hole.x;
                        ny = hole.y;
                        break;
                    }
                }
            }
        }
    }

    private static int changeDir(int shape, int dir) {
        // 0 1 2 3
        // 상 좌 하 우
        if (shape == 1) {
            if (dir == 3 || dir == 0) {
                dir = (dir + 2) % 4; // 오른쪽, 위
            } else if (dir == 1) {
                dir = 0;
            } else {
                dir = 3;
            }
        } else if (shape == 2) {
            if (dir == 2 || dir == 3) {
                dir = (dir + 2) % 4; // 오른쪽, 아래
            } else if (dir == 0) {
                dir = 3;
            } else {
                dir = 2;
            }
        } else if (shape == 3) {
            if (dir == 1 || dir == 2) {
                dir = (dir + 2) % 4; // 왼쪽, 아래
            } else if (dir == 0) {
                dir = 1;
            } else {
                dir = 2;
            }
        } else if (shape == 4) {
            if (dir == 0 || dir == 1) {
                dir = (dir + 2) % 4; // 왼쪽, 위
            } else if (dir == 3) {
                dir = 0;
            } else {
                dir = 1;
            }
        } else if (shape == 5) {
            dir = (dir + 2) % 4;
        }
        return dir;
    }
}
