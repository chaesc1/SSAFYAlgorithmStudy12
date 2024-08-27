import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int tmp[], input[];
	static int m;
	static int limit = 1;
	static int output[];
	static StringBuilder sb;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();

		n = sc.nextInt();
		m = sc.nextInt();
		tmp = new int[n];
		output = new int[m];
		
		//버퍼에 일단 입력 받기 
		tmp[0] = sc.nextInt();
		for (int i = 1; i < n; i++) {
			tmp[i] = sc.nextInt();
		}
		
		//중복 안 된 원소 사전 순으로 저장
		input = new int[n];
		Arrays.sort(tmp);
		input[0] = tmp[0];
		int iter = 0;
		for (int i = 1; i < n; i++) {
			if(tmp[i] != tmp[i-1]) {
				iter++;
				input[iter] = tmp[i];
				limit++;
			}
		}
		
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

		for (int i = 0; i < limit; i++) {
			if (order != 0 && input[i] < output[order - 1])
				continue;
			output[order] = input[i];
			permu(order + 1);
		}

	}
}