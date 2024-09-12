package day0827;

import java.util.*;
import java.io.*;

//110,800 kb	616 ms

public class Solution_7733_치즈도둑_한송헌 {
	//인접 리스트를 기준으로 bfs/dfs 진행
	//현재 날짜보다 큰 숫자이거나, 방문 안 한 곳만 가능
	//map의 모든 부분이 현재 날자보다 작은 숫자이거나 방문된 경우 종료
	//총 bfs를 실행한 횟수 = 덩어리 개수
	
	static int tc;
	static int n;
	static int cheese[][];
	static int answer;
	static boolean visit[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	static class Pair{
		int x, y;

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
			answer = 1;
			
			n = sc.nextInt();
			int limit = 0;
			cheese = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					cheese[i][j] = sc.nextInt();
					limit = Math.max(limit, cheese[i][j]);
				}
			}
			
			for(int k = 1; k < limit; k++) { //각 날짜 별로 덩어리 세기
				visit = new boolean[n][n];
				
				int tmp = 0;
				//bfs
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(cheese[i][j] > k && !visit[i][j]) {
							tmp++;
							bfs(new Pair(i, j), k);
						}
					}
				}
				
				answer = Math.max(answer, tmp);
				
			}

			
			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}
		System.out.print(sb);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	static void bfs(Pair pos, int day) {
		
		Queue<Pair> q = new ArrayDeque<>();
		q.add(pos);
		visit[pos.x][pos.y] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				
				if(OOB(tx, ty)) continue;
				if(visit[tx][ty]) continue;
				if(cheese[tx][ty] <= day) continue;
				
				visit[tx][ty] = true;
				q.add(new Pair(tx, ty));
			}
			
		}
		
	}
}
