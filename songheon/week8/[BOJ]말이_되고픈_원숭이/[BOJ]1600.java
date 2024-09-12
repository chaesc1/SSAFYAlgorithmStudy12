import java.io.*;
import java.util.*;

public class Main {
	
	static int k;
	static int w, h;
	static int map[][];
	static boolean visit[][][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int tdx[] = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int tdy[] = {-2, -1, 1, 2, -2, -1, 1, 2};
	static int answer = 0;
	
	static class Tuple{
		int x, y;
		int knite;

		public Tuple (int x, int y, int knite) {
			super();
			this.x = x;
			this.y = y;
			this.knite = knite;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		k = sc.nextInt();
		w = sc.nextInt();
		h = sc.nextInt();
		map = new int[h][w];
		visit = new boolean[h][w][k+1];
		
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//bfs 진행
		bfs();
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= h || y < 0 || y >= w;
	}
	
	static void bfs() {
		Queue<Tuple> q = new ArrayDeque<>();
		q.add(new Tuple(0, 0, 0));
		visit[0][0][0] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			
			int sz = q.size();
//			System.out.println(sz);
			while(sz > 0) {
				Tuple cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				int tmpk = cur.knite;
				
//				System.out.println(x + " " + y);
				
				
				if(x == h-1 && y == w-1) {
					System.out.println(cnt);
					return;
				}
				for(int i = 0; i < 4; i++) {
					int tx = x + dx[i];
					int ty = y + dy[i];
					if(OOB(tx, ty)) continue;
					if(map[tx][ty] == 1) continue;
					if(tmpk > k) break;
					if(visit[tx][ty][tmpk]) continue;
					visit[tx][ty][tmpk] = true;
					q.add(new Tuple(tx, ty, tmpk));
				}
				
				for(int i = 0; i < 8; i++) {
					int tx = x + tdx[i];
					int ty = y + tdy[i];
					if(OOB(tx, ty)) continue;
					if(map[tx][ty] == 1) continue;
					if(tmpk+1 > k) break;
					if(visit[tx][ty][tmpk+1]) continue;
					visit[tx][ty][tmpk+1] = true;
					q.add(new Tuple(tx, ty, tmpk+1));
				}
				sz--;
			}
			cnt++;
			
		}
		
		System.out.println(-1);
		return;
		
		
		
	}
}