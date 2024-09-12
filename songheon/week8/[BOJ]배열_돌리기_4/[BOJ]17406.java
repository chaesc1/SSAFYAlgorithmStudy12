import java.util.*;
import java.io.*;

public class Main {
	static int m, n, k;
	static int map[][];
	static int buffer[][], buffer2[][];
	static int r, c, s;
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, 1, 0, -1};
	static int command[][];
	static int order[];
	static boolean visit[];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		map = new int[n][m];
		buffer = new int[n][m];
		buffer2 = new int[n][m];
		command = new int[k][3];
		order = new int[k];
		visit = new boolean[k];
		answer = 5000;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < k; i++) {
			command[i][0] = sc.nextInt()-1;
			command[i][1] = sc.nextInt()-1;
			command[i][2] = sc.nextInt();
		}
		
		permu(0);
		
		System.out.println(answer);
	}
	
	static void permu(int cur) {
		if(cur == k) {
			boolean flag = true;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					buffer[i][j] = map[i][j];
				}
			}
			for(int i = 0; i < k; i++) {
				
				r = command[order[i]][0];
				c = command[order[i]][1];
				s = command[order[i]][2];
				
				if(flag)
					rotate(buffer, buffer2, r, c, s);
				else
					rotate(buffer2, buffer, r, c, s);
				
				flag = !flag;
			}
			if(flag)
				answer = Math.min(answer, getAns(buffer));
			else
				answer = Math.min(answer, getAns(buffer2));
		}
		
		for(int i = 0; i < k; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			order[cur] = i;
			permu(cur + 1);
			visit[i] = false;
		}
	}
	
	static void rotate(int[][] map, int[][] buffer, int r, int c, int s) {
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(i < r - s || i > r + s || 
						j < c - s || j > c + s) {
					buffer[i][j] = map[i][j];
				}
			}
		}

		int gap = s;
		while(gap > 0) {
			rotatePart(map, buffer, r-gap, c-gap, r+gap, c+gap);
			gap--;
		}
		buffer[r][c] = map[r][c];
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				System.out.print(buffer[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
	
	static void rotatePart(int[][] map, int[][] buffer, int sx, int sy, int ex, int ey) {
		
		int tmp = map[sx][sy];
		int x = sx;
		int y = sy;
		int dir = 0;
		while(true) {
			
			int tx = x + dx[dir];
			int ty = y + dy[dir];
			
			if(tx < sx || tx > ex || ty < sy || ty > ey) {
				dir = (dir + 1) % 4;
				 tx = x + dx[dir];
				 ty = y + dy[dir];
			}
			
			if(tx == sx && ty == sy) {
				buffer[x][y] = tmp;
				break;
			}
			
			buffer[x][y] = map[tx][ty];
			x = tx;
			y = ty;
		}
	}
	
	static int getAns(int[][] map) {
		int answer = 5000;
		for(int i = 0; i < n; i++) {
			int tmp = 0;
			for(int j = 0; j < m; j++) {
				tmp += map[i][j];
			}
			answer = Math.min(answer, tmp);
		}
		return answer;
	}
}