package SWexpertAcademy.AType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//두께 D, 가로크기 W인 보호 필름 단면의 정보와 합격기준 K
public class SWEA2112 {
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
            solve(0, 0);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    // index 번째 행에 대한 조건 탐색을 한다
    // A 약품 넣어보고, B 약품 넣어보고 둘다 해보고
    // 원상복구 시킨다.
    // cnt -> 약품 처리 횟수
    private static void solve(int index, int cnt) {
        if (isOk()) {
            result = Math.min(cnt, result);
            return;
        }
        if (cnt > result) {
            return;
        }

        if (index == D) {
            return;
        }

        int[] col = new int[W];

        for (int i = 0; i < W; i++) {
            col[i] = map[index][i];
        }

        solve(index + 1, cnt);

        // A 로 교체해봐
        for (int i = 0; i < W; i++) {
            map[index][i] = 0;
        }
        solve(index + 1, cnt + 1);

        // B로 교체
        for (int i = 0; i < W; i++) {
            map[index][i] = 1;
        }
        solve(index + 1, cnt + 1);

        // 원상복구
        for (int i = 0; i < W; i++) {
            map[index][i] = col[i];
        }
    }

    // 열을 체크해서 연속된 K 개가 있는지 체크
    private static boolean isOk() {
        for (int i = 0; i < W; i++) {
            int count = 1; // 시작 포함
            boolean isValid = false;

            for (int j = 1; j < D; j++) {
                if (map[j][i] == map[j - 1][i]) {
                    count++;
                } else {
                    count = 1;
                }

                if (count == K) {
                    isValid = true;
                    break;
                }
            }
            // 마지막까지 체크했는데 합격요건에 부합하지 않으면
            if (!isValid) {
                return false;
            }
        }

        return true;
    }
}
