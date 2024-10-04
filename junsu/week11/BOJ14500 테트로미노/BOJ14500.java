//BOJ14500 테트로미노, 골드4
//처음에는 각 테트로미노 모양들을 2차원 배열에 담아서 해야하는 줄 알았으나 아니었음
//dfs로 길이가 4가 됐을때 합을 구하면 되는거였다.
// ㅗ, ㅜ, ㅏ, ㅓ 모양들은 dfs로 구현할 수 없었다. 그렇기에 따로 처리해줘야함
import java.io.*;
import java.util.*;

public class BOJ14500 {
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
			}
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// 모든 좌표에서 DFS 탐색을 시작
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(0, i, j, map[i][j]);
				visited[i][j] = false;
				
				checkExtraShape(i , j);
			}
		}
		
	
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void dfs(int cnt, int curX, int curY, int sum) {
		if(cnt == 3) {
			ans = Math.max(sum, ans);
			return;
		}
		
		for(int dic = 0; dic < 4; dic++) {
			int nextX = curX + dx[dic];
			int nextY = curY + dy[dic];
			
			// 1. 종이 범위를 벗어나는지 먼저 체크해야한다.
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
			// 2. 종이 범위 내부일 때는 방문했던 곳인지를 체크해줘야한다.
			if(visited[nextX][nextY]) continue;
			
			visited[nextX][nextY] = true;
			dfs(cnt+1, nextX, nextY, sum + map[nextX][nextY]);
			//다녀온 곳은 다른 테트로미노가 갈 수 있게 false 해줘야한다.
			visited[nextX][nextY] = false; 
		}
		
	}
	static void checkExtraShape(int x, int y) {
		// ㅗ, ㅜ, ㅏ, ㅓ 모양 체크
		// ㅗ 모양
		if(x >= 0 && x + 1 < N && y-1 >= 0 && y+1 < M) {
			int sum = map[x][y] + map[x+1][y-1] + map[x+1][y] + map[x+1][y+1];
			ans = Math.max(ans, sum);
		}
		// ㅜ 모양
		if(x >= 0 && x+1 < N && y >= 0 && y+2 < M) {
			int sum = map[x][y] + map[x][y+1] + map[x][y+2] + map[x+1][y+1];
			ans = Math.max(ans, sum);
		}
		// ㅏ 모양
		if(x >= 0 && x+2 < N && y >= 0 && y+1 < M) {
			int sum = map[x][y] + map[x+1][y] + map[x+2][y] + map[x+1][y+1];
			ans = Math.max(ans, sum);
		}
		// ㅓ 모양
		if(x >= 0 && x+2 < N && y-1 >= 0 && y < M) {
			int sum = map[x][y] + map[x+1][y] + map[x+2][y] + map[x+1][y-1];
			ans = Math.max(ans, sum);
		}
	}
}


