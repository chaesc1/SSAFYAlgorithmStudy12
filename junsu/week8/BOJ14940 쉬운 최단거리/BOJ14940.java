//BOJ ���� �ִܰŸ� �ǹ�1
//BFS�� ������ �� �־ ���Ҵ�
//�ִ� �Ÿ��� ���ϴ� �������� ���ͽ�Ʈ�� �˰��򿡼� �� �ڵ�� ����ϴ� ���� ������.
// ��԰� �� �� �ִ� �� �߿� ������ �� ���� ��ġ�� -1�� �����༭ Ʋ�ȴ�..

import java.io.*;
import java.util.*;

public class BOJ14940 {
	static int n, m;
	static int[][] map, res;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1}; //�� �� �� ��
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
		
		// ����� ���� ���߱�
		for(int i = 0; i < n; i++) {
			Arrays.fill(res[i], Integer.MAX_VALUE);
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int number = Integer.parseInt(st.nextToken());
				map[i][j] = number;
				// ��ŸƮ ���� ã�Ƽ� �����ϱ�
				if(number == 2) {
					startX = i;
					startY = j;
					res[i][j] = 0;  //ó������ �Ÿ���� ��ġ�� 0 ����
				} 
				// ���� �� �� ���� ���� ��ġ ���Ƶα� 
				else if(number == 0) {
					res[i][j] = 0;
					visited[i][j] = true;
				}
			}
		}
		// �� �ߵ����� Ȯ�ο� for��
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
				
				// ���ͽ�Ʈ�� Ǯ���� ����� �ڵ�
				if(res[nx][ny] > res[x][y] + 1) {
					res[nx][ny] = res[x][y] + 1;
					q.add(new int[] {nx, ny});
				}
			}
			
		}
	}
}
