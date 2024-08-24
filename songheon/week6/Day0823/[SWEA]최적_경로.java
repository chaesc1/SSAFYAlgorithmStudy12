import java.util.Scanner;

public class Solution {
	
	// 회사에서부터 출발하여 모든 경로를 탐색
	// dfs를 실행하며 모든 고객의 집을 방문하여
	
	static int tc, n;
	static Pair start;
	static Pair end;
	static Pair[] cust;
	static boolean[] visit;
	static int answer;
	
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
		
			n = sc.nextInt();
			start = new Pair(sc.nextInt(), sc.nextInt());
			end = new Pair(sc.nextInt(), sc.nextInt());
			answer = 2400;
			cust = new Pair[n];
			visit = new boolean[n];
			
			for(int i = 0; i < n; i++) {
				cust[i] = new Pair(sc.nextInt(), sc.nextInt());
			}
			
			dfs(start, 0, 0);
			
			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}
		
		System.out.print(sb);
	}
	
	static void dfs(Pair pre, int order, int route) {
		if(order == n) {
			int tmp = route + Math.abs(pre.x - end.x) + Math.abs(pre.y - end.y);
			answer = Math.min(answer,  tmp);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visit[i])
				continue;
			
			int tmp = Math.abs(pre.x - cust[i].x) + Math.abs(pre.y - cust[i].y);
			if(answer <= route + tmp)
				continue;
			
			visit[i] = true;
			dfs(cust[i], order + 1, route + tmp);
			visit[i] = false;
		}
		
	}
}