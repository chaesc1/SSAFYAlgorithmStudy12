//BOJ 최소 스패닝 트리 골프4
//Node 클래스에 그래프 간선, 가중치 정보를 담아서 활용
//Node 타입을 ArrayList배열에 담아서 정점끼리 연결된 것을 표현
//프림을 기반으로 하여 BFS를 사용하였다
//MST를 프림으로 공부하는데 도움이 됐다.
import java.io.*;
import java.util.*;

public class BOJ1197 {
	static class Node implements Comparable<Node>{
		int to;
		int cost;
		
		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static int V, E, ans;
//	static int[] parents;
	static boolean[] visited;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		visited = new boolean[V+1];
		nodeList = new ArrayList[V+1];
		for(int i = 1; i <= V; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
//			nodeList.add(new Node(from, to, cost));
			nodeList[from].add(new Node(to, cost));
			nodeList[to].add(new Node(from, cost));
		}
			
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			int to = n.to;
			int cost = n.cost;
			
			if(visited[to]) continue;
			visited[to] = true;
			ans += cost;
			
			for(Node next : nodeList[to]) {
				if(!visited[next.to]) pq.add(next);
			}
		}
		
	    System.out.println(ans);
	}
}









