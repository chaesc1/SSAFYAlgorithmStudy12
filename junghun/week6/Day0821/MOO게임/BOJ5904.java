package week6.Day0821.MOO게임;

import java.io.BufferedReader;

public class BOJ5904 {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // Moo 문자열 패턴:
        // S(K) = S(K-1) + "m" + "o" * (k+2) + S(K-1)
        // Base case는 "moo"

        // 초기 길이와 단계 설정
        int length = 3; // S(0)의 길이 ("moo")
        int k = 0;

        // 길이가 N을 포함할 수 있을 때까지 단계 증가
        // length는 S(K)의 길이
        while (length < N) {
            k++;
            // S(K)를 계산: S(K-1)과 중간의 "m" + "o"* (k+3) 그리고 또다른 S(K-1)을 합친 것
            length = 2 * length + (k + 3);
        }

        // 주어진 N 위치의 문자열을 찾기 위해 재귀 호출
        callMoo(N, k, length);
        System.out.println(sb.toString());
    }

    // 재귀적으로 Moo 문자열에서 N 위치의 문자를 찾는 함수
    private static void callMoo(int n, int k, int length) {
        // Base case: k가 0일 때 S(0) 문자열은 "moo"
        if (k == 0) {
            if (n == 1) {
                sb.append("m");
            } else {
                sb.append("o");
            }
            return;
        }

        // 이전 단계의 S(K-1) 문자열 길이
        int prevLen = (length - (k + 3)) / 2;

        if (n <= prevLen) {
            // N번째 문자가 첫 번째 S(K-1)에 포함된 경우
            callMoo(n, k - 1, prevLen);
        } else if (n > prevLen + (k + 3)) {
            // N번째 문자가 두 번째 S(K-1)에 포함된 경우
            callMoo(n - (prevLen + (k + 3)), k - 1, prevLen);
        } else {
            // N번째 문자가 중간 "m" + "o"* (k+2)에 포함된 경우
            long midIndex = n - prevLen;
            if (midIndex == 1) {
                sb.append("m");
            } else {
                sb.append("o");
            }
        }
    }
}