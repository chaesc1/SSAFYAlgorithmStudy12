package week3.BOJ음식물피하기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1743 {
	static int N, M, K;
	static boolean[][] visited;
	static int[][] food;
	static int result;
	
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	

	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		food = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			food[r][c] = 1;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(food[i][j] == 1 && !visited[i][j]) bfs(i, j);
			}
		}
		System.out.println(result);
	}
	
	static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		visited[x][y] = true;
		q.offer(new Point(x, y));
		
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || food[nx][ny] != 1) continue;
				
				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
				cnt++;
			}
		}
		result = Math.max(result, cnt);
	}
}