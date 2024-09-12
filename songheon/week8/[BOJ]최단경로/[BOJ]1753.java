import java.util.*;
import java.io.*;

public class Main {

	static int V, E, K;
	static Node list[];
	
	static class Node{
		int vertex;
		int weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	static class Vertex implements Comparable<Vertex>{
		int vertex;
		int weight;
		public Vertex(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Vertex o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		K = sc.nextInt()-1;
		
		list = new Node[V];
		
		for(int i = 0; i < E; i++) {
			int u = sc.nextInt()-1;
			int v = sc.nextInt()-1;
			int w = sc.nextInt();
			
			list[u] = new Node(v, w, list[u]);
		}
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		int minDist[] = new int[V];
		boolean visit[] = new boolean[V];
		int INF = 2000000;
		for(int i = 0; i < V; i++)
			minDist[i] = INF;
		
		pq.add(new Vertex(K, 0));
		int min = 0;
		minDist[K] = 0;
		
		while(!pq.isEmpty()) {
			Vertex cur = pq.poll();
			if(visit[cur.vertex]) continue;
			visit[cur.vertex] = true;
			min = minDist[cur.vertex];
//			System.out.println("pq poll : " + cur.vertex + " " + min);
			
			for(Node n = list[cur.vertex]; n != null; n = n.next) {
				if(visit[n.vertex]) continue;
				if(minDist[n.vertex] > min + n.weight) {
					minDist[n.vertex] = min + n.weight;
					pq.add(new Vertex(n.vertex, minDist[n.vertex]));
//					System.out.println("pq push : " + n.vertex + " " + minDist[n.vertex]);
				}
			}
		}
		
		for(int i = 0; i < V; i++) {
			if(i == K) System.out.println(0);
			else if(minDist[i] == INF) System.out.println("INF");
			else System.out.println(minDist[i]);
		}
		
	}
}