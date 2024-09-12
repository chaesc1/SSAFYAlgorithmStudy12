import java.io.*;
import java.util.*;

public class Solution {

	static int tc;
	static int n;
	static int map[][];
	static boolean dessert[];
	static int answer;
	static int dx[] = {1, 1, -1, -1};
	static int dy[] = {1, -1, -1, 1}; // 이동 방향은 고정  : 시계방향 회전(우하, 좌하, 좌상, 우상)
	static int sx, sy;
	static int dir;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			n = sc.nextInt();
			map = new int[n][n];
			dessert = new boolean[101];
			answer = -1;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					sx = i; sy = j; dir = 0;
					dfs(i, j, 0, 1);
				}
			}
			sb.append(answer).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static boolean OOB(int x, int y) {
		if(x < 0 || x >= n || y < 0 || y >= n) return true;
		return false;
	}
	
	static void dfs(int x, int y, int dir, int len) {
		if(OOB(x, y)) return;
		if(dessert[map[x][y]]) return;
		if(dir == 4) return;
		
		if(x == sx+1 && y == sy-1) {
			answer = Math.max(answer, len);
			return;
		}
		
		
		dessert[map[x][y]] = true;
		dfs(x + dx[dir], y + dy[dir], dir, len + 1);
		dfs(x + dx[dir], y + dy[dir], dir + 1, len + 1);
		dessert[map[x][y]] = false;
	}
}