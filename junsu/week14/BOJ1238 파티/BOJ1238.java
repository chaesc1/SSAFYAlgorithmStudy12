//BOJ1238 파티, 골드3
//다익스트라 알고리즘
//목적지로 간 후 다시 되돌아오는 거라서 다익스트라 목적지에서 시작점으로 한번 더 써주면 된다.
//visited 안쓴 버전
//실수한부분 - pq.add 위치 잘못씀;
//pq.add(new Node(next.to, dist[next.to]));
import java.io.*;
import java.util.*;

public class BOJ1238 {
	static class Node implements Comparable<Node>{
		int to;
		int cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	static int N, M, X, MaxAns;
	static int[] dist;
	static boolean[] visited;
	static int INF = 987654321;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		nodeList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList[from].add(new Node(to, cost));
		}

		int temp;
		for(int i = 1; i <= N; i++) {
			dijstra(i);
			temp = dist[X];
			//파티후 귀가
			dijstra(X);
			temp += dist[i];
			
			MaxAns = Math.max(MaxAns, temp);
//			bw.write(MaxAns+"\n");
		}

		bw.write(MaxAns+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 파티하러 X 마을로 출발
	static void dijstra(int start) {
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
//		//다익스트라 알고리즘
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.to;
			int cost = curNode.cost;
			
			for(Node next : nodeList[cur]) {
				if(dist[next.to] > dist[cur] + next.cost) {
					dist[next.to] = dist[cur] + next.cost;
					pq.add(new Node(next.to, dist[next.to]));
				}
//				실수한부분 - pq.add 위치 잘못씀;
//				pq.add(new Node(next.to, dist[next.to])); 
			}
		}
		
	}
}