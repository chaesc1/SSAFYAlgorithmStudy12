import java.util.*;
import java.io.*;

public class BOJ1743 {
	static boolean visit[][];
	static int map[][];
	static int dirX[] = {0, 0, -1, 1};
	static int dirY[] = {-1, 1, 0, 0};
	static int N, M;
	static int nowX, nowY;
	static int x, y;
	static int count = 1;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken()) + 1;
		int K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());

			map[y][x] = 1;
		}

		// 1을 만나면 탐색 시작
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {

				count = 0;

				if(visit[i][j] == false && map[i][j] > 0) {
					DFS(j, i);
					max = Math.max(max, count);
				}
			}
		}

		System.out.println(max);
	}

	static void DFS(int x, int y) {
		visit[y][x] = true;
		count++;

		for(int i=0; i<4; i++) {
			nowX = x + dirX[i];
			nowY = y + dirY[i];

			if(Range_check() && visit[nowY][nowX] == false && map[nowY][nowX] > 0) {
				DFS(nowX, nowY);
			}
		}
	}

	static boolean Range_check() {
		return (nowX >= 0 && nowY >= 0 && nowX < M && nowY < N);
	} 
} 