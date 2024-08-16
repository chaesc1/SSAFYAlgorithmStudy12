package Day0816.SWEA홈방범서비스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2117 {
    static class House {
        int x, y;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, max;
    static int[][] board;
    static ArrayList<House> houseList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testcase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testcase; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            board = new int[n][n];
            houseList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        houseList.add(new House(i, j));
                    }
                }
            }
            // 전부 덮는 k부터 1까지 줄여나가
            int K = 0;
            if (K % 2 == 0) {
                K = n + 1;
            } else {
                K = n;
            }
            // 완전 탐색
            max = Integer.MIN_VALUE;
            for (int k = K; k >= 1; k--) {
                if (getOperation(k) <= max) break;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        // x,y 좌표를 중심으로 k + - 만큼
                        solve(i, j, k);
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
    }

    //서비스 제공받는 집들을 통해 얻는 수익(집갯수*M) - 운영 비용(5)
    private static void solve(int x, int y, int k) {
        int houseCount = 0;
        for (House house : houseList) {
            int dist = Math.abs(house.x - x) + Math.abs(house.y - y);
            if (dist < k) {
                houseCount++;
            }
        }
        int cost = (houseCount * m) - getOperation(k);
        if (cost >= 0) {
            max = Math.max(max, houseCount);
        }
    }

    //운영 비용 = K * K + (K - 1) * (K - 1)
    private static int getOperation(int k) {
        return k * k + (k - 1) * (k - 1);
    }
}
