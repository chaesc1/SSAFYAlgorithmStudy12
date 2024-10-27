//BOJ14284 간선 이어가기2, 골드5
//데이크스트라 알고리즘
import java.io.*;
import java.util.*;

public class BOJ14284 {
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
	static int N, M, ans;
	static ArrayList<Node>[] nodeList;
	static int[] dist;
	static int INF = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N+1];
		nodeList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			dist[i] = INF;
			nodeList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodeList[a].add(new Node(b, c));
			nodeList[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		dijkstra(s, t);
		
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void dijkstra(int start, int end) {
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
			}
		}
		ans = dist[end];
	}
}
