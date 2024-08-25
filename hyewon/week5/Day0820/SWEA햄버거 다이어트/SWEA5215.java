import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int tc= Integer.parseInt(br.readLine());
		StringBuilder sb= new StringBuilder();
		for(int t= 1; t<= tc ;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int n = Integer.parseInt(st.nextToken()); //재료의 수
			int l = Integer.parseInt(st.nextToken()); //제한 칼로리
			int[] T = new int[n+1];
			int[] K = new int[n+1];
			for(int i=1;i<=n;i++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine()," ");
				T[i]=Integer.parseInt(st1.nextToken());
				K[i]=Integer.parseInt(st1.nextToken());
			}
			
			int[][] dp=new int[n+1][l+1];
			
			for(int i=1;i<=n;i++) {
				for(int j=0;j<=l;j++) {
				if(j<K[i]) {
						dp[i][j]=dp[i-1][j];
					}
					else {
						dp[i][j]=Math.max(dp[i-1][j-K[i]]+T[i], dp[i-1][j]);
					}
				}
			}	
			sb.append("#").append(t).append(" ").append(dp[n][l]).append("\n");
		}
		
		System.out.print(sb);
	}
}