package day0902;

import java.io.*;
import java.util.*;

public class Main_B_14502_연구소_한송헌 {

	static int n, m;
	static int map[][];
	static int tmp[][];
	static boolean visit[][];
	static int answer;
	static Pair output[];
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {-1, 0, 1, 0};
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		output = new Pair[3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		combi(0, 0);

		System.out.println(answer);

	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m; 
	}
	
	static void counting() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(visit[i][j]) continue;
				if(tmp[i][j] != 2) continue;
				bfs(i, j);
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tmp[i][j] == 0) cnt++;
			}
		}
		answer = Math.max(cnt, answer);
	}
	
	static void bfs(int x, int y) {
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(x, y));
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				
				if(OOB(tx,ty)) continue;
				if(visit[tx][ty]) continue;
				if(tmp[tx][ty] == 0) {
					tmp[tx][ty] = 2;
					q.add(new Pair(tx, ty));
					visit[tx][ty] = true;
				}
			}
		}
	}
	
	static void combi(int cnt, int start) {
		// 기저 조건
		if (cnt == 3) {
			visit = new boolean[n][m];
			tmp = new int[n][m];			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			for(int i = 0; i < 3; i++) {
				Pair cur = output[i];
				tmp[cur.x][cur.y] = 1;
			}
			counting();
			return;
		}

		for (int idx = start; idx < n * m; idx++) {
			int r = idx / m;
			int c = idx % m;
			
			if(map[r][c] != 0) continue;
			
			output[cnt] = new Pair(r, c);

			combi(cnt + 1, idx + 1);
		}
	}
}
