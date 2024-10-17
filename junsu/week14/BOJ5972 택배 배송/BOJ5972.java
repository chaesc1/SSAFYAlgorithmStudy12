//BOJ5972 택배 배송, 골드5
//다익스트라 알고리즘
import java.io.*;
import java.util.*;

public class BOJ5972 {			
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
	static int[] dist;
	static int INF = 987654321;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
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
			nodeList[to].add(new Node(from, cost)); //양방향이라서 둘 다 넣는다.
		}
		
		dijkstra(1);
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void dijkstra(int start) {
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
					//pq 넣어줄때 new Node로 넣는데 이때 실수하기 쉬움
					pq.add(new Node(next.to, dist[next.to]));
				}
			}
		}
		ans = dist[N];
	}
}
