import java.io.*;
import java.util.*;

public class Solution {
	static int answer = 0;
	static int oneDay, oneMonth, threeMonth, oneYear;
	static int[] plans = new int[13];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			oneDay = Integer.parseInt(st.nextToken());
			oneMonth = Integer.parseInt(st.nextToken()); 
			threeMonth = Integer.parseInt(st.nextToken()); 
			oneYear = Integer.parseInt(st.nextToken()); 

			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= 12; i++) {
				plans[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = oneYear;
			
			dfs(0, 0); // 0부터 1월
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	public static void dfs(int month, int cost) {
		if(month > 12) {
			if(cost < answer) // 최소 비용 구하기
				answer = cost;
			return;
		}
		// 이용 O
		if(plans[month] > 0) {
			// 1일
			dfs(month + 1, cost + oneDay * plans[month]);
			// 1달
			dfs(month + 1, cost + oneMonth);
			// 3달
			dfs(month + 3, cost + threeMonth);
		}
		// 이용 X
		else dfs(month + 1, cost);
	}
}
