// BOJ 17136 색종이 붙이기 골드2
// 브루트포스, 특히 백트래킹에 대한 지식과 개념, 구현능력이 부족하단 걸 깨닫게 됐다..
// 첨에 방향성을 잘못 잡고(그냥 생각나는대로 빡구현) gpt에게 피드백 받음
// 다음에 다시 풀어보자
//백트래킹 사용: solve 함수를 재귀적으로 호출하여 모든 경우를 탐색
//색종이 붙이기 및 상태 복구: attach 함수로 색종이를 붙이고 원상태로 복구할 수 있게 했다.
//최적화: 현재 최솟값보다 많은 색종이를 사용하게 되면 종료하도록 했습니다.
import java.io.*;
import java.util.*;

public class BOJ17136 {
    static int N = 10;
    static int ans = Integer.MAX_VALUE;
    static int[][] area = new int[N][N];
    static int[] paperLimit = {0, 5, 5, 5, 5, 5}; // 1부터 5까지 색종이 최대 5장씩
    static int totalOnes = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                if (area[i][j] == 1) totalOnes++;
            }
        }
        // 백트래킹 시작
        solve(0, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void solve(int x, int y, int usedPapers) {
        if (usedPapers >= ans) return; // 현재 최솟값보다 크거나 같으면 종료
        if (x >= N) { // 모든 줄을 다 탐색했을 경우
            ans = Math.min(ans, usedPapers);
            return;
        }
        if (y >= N) { // 현재 줄의 모든 열을 탐색했으면 다음 줄로 이동
            solve(x + 1, 0, usedPapers);
            return;
        }
        if (area[x][y] == 0) { // 현재 위치가 0이면 다음 칸으로 이동
            solve(x, y + 1, usedPapers);
            return;
        }
        for (int size = 5; size >= 1; size--) {
            if (canAttach(x, y, size)) {
                attach(x, y, size, 0); // 색종이를 붙임
                paperLimit[size]--;
                solve(x, y + 1, usedPapers + 1);
                attach(x, y, size, 1); // 상태 복구
                paperLimit[size]++;
            }
        }
    }

    static boolean canAttach(int x, int y, int size) {
        if (paperLimit[size] <= 0) return false;
        if (x + size > N || y + size > N) return false;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (area[i][j] != 1) return false;
            }
        }
        return true;
    }

    static void attach(int x, int y, int size, int state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                area[i][j] = state;
            }
        }
    }
}
