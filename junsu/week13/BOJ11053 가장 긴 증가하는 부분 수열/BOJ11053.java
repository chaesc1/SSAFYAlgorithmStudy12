//BOJ11053 가장 긴 증가하는 부분 수열, 실버2
import java.io.*;
import java.util.*;

public class BOJ11053 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N+1];
		int[] dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
//		System.out.println(Arrays.toString(A));
		
		int max = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j < i; j++) {
				if(A[i] > A[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1); // 이전 원소들 중 가장 큰 dp값 + 1
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(Arrays.toString(dp));

		bw.write(max+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
