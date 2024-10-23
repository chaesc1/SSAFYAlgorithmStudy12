//BOJ2206 벽 부수고 이동하기, 골드3
//BFS 문제
// 3차원 배열로 벽 안부수고 지나가는 방문노드 경로, 벽 부수고 지나가는 방문노드 경로를 
// 기록하며 풀었다. 기존의 2차원 배열로 하려면 벽 부순 케이스, 안 부순 케이스 나눠서 풀어야함
// 그래서 차라리 3차원 배열을 활용
import java.io.*;
import java.util.*;

public class BOJ2206 {
	static int N, M, ans;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0}; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		
		map = new int[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int j = 1; j <= M; j++) {
				char ch = temp.charAt(j-1);
				String tmp = Character.toString(ch);
				int n = Integer.parseInt(tmp);
				map[i][j] = n;
			}
		}

//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		ans = BFS();
		

		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static int BFS() {
		// [0, n, m] : 벽 안부수고 지나가는 방문노드 경로
		// [1, n, m] : 벽 부수고 지나가는 방문노드 경로
		
		int[][][] check = new int[2][N+1][M+1];
		Queue<int[]> p = new LinkedList<>();
		
		//시작 위치 (1, 1)로 지정
		p.add(new int[] {0, 1, 1});
		check[0][1][1] = 1;
		
		while(!p.isEmpty()) {
			int[] cur = p.poll();
			int wall = cur[0];
			int curX = cur[1];
			int curY = cur[2];
			
			//97퍼에서 틀리는 예외케이스 처리
			if(curX == N && curY == M) {
				return check[wall][curX][curY];
			}
			
			for(int dic = 0; dic < 4; dic++) {
				int nextX = curX + dx[dic];
				int nextY = curY + dy[dic];
				if(nextX <= 0 || nextY <= 0 || nextX > N || nextY > M) continue;

				//다음 노드가 벽이 아닐 때(0)
				// 다음 노드의 wall이 0이라면 0인 자리에 방문체크
				// 다음 노드의 wall이 1이면 1인 자리에 방문체크
				if(map[nextX][nextY] == 0) {
					if(check[wall][nextX][nextY] == 0) {
						p.add(new int[] {wall, nextX, nextY});
						check[wall][nextX][nextY] = check[wall][curX][curY] + 1;
						if(nextX == N && nextY == M) {
							return check[wall][nextX][nextY];
						}
					}
				}
				else { 
					if(wall == 0) { //벽을 한 번도 뿌순적 없을때만 실행가능 즉 예외케이스다.
						if(check[1][nextX][nextY] == 0) {
							p.add(new int[] {1, nextX, nextY});
							check[1][nextX][nextY] = check[0][curX][curY] + 1;
							if(nextX == N && nextY == M) {
								return check[1][nextX][nextY];
							}
						}
					}
				}
			}
		}
		
		//q가 다 비었는데 N, M 위치에 도달 못했을 경우
		return -1;
	}
}
