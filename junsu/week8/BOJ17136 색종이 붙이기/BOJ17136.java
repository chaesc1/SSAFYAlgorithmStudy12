// BOJ 17136 ������ ���̱� ���2
// ���Ʈ����, Ư�� ��Ʈ��ŷ�� ���� ���İ� ����, �����ɷ��� �����ϴ� �� ���ݰ� �ƴ�..
// ÷�� ���⼺�� �߸� ���(�׳� �������´�� ������) gpt���� �ǵ�� ����
// ������ �ٽ� Ǯ���
//��Ʈ��ŷ ���: solve �Լ��� ��������� ȣ���Ͽ� ��� ��츦 Ž��
//������ ���̱� �� ���� ����: attach �Լ��� �����̸� ���̰� �����·� ������ �� �ְ� �ߴ�.
//����ȭ: ���� �ּڰ����� ���� �����̸� ����ϰ� �Ǹ� �����ϵ��� �߽��ϴ�.
import java.io.*;
import java.util.*;

public class BOJ17136 {
    static int N = 10;
    static int ans = Integer.MAX_VALUE;
    static int[][] area = new int[N][N];
    static int[] paperLimit = {0, 5, 5, 5, 5, 5}; // 1���� 5���� ������ �ִ� 5�徿
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
        // ��Ʈ��ŷ ����
        solve(0, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void solve(int x, int y, int usedPapers) {
        if (usedPapers >= ans) return; // ���� �ּڰ����� ũ�ų� ������ ����
        if (x >= N) { // ��� ���� �� Ž������ ���
            ans = Math.min(ans, usedPapers);
            return;
        }
        if (y >= N) { // ���� ���� ��� ���� Ž�������� ���� �ٷ� �̵�
            solve(x + 1, 0, usedPapers);
            return;
        }
        if (area[x][y] == 0) { // ���� ��ġ�� 0�̸� ���� ĭ���� �̵�
            solve(x, y + 1, usedPapers);
            return;
        }
        for (int size = 5; size >= 1; size--) {
            if (canAttach(x, y, size)) {
                attach(x, y, size, 0); // �����̸� ����
                paperLimit[size]--;
                solve(x, y + 1, usedPapers + 1);
                attach(x, y, size, 1); // ���� ����
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
