import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int map[][];
	static boolean visit[][];
	static int num;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	static ArrayList<Edge> edges;
	static int parents[];
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static class Edge implements Comparable<Edge>{
		int start, end;
		int weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		visit = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//numbering
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1 && !visit[i][j]) {
					num++;
					bfs(i, j, num);
				}
			}
		}
		
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		edges = new ArrayList<>();
		//find edges
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] != 0) {
//					System.out.println("start" + i + " " + j);
					for(int d = 0; d < 4; d++) {
						findEdge(i, j, d, map[i][j]);
					}
				}
			}
		}
		
		//크루스칼 알고리즘
		Collections.sort(edges);
		int cnt = 0;
		int cost = 0;
		make();
		for(int i = 0; i< edges.size(); i++) {
			
			if(cnt == num-1) break;
			
			Edge cur = edges.get(i);
			int start = cur.start;
			int end = cur.end;
			int weight = cur.weight;
		
			if(!union(start, end)) continue;
//			System.out.println(cur);
			cnt++;
			cost += weight;
		}
		
		if(cnt == num-1) System.out.println(cost);
		else System.out.println(-1);
		
	}
	
	static void make() {
		parents = new int[num+1];
		for(int i = 1; i <= num; i++) {
			parents[i] = -1;
		}
	}
	static int find(int a) {
		if(parents[a] == -1)
			return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
		
	}
	
	static void findEdge(int x, int y, int dir, int num) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		int len = 0;
		while(true) {
			if(OOB(nx, ny)) break;
			if(map[nx][ny] == num) break;
			
			//물인 경우
			if(map[nx][ny] == 0) {
				visit[nx][ny] = true;
				nx += dx[dir];
				ny += dy[dir];
				len++;
				continue;
			}
			//다른 섬인 경우 -> 엣지 만들기
			if(len < 2) break;
			edges.add(new Edge(num, map[nx][ny], len));
//			System.out.println(num +  " " + map[nx][ny] + " " + len);
			break;
			
		}
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
	
	
	static void bfs(int x, int y, int num) {
		Queue<Pair> q = new ArrayDeque<>();
		
		q.add(new Pair(x, y));
		visit[x][y] = true;
		map[x][y] = num;
		
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				
				if(OOB(tx, ty)) continue;
				if(visit[tx][ty]) continue;
				if(map[tx][ty] != 1) continue;
				visit[tx][ty] = true;
				map[tx][ty] = num;
				q.add(new Pair(tx, ty));
			}
		}
	}
}