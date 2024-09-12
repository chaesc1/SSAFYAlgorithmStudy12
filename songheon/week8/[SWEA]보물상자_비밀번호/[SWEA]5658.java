import java.io.*;
import java.util.*;

public class Solution {

	static int tc;
	static int n;
	static int k;
	static char snum[];
	static int answer = 0;
	static Integer nums[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");

			// 입력 받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			snum = new char[n];
			snum = br.readLine().toCharArray();
			nums = new Integer[n];

			// 숫자 회전하기
			for (int i = 0; i < n / 4; i++) {
//				System.out.println(Arrays.toString(snum));
				// 숫자 구하기
				makeNum(i, snum);
				char[] tmp = new char[n];
				for (int j = 0; j < n-1; j++) {
					tmp[j] = snum[j + 1];
				}
				tmp[n-1] = snum[0];
				snum = tmp;
			}
			Arrays.sort(nums, Collections.reverseOrder());
//			System.out.println(Arrays.toString(nums));

			int pre = -1;
			int order = 0;
			for (int i = 0; i < n; i++) {
				if (nums[i] == pre) {
					pre = nums[i];	
					continue;
				}
				order++;
				if (order == k) {
					answer = nums[i];
					break;
				}
				pre = nums[i];
			}

			sb.append(answer).append('\n');
		}

		System.out.println(sb);

	}

	static void makeNum(int order, char[] snum) {
		// 숫자 변환
		for (int i = 0; i < 4; i++) {
			StringBuilder tmp = new StringBuilder();
			int start = i * n / 4;
			int end = i * n / 4 + n / 4;
			for (int c = start; c < end; c++) {
				tmp.append(snum[c]);
			}

			// 십진수로 변환
			int num = 0;
			for (int j = 0; j < n / 4; j++) {
				char cur = tmp.charAt(n / 4 - 1 - j);
				if ('0' <= cur && cur <= '9')
					num += ((cur - '0') * Math.pow(16, j));
				else
					num += ((10 + (cur - 'A')) * Math.pow(16, j));
//				System.out.println(num);
			}
			nums[order * 4 + i] = num;
		}
	}
}