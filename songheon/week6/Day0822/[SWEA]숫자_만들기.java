import java.util.*;
import java.io.*;

public class Solution {

	// 첫번째 연산부터 dfs 진행 -> 중복 순열
	// 매개변수로 현재까지 계산 결과 저장
	// 단 해당 연산의 남아있는 개수 체크 -> 0이면 가지치기
	// 연산 완성 후 계산 결과를 최댓값 최솟값 업데이트
	// 최댓값 최솟값의 차 계산

	static int tc;
	static int n;
	static int op[]; // 남아있는 연산 수 ( + - * / )
	static int num[]; // 계산의 대상이 되는 연산 수
	static int answer;
	static int mx, mn;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {

			n = sc.nextInt();
			op = new int[4];
			num = new int[n];
			mx = -100000000;
			mn = 100000000;

			for (int i = 0; i < 4; i++) {
				op[i] = sc.nextInt();
			}

			for (int i = 0; i < n; i++) {
				num[i] = sc.nextInt();
			}

			dfs(1, num[0]);
	
			sb.append("#").append(t).append(" ").append(mx - mn).append('\n');
		}

		System.out.print(sb);
	}

	static void dfs(int order, int result) {

		if (order == n) {
			mx = Math.max(mx, result);
			mn = Math.min(mn, result);
			return;
		}

		// + 연산
		if (op[0] > 0) {
			op[0]--;
			dfs(order + 1, result + num[order]);
			op[0]++;
		}
		// - 연산
		if (op[1] > 0) {
			op[1]--;
			dfs(order + 1, result - num[order]);
			op[1]++;
		}
		// * 연산
		if (op[2] > 0) {
			op[2]--;
			dfs(order + 1, result * num[order]);
			op[2]++;
		}
		// / 연산
		if (op[3] > 0) {
			op[3]--;
			dfs(order + 1, result / num[order]);
			op[3]++;
		}

	}

}