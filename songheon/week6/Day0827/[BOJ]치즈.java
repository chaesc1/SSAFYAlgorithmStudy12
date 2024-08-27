package day0827;

import java.io.*;
import java.util.*;

public class Main_B_2636_치즈_한송헌 {

	// 시간 증가
	// 0, 0부터 치즈가 아닌 곳으로 bfs -> 치즈 만나면 녹임
	// 치즈이고인 곳 기준으로 bfs 진행 -> 전체 탐색
	// 치즈 개수 cnt(bfs 횟수)
	// 치즈 개수가 0이면 (다 녹았으면) 정답 출력
	// 정답으로 치즈 개수 업데이트

	static int r, c;
	static int map[][];
	static boolean visit[][];
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { -1, 0, 1, 0 };
	static int answer;
	static int tanswer;
	static int time;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		map = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		while (true) {
			
//			치즈　개수　세기
			visit = new boolean[r][c];
			int part = 0;
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] == 1 && !visit[i][j]) {
						part++;
						tanswer += counting(new Pair(i, j));
					}
				}
			}
			
			if (part == 0) { //종료
				System.out.print(time + " " + answer);
				break;
			}
			answer = tanswer;
			
			tanswer = 0;
			time++;
			
			visit = new boolean[r][c];
			//녹이기
			melting(new Pair(0, 0));
		}

	}

	static boolean OOB(int x, int y) {
		return x < 0 || x >= r || y < 0 || y >= c;
	}
	
	static void melting(Pair pos) {

		Queue<Pair> q = new ArrayDeque<>();
		q.add(pos);
		visit[pos.x][pos.y] = true;

		while (!q.isEmpty()) {
			Pair cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];

				if (OOB(tx, ty))
					continue;
				if (visit[tx][ty])
					continue;
				if(map[tx][ty] == 1) {
					visit[tx][ty] = true;
					map[tx][ty] = 0;
					continue;
				}
				q.add(new Pair(tx, ty));
				visit[tx][ty] = true;
				
			}

		}
	}
	
	static int counting(Pair pos) {
		int cnt= 1;
		Queue<Pair> q = new ArrayDeque<>();
		q.add(pos);
		visit[pos.x][pos.y] = true;

		while (!q.isEmpty()) {
			Pair cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];

				if (OOB(tx, ty))
					continue;
				if (visit[tx][ty])
					continue;
				if (map[tx][ty] == 0)
					continue;
				q.add(new Pair(tx, ty));
				visit[tx][ty] = true;
				cnt++;
				
			}

		}
		return cnt;
	}
}
