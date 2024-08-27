import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int input[];
	static int m;
	static int output[];
	static StringBuilder sb;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();

		n = sc.nextInt();

		input = new int[n];

		for (int i = 1; i <= n; i++) {
			input[i - 1] = i;
		}

		m = sc.nextInt();
		output = new int[m];

		permu(0);

		System.out.print(sb);
	}

	static void permu(int order) {
		if (order == m) {
			for (int i : output) {
				sb.append(i).append(" ");
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < n; i++) {
			if (order != 0 && input[i] < output[order - 1])
				continue;
			output[order] = input[i];
			permu(order + 1);
		}

	}
}