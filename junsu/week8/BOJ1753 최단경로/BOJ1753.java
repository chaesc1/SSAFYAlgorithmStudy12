//BOJ �ִܰ�� ����4
//���ͽ�Ʈ�� & ��������Ʈ�� �Բ� ����غ��� ��ȸ����.
//�ű�� �켱���� ť���� ���
//�Ÿ� ����ġ�� ���� �迭�� �����ϰ� ��ġ ������ visited ���
//������ �Ӹ��� �ȵ��ư���. �ٽ� Ǯ���

import java.io.*;
import java.util.*;

public class BOJ1753 {
	static class Node implements Comparable<Node>{
		int to;
		int cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	
	static int V, E;
	static int[] dist;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		nodeList = new ArrayList[V+1];
		for(int i = 1; i <= V; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		dist = new int[V+1];
		int INF = Integer.MAX_VALUE;
		for(int i = 1; i <= V; i++) {
			dist[i] = INF;
		}
//		System.out.println(Arrays.toString(dist));
		
		int start = Integer.parseInt(br.readLine());
//		System.out.println(start);
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList[from].add(new Node(to, cost));
		}
		// �˻����
		Dijkstra(start);
		
		for(int i = 1; i <= V; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
	}
	static void Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V+1];
		pq.add(new Node(start, 0)); //�������̶� cost�� 0�־���
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.to;
			if(check[cur]) continue;
			check[cur] = true;
			
			for(Node node : nodeList[cur]) {
				if(dist[node.to] > node.cost + dist[cur]) {
					dist[node.to] = node.cost + dist[cur];
				}
				pq.add(new Node(node.to, dist[node.to]));
			}
			
		}
		
	}
}
