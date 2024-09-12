import java.util.*;
import java.io.*;

public class Main {
	// 벽 없는 경우, 부술 벽 고르기
	// 레벨별 BFS
	// (0,0) ~ (n-1, m-1) 이동
	// 시작 노드부터 거리 1 설정
	// 큐에 시작 노드 삽입
	// 방문 체크
	// 큐가 빌 때까지 반복
	// 현재 큐 사이즈만큼 반복
	// 거리 증가
	// 사방 탐색하면서 조건 만족하면 큐에 삽입
	// 경계 벗어나지 않음
	// 방문되지 않음
	// 맵 상의 0인 경우
	// 도착 노드인 경우 브레이크
	// 해당 거리 정보 출력

	// 시작점에서의 bfs 실행 -> 거리 저장
	// 도착점에서의 bfs 실행 -> 거리 저장
	// bfs 진행하면서 1인 경우 거리만 저장하고 탐색 중단
	// 도착점 : 두 거리 정보 더함
	// 벽을 부수고 진행하는 경우 : 해당 벽의 거리 두 값을 더함

	static int n, m;
	static int map[][];
	static int dist[][];
	static int edist[][];
	static boolean visit[][];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	static int answer;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int INF = 1000001;
		answer = INF;
		map = new int[n][m];
		dist = new int[n][m];
		edist = new int[n][m];
		for (int i = 0; i < n; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = tmp.charAt(j) - '0';
				dist[i][j] = INF; edist[i][j] = INF;
			}
		}

		// 시작점에서의 bfs
		bfs(0, 0, dist);
		
		// 도착점에서의 bfs
		bfs(n - 1, m - 1, edist); //벽 부수고 갈 수 있는지 탐색
		
		answer = edist[0][0];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					answer = Math.min(answer, dist[i][j] + edist[i][j]);
				}
			}
		}
		
		if(answer >= INF)
			answer = -1;
		else
			answer += 1;
		System.out.println(answer);
	}

	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}

	static void bfs(int x, int y, int dist[][]) {
		visit = new boolean[n][m];
		int d = 0;
		
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(x, y));
		visit[x][y] = true;
		dist[x][y] = 0;
		
		while(!q.isEmpty()) {
			 int size = q.size();
			 d++;
			 
			 while(size > 0) {
				 Pair cur = q.poll();
				 for(int i = 0; i < 4; i++) {
					 int tx = cur.x + dx[i];
					 int ty = cur.y + dy[i];
					 
					 if(OOB(tx, ty)) continue;
					 if(visit[tx][ty]) continue;
					 if(map[tx][ty] == 1) {
						 dist[tx][ty] = d;
						 visit[tx][ty] = true;
					 }
					 else if(map[tx][ty] == 0) {
						dist[tx][ty] = d;
						visit[tx][ty] = true;
						q.add(new Pair(tx, ty));
					 }
				 }
				 size--;
			 }
			
			
			
		}
	}
}