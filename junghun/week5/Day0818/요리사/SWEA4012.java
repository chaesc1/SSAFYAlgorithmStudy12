package Day0818.요리사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4012 {
    static int N;
    static int[][] map;
    static boolean[] isSelected;
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            isSelected = new boolean[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = Integer.MAX_VALUE;
            // 식재료의 조합을 이용
            sb.append("#").append(tc).append(" ");
            comb(0,0);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void comb(int depth, int num) {

        if (depth == N / 2) {
            int diff = calc();
            answer = Math.min(answer, diff);
            return;
        }

        for (int i = num; i < N; i++) {
            isSelected[i] = true;
            comb(depth + 1, i + 1);
            isSelected[i] = false;
        }
    }

    private static int calc() {
        int sumA = 0; // 내가 선택한거 합
        int sumB = 0; // 상대방이 선택한 합

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isSelected[i] && isSelected[j]) {
                    sumA += map[i][j] + map[j][i];
                }
                if (!isSelected[i] && !isSelected[j]) {
                    sumB += map[i][j] + map[j][i];
                }
            }
        }
        return Math.abs(sumA - sumB);
    }
}
