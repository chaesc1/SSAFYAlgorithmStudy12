//BOJ1916 최소비용 구하기
//다익스트라 알고리즘
import java.io.*;
import java.util.*;

public class BOJ1916 {
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
	static int N, M;
	static int[] dist;
	static boolean[] visited;
	static int INF = 987654321;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N+1];
		nodeList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			nodeList[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList[from].add(new Node(to, cost));
		}


		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

//		//다익스트라 알고리즘
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited = new boolean[N+1];
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.to;
			int cost = curNode.cost;
			
			if(visited[cur]) continue;
			visited[cur] = true;
			
			for(Node node : nodeList[cur]) {
				if(dist[node.to] > dist[cur] + node.cost) {
					dist[node.to] = dist[cur] + node.cost;
				}
				pq.add(new Node(node.to, dist[node.to]));
			}
			
		}
		
//		System.out.println(Arrays.toString(dist));
		bw.write(dist[end]+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
