import java.io.*;
import java.util.*;

public class Main {
	static int tc;
	static int n;
	static int st[][];
	static int dp[][];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			n = sc.nextInt();
			st = new int[2][n];
			dp = new int[2][n];
			
			for(int i = 0; i < n; i++) {
				st[0][i] = sc.nextInt();
			}
			for(int i = 0; i < n; i++) {
				st[1][i] = sc.nextInt();
			}
			
			dp[0][0] = st[0][0];
			dp[1][0] = st[1][0];
			if(n != 1) {
				dp[0][1] = dp[1][0] + st[0][1];
				dp[1][1] = dp[0][0] + st[1][1];				
				
			}
			for(int i = 2; i < n; i++) {
				dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + st[0][i];
				dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + st[1][i];
			}
			
			
			sb.append(Math.max(dp[0][n-1], dp[1][n-1])).append('\n');
		}
		System.out.print(sb);
		
	}
}