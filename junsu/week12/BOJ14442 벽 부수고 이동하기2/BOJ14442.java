//BOJ14442 벽 부수고 이동하기2, 골드3
//기존 벽 부수고 이동하기에서 벽을 부술 수 있는 수가 1개에서 K개로 변경되었다.
//check 배열을 3차원 배열로 받아서 [K+1][][] 만큼 해준다.
//각 벽을 깨순 경우에 따라 거리를 계산해준다.
//틀린 이유 : 이동할때마다 그곳이 처음 방문했는지(0인지) 체크하고 경로탐색해야함
import java.io.*;
import java.util.*;

public class BOJ14442 {
	static int N, M, K, ans;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		ans = Integer.MAX_VALUE;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int j = 1; j <= M; j++) {
				char ch = temp.charAt(j-1);
				String tmp = Character.toString(ch);
				map[i][j] = Integer.parseInt(tmp);
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		ans = bfs();
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		int[][][] check = new int[K+1][N+1][M+1];
		q.add(new int[] {0, 1, 1}); //시작 지점
		check[0][1][1] = 1; //시작 경로
		
		while(!q.isEmpty()) {
			//큐 값 받고
			int[] cur = q.poll();
			int w = cur[0]; //벽 몇번 뿌서졌는지
			int curX = cur[1];
			int curY = cur[2];
			
			if(curX == N && curY == M) return check[w][curX][curY];
			
			//for문 방향체크
			for(int dic = 0; dic < 4; dic++) {
				int nextX = curX + dx[dic];
				int nextY = curY + dy[dic];
				
				if(nextX <= 0 || nextY <= 0 || nextX > N || nextY > M) continue;
				
				//다음 노드가 벽이 아닐 때(0)
				if(map[nextX][nextY] == 0) {
					if(check[w][nextX][nextY] == 0) { //이걸 빼먹고 안해줬음
						q.add(new int[] {w, nextX, nextY});
						check[w][nextX][nextY] = check[w][curX][curY] + 1;
						if(nextX == N && nextY == M) {
							return check[w][nextX][nextY];
						}
					}
				}
				else if(map[nextX][nextY] == 1) { //벽 만났을 때
					if(w < K) { //벽을 더 깰 수 있을 때
						if(check[w+1][nextX][nextY] == 0) { //벽 부술 때도 이거 해줘야함 - 이걸 빼먹고 안해줬음
							q.add(new int[] {w+1, nextX, nextY});
							check[w+1][nextX][nextY] = check[w][curX][curY] + 1;
						}
					}else { //더 이상 벽을 못 깰 때
						continue;
					}
				}
			}
		}
		//q가 다 비었는데 N, M 위치에 도달 못했을 경우
		return -1;
	}
}
