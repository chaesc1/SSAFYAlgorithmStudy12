// BOJ1012 ����� ���� �ǹ�2
// �ٽ���  visited ��� map�� Ȱ���Ͽ� 0�̸� �湮�� ��, 1�̸� �湮�ؾ��� ������ �����ߴ�.
import java.io.*;
import java.util.*;

public class BOJ1012 {
	static int N, M, K, answer;
	static int[] dx = {0, 1, 0, -1}; //�� �� �� ��
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
		map[x][y] = 0; //ó�� ť�� �ִ� ���� Ȯ�� ������ 0���� �ٲ�
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int curX = arr[0];
			int curY = arr[1];
			
			// visited ��� map�� Ȱ��
//			map[curX][curY] = 0; // ���⼭ �ϸ� �̹� �ߺ��Ȱ� ���� �ֱ� ������ �޸� �ʰ�
			
			for(int dic = 0; dic < 4; dic++) {
				int nx = curX + dx[dic];
				int ny = curY + dy[dic];
				// visited ��� map[nx][ny] �� 0�̸� �湮 ���ϴ� �������Ѵ�.
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0) continue;
				map[nx][ny] = 0;  //���⼭ üũ���ָ� �޸� �ʰ��� �ذ� ����
				q.add(new int[] {nx, ny});
			}
		}
	}
}




