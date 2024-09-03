import java.io.*;
import java.util.*;

public class Main {
	//2차원 다익스트라 : bfs + priority queue
	//최소 비용으로 0, 0 -> n-1, n-1
	//테스트케이스 주의
	//비용 저장하는 이차원 배열
	//방문여부 저장하는 이차원 배열
	//큐에 저장하는 데 필요한 노드 생성 : 해당 셀의 위치 정보, 비용 정보
	//시작 노드 큐에 삽입
	//큐가 빌때까지 BFS 실행
		//큐에서 빼냄
		//방문된 노드면 패스
		//도착점이면 브레이크
		//큐 방문 체크
		//큐와 인접한 노드 탐색하면서 조건 만족하면 큐에 삽입
			//경계 벗어나지 않는 노드
			//방문 안된 노드
			//축적 거리 갱신 가능한 노드
	//마지막 노드에 저장된 축적 거리 출력
	
	static int tc;
	static int n;
	static int map[][];
	static boolean visit[][];
	static int minCost[][];
	static int answer = 0;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	static class Node{
		int x, y;
		int weight;
		public Node(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(true) {
			n = sc.nextInt();
			if(n == 0) break;
			
			answer = 0;
			sb.append("Problem ").append(++tc).append(": ");
			map = new int[n][n];
			visit = new boolean[n][n];
			minCost = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					minCost[i][j] = Integer.MAX_VALUE;
				}
			}
			
			//bfs 탐색
			PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2)->e1.weight-e2.weight);
			
			minCost[0][0] = map[0][0];
			pq.add(new Node(0, 0, minCost[0][0]));
			
			while(!pq.isEmpty()) {
				Node stop = pq.poll();
				int x = stop.x;
				int y = stop.y;
				int w = stop.weight;
				
				if(visit[x][y]) continue;
				if(x == n-1 && y == n-1) break;
				visit[x][y] = true;
				
				for(int i = 0; i < 4; i++) {
					int tx = x + dx[i];
					int ty = y + dy[i];
					
					if(OOB(tx, ty)) continue;
					if(visit[tx][ty]) continue;
					if(minCost[tx][ty] > w + map[tx][ty]) {
						minCost[tx][ty] = w + map[tx][ty];
						pq.add(new Node(tx, ty, minCost[tx][ty]));
					}
				}
				
			}
			answer = minCost[n-1][n-1];
			
			
			sb.append(answer).append('\n');
		}
		System.out.print(sb);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	
}