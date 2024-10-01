//BOJ12865 평범한 배낭, 골드5
// DP문제, 이해하는데 시간을 많이 사용하였다.
import java.io.*;
import java.util.*;

public class BOJ12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[N+1][K+1];
			int[] W = new int[N+1];
			int[] V = new int[N+1];
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				W[i] = Integer.parseInt(st.nextToken());
				V[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int k = 1; k <= K; k++) { //무게
				for(int i = 1; i <= N; i++) { // 물품의 수
					dp[i][k] = dp[i-1][k]; //기본적으로 이전 아이템의 가치를 저장하고 시작
					if(k - W[i] >= 0) { //무게에서 자신의 무게를 뺐을 때 남는 무게가 존재한다면
						//이전 물품에서 구한 가치 VS (남은 무게의 가치 + 자신의 가치) 중에서 큰 값을 저장한다.
						dp[i][k] = Math.max(dp[i-1][k], V[i] + dp[i-1][k - W[i]]);
					}
				}
			}
			
			bw.write("#"+ tc + " " + dp[N][K] + "\n");
			bw.flush();
		}
		bw.close();
		br.close();
	}
}
