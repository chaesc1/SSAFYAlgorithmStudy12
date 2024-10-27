//BOJ1261 알고스팟, 골드4
//다익스트라 알고리즘, 벽 부수고 이동하기랑 비슷
//최단경로가 아닌 벽 부순 갯수 기준이라서 우선순위 큐 써야함
import java.io.*;
import java.util.*;

public class BOJ1261 {
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int cost;
		
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	static int N, M, ans;
	static int[][] map;
	static int INF = 987654321;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
//			st = new StringTokenizer(br.readLine());
//			String temp = st.nextToken();
			String temp = br.readLine();
			for(int j = 1; j <= M; j++) {
				map[i][j] = temp.charAt(j-1) - '0';
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		ans = dijkstra();
		
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 1, 0)); // x, y, 벽을 부순 횟수
        boolean[][] visit  = new boolean[N+1][M+1];
        visit[1][1] =true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curCost = cur.cost;

            if (curX == N && curY == M) {
            	return curCost;
            }

            for (int dic = 0; dic < 4; dic++) {
                int nextX = curX + dx[dic];
                int nextY = curY + dy[dic];
                if (nextX <= 0 || nextY <= 0 || nextX > N || nextY > M) continue;
                
                if(!visit[nextX][nextY] && map[nextX][nextY] == 0) {
                	visit[nextX][nextY] = true;
                	pq.add(new Node(nextX, nextY, curCost));
                }
                if(!visit[nextX][nextY] && map[nextX][nextY] == 1) {
                	visit[nextX][nextY] = true;
                	pq.add(new Node(nextX, nextY, curCost+1));
                }
            }
        }
        return 0;
    }
}
