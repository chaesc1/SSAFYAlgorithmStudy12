// BOJ1012 유기농 배추 실버2
// 핵심은  visited 대신 map을 활용하여 0이면 방문한 곳, 1이면 방문해야할 곳으로 구분했다.
import java.io.*;
import java.util.*;

public class BOJ1012 {
	static int N, M, K, answer;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	static int[][] map;
//	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			answer = 0;
			map = new int[N][M];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[b][a] = 1;
			}
			
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < M; j++) {
//					System.out.print(map[i][j] +" ");
//				}
//				System.out.println();
//			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 1) {
						bfs(i, j);
						answer++;
					}
				}
			}
			
			
			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		map[x][y] = 0; //처음 큐에 넣는 값도 확인 했으니 0으로 바꿈
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int curX = arr[0];
			int curY = arr[1];
			
			// visited 대신 map을 활용
//			map[curX][curY] = 0; // 여기서 하면 이미 중복된걸 많이 넣기 때문에 메모리 초과
			
			for(int dic = 0; dic < 4; dic++) {
				int nx = curX + dx[dic];
				int ny = curY + dy[dic];
				// visited 대신 map[nx][ny] 가 0이면 방문 못하는 곳으로한다.
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0) continue;
				map[nx][ny] = 0;  //여기서 체크해주면 메모리 초과를 해결 가능
				q.add(new int[] {nx, ny});
			}
		}
	}
}




