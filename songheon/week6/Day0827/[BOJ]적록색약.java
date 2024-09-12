package day0827;

import java.util.*;
import java.io.*;

//12796kb	92ms

public class Main_B_10026_적록색약_한송헌 {
	//적록 색약이 아닌 경우 rgb의 각 요소들의 구역 구하기 위한 bfs 진행
	//파라메터로 현재 지정된 색 전달
	//적록 색약인 경우 r, g 함께 b는 따로 구역 구하기 위해 bfs 진행
	//마찬가지로 파라메터에 현재 지정된 색 전달

	static int n;
	static char map[][];
	static boolean visit[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int ans1, ans2;
	static HashMap<Character, Integer> color;

	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visit = new boolean[n][n];
		color = new HashMap<>();


		for(int i = 0; i < n; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < n; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}

		//		System.out.println("end");

		//적록색약 x
		color.put('R', 0);
		color.put('G', 1);
		color.put('B', 2);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visit[i][j] ) {
					ans1++;
					bfs(new Pair(i, j), color.get(map[i][j]));
				}
			}
		}

//		System.out.println("end");

		//적록색약 
		color.put('G', 0);
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j] == false) {
					ans2++;
					bfs(new Pair(i, j), color.get(map[i][j]));
				}
			}
		}


		System.out.print(ans1 + " " + ans2);

	}

	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}

	static void bfs(Pair pos, int c) {

		Queue<Pair> q = new ArrayDeque<>();
		q.add(pos);
		visit[pos.x][pos.y] = true;

		while(!q.isEmpty()) {
			Pair tmp = q.poll();
			int curx = tmp.x;
			int cury = tmp.y;

			for(int i = 0; i < 4; i++) {
				int tx = curx + dx[i];
				int ty = cury + dy[i];

				if(OOB(tx, ty)) continue;					//경계 
				if(color.get(map[tx][ty]) != c) continue;	//원하는 색아님
				if(visit[tx][ty]) continue;
				
				q.add(new Pair(tx, ty));
				visit[tx][ty] = true;
			}
		}


	}
}
