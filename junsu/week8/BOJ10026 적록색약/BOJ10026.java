//BOJ 10026 적록색약 골드5
import java.io.*;
import java.util.*;

public class BOJ10026 {
	static int N, ans1, ans2;
	static char[][] area;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		area = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
//			System.out.println(temp);
			for(int j = 0; j < N; j++) {
				area[i][j] = temp.charAt(j);
			}
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(area[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		ans1 = ans2 = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs1(i, j); //적록색약 아닌 사람이 봤을때
					ans1++;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs2(i, j); //적록색약인 사람이 봤을때
					ans2++;
				}
			}
		}
		
//		System.out.println(Arrays.deepToString(visited));
		System.out.println(ans1+" "+ans2);
	}
	static void bfs1(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int curX = xy[0];
			int curY = xy[1];
			
			for(int dic = 0; dic < 4; dic++) {
				int nx = curX + dx[dic];
				int ny = curY + dy[dic];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(area[x][y] == area[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
	static void bfs2(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int curX = xy[0];
			int curY = xy[1];
			
			for(int dic = 0; dic < 4; dic++) {
				int nx = curX + dx[dic];
				int ny = curY + dy[dic];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(area[x][y] == area[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				} // 적록색약인 경우 빨강과 초록을 같이 보는 조건문 
				else if(area[x][y] == 'R' && area[nx][ny] == 'G') {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				} else if(area[x][y] == 'G' && area[nx][ny] == 'R') {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
}
