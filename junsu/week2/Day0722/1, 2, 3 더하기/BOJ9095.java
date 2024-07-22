// 백준 9095번 1, 2, 3 더하기 실버3
import java.io.*;
import java.util.*;

public class BOJ9095 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(br.readLine());
		
		int [] dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;


		for(int test = 0; test < T; test++) {
			int n = Integer.parseInt(br.readLine());
			for(int i = 4; i <= 10; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			System.out.println(dp[n]);
		}
		
		
	}
}
