import java.util.*;
import java.io.*;

public class Main {

	static int n, k;
	static int w[], v[];
	static int dp[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		w = new int[n+1];
		v = new int[n+1];
		dp = new int[n+1][k + 1];
		
		for(int i = 1; i <= n; i++) {
			w[i] = sc.nextInt();
			v[i] = sc.nextInt();
		}
		
		for(int i = 1; i <= n; i++) {
			// i : 현재 넣을 물건의 인덱스
			for(int j = 1; j <= k; j++) {
				// j : 현재 가능한 무게
				dp[i][j] = dp[i-1][j];
				if(w[i] <= j) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]] + v[i]);
				}
			}
		}
		System.out.print(dp[n][k]);
		
	}
	
}