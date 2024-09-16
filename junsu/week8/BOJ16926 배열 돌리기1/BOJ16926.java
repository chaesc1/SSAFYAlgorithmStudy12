//BOJ16926 배열 돌리기1 골드5
import java.io.*;
import java.util.*;

public class BOJ16926 {
	static int N, M, R;
	static int min;
	static int[][] map;
	
	static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Math.min(N, M);
		

		
		for(int i = 0; i < R; i++) {
			rotate();
		}
	
		for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			System.out.print(map[i][j] + " ");
    		}
    		System.out.println();
    	}
	}
	static void rotate() {
		for(int t = 0; t < min/2; t++) { // 회전시킬 그룹의 갯수 구하기
			int x = t;
			int y = t;
			
			int temp = map[x][y];
			
			int idx = 0;
			while(idx < 4) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				
				// 범위 안이라면 N-t가 끝점, t가 시작점
				if(nx < N-t && ny < M-t && nx >= t && ny >= t) {
					map[x][y] = map[nx][ny];
					x = nx;
					y = ny;
				}
				
				// 범위를 벗어났다면 다음방향으로 간다.
				else {
					idx++;
				}
				
			}
			
			map[t+1][t] = temp;
			
		}
		
		
	}
}
