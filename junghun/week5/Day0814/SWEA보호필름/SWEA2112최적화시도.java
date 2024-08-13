package SWexpertAcademy.AType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2112최적화시도 {
    static int D, W, K;
    static int[][] map;
    static int result; // 최솟값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testcase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            result = Integer.MAX_VALUE;

            map = new int[D][W];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // A 약품 처리하지 않아도 되는 경우를 먼저 체크
            if (isOk()) {
                result = 0;
            } else {
                solve(0, 0);
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    // 재귀 탐색 함수
    private static void solve(int index, int cnt) {
        if (cnt >= result) return; // 이미 최솟값을 넘은 경우 가지치기

        if (index == D) {
            if (isOk()) {
                result = cnt;
            }
            return;
        }

        // 현재 행 상태 저장
        int[] original = map[index].clone();

        // 약품 처리 없이 다음 행으로
        solve(index + 1, cnt);

        // A 약품으로 교체한 경우
        fillRow(index, 0);
        solve(index + 1, cnt + 1);

        // B 약품으로 교체한 경우
        fillRow(index, 1);
        solve(index + 1, cnt + 1);

        // 원상복구
        map[index] = original;
    }

    // 특정 행을 특정 값으로 채우는 함수
    private static void fillRow(int index, int value) {
        for (int i = 0; i < W; i++) {
            map[index][i] = value;
        }
    }

    // 조건을 만족하는지 확인하는 함수
    // 조건을 만족하는지 확인하는 함수
    private static boolean isOk() {
        for (int i = 0; i < W; i++) {  // 열 단위로 체크
            int count = 1;  // 연속된 동일한 셀의 개수를 세기 위한 변수
            boolean valid = false;

            for (int j = 1; j < D; j++) {
                if (map[j][i] == map[j - 1][i]) {
                    count++;
                } else {
                    count = 1;
                }

                // 특정 열에서 K개의 연속된 셀이 있는지 확인
                if (count >= K) {
                    valid = true;
                    break;
                }
            }

            // 만약 K개의 연속된 셀이 없는 열이 하나라도 있으면, 조건 불충족
            if (!valid) {
                return false;
            }
        }
        return true;  // 모든 열이 조건을 만족하면 true
    }
}