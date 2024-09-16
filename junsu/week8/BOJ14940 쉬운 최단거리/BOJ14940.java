//BOJ 쉬운 최단거리 실버1
//BFS를 연습할 수 있어서 좋았다
//최단 거리를 구하는 과정에서 다익스트라 알고리즘에서 쓴 코드랑 비슷하단 것을 느꼈다.
// 까먹고 갈 수 있는 땅 중에 도달할 수 없는 위치를 -1로 안해줘서 틀렸다..

import java.io.*;
import java.util.*;

public class BOJ14940 {
	static int n, m;
	static int[][] map, res;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		res = new int[n][m];
		visited = new boolean[n][m];
		int startX = 0;
		int startY = 0;
		
		// 입출력 조건 맞추기
		for(int i = 0; i < n; i++) {
			Arrays.fill(res[i], Integer.MAX_VALUE);
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int number = Integer.parseInt(st.nextToken());
				map[i][j] = number;
				// 스타트 지점 찾아서 저장하기
				if(number == 2) {
					startX = i;
					startY = j;
					res[i][j] = 0;  //처음에는 거리계산 위치는 0 넣음
				} 
				// 원래 갈 수 없는 땅인 위치 막아두기 
				else if(number == 0) {
					res[i][j] = 0;
					visited[i][j] = true;
				}
			}
		}
		// 값 잘들어갔는지 확인용 for문
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				System.out.print(res[i][j] +" ");
//			}
//			System.out.println();
//		}
		
		bfs(startX, startY);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(res[i][j] == Integer.MAX_VALUE) {
					System.out.print(-1+" ");
				}else {
					System.out.print(res[i][j] +" ");
				}
				
			}
			System.out.println();
		}
	}
	static void bfs(int startX, int startY) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {startX, startY});
		
		while(!q.isEmpty()) {
			int[] location = q.poll();
			int x = location[0];
			int y = location[1];
			
			if(visited[x][y]) continue;
			visited[x][y] = true;

			for(int dic = 0; dic < 4; dic++) {
				int nx = x + dx[dic];
				int ny = y + dy[dic];
				if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
//				System.out.println(nx + " "+ ny);
//				System.out.println(res[nx][ny] +" "+(res[x][y] + 1));
				
				// 다익스트라 풀때랑 비슷한 코드
				if(res[nx][ny] > res[x][y] + 1) {
					res[nx][ny] = res[x][y] + 1;
					q.add(new int[] {nx, ny});
				}
			}
			
		}
	}
}
