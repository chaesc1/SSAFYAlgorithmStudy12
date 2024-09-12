import java.io.*;
import java.util.*;

public class Solution {
	static int tc;
	static int n, k;
	static int map[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int answer = 0;
	static ArrayList<Pair> poles;
	
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
			sb.append("#").append(t).append(" ");
			answer = 0;
			poles = new ArrayList<>();
			
			n = sc.nextInt();
			k = sc.nextInt();
			map = new int[n][n];
			
			int mx = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					mx = Math.max(mx, map[i][j]);
				}
			}
			
//			//정상 기록
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(map[i][j] == mx)
						poles.add(new Pair(i, j));
				}
			}
			
			for(int i = 0; i < poles.size(); i++) {
				Pair cur = poles.get(i);
				boolean visit[][] = new boolean[n][n];
				visit[cur.x][cur.y] = true;
				dfs(cur, 1, false, visit); //현재 위치, 등산로 길이, 공사 여부, visit 배열
			}
			
			sb.append(answer).append('\n');
		}

		System.out.println(sb);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	
	static void dfs(Pair pos, int len, boolean cut, boolean[][] visit) {
		answer = Math.max(len, answer);
		
		
		int x = pos.x;
		int y = pos.y;
		int h = map[x][y];
//		System.out.println("curxy : " + x + " " + y);
//		System.out.println("len : " + len);
		for(int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(OOB(tx, ty)) continue;
			if(visit[tx][ty]) continue;
			if(map[tx][ty] < h) {
				visit[tx][ty] = true;
				dfs(new Pair(tx, ty), len + 1, cut, visit);
				visit[tx][ty] = false;
			}
			else {
				if(cut) continue;
				int tmp = map[tx][ty];
				
				if(tmp - (map[x][y]-1) > k) continue;
				map[tx][ty] = map[x][y]-1;
				
//				System.out.println("x, y : " + tx + " "+  ty);
//				System.out.println("height : " + map[tx][ty]);
				visit[tx][ty] = true;
				dfs(new Pair(tx, ty), len + 1, true, visit);
				visit[tx][ty] = false;
				map[tx][ty] = tmp;
			}
		}
		
		
		
	}
	
}