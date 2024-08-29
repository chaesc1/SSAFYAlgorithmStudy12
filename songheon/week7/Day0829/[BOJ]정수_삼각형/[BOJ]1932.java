import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int tri[][];
	static int value[][];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		tri = new int[n][n];
		value = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i + 1; j++) {
				tri[i][j] = sc.nextInt();
			}
		}
		
		value[0][0] = tri[0][0];
		dfs(1, 0, value[0][0]);

		System.out.println(answer);
	}
	
	static void dfs(int order, int start, int sum) {
		if(order == n) {
//			System.out.println(sum);
			answer = Math.max(sum, answer);
			return;
		}
		
		if(value[order][start] < sum + tri[order][start]) {
			value[order][start] = sum + tri[order][start];
			dfs(order + 1, start, value[order][start]);			
		}
		if(value[order][start + 1] < sum + tri[order][start + 1]) {
			value[order][start + 1] = sum + tri[order][start + 1];
			dfs(order + 1, start + 1, value[order][start + 1]);
		}
	}
}