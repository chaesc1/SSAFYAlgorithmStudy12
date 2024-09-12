import java.util.*;
import java.io.*;

public class Solution {

	
	static int tc;
	static int V, E;
	static Edge edges[];
	static int parents[];
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static void make() {
		parents = new int[V];
		for(int i = 0; i < V; i++) {
			parents[i] = -1;
		}
	}
	static int find(int a) {
		if(parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot)
			return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			
			V = sc.nextInt();
			E = sc.nextInt();
			edges = new Edge[E];
			
			for(int i = 0; i < E; i++) {
				edges[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
			}
			
			make();
			
			Arrays.sort(edges);
			
			long cnt = 0, cost = 0;
			
			for(int i = 0; i < E; i++) {
				
				if(cnt == V-1)
					break;
				
				Edge cur = edges[i];
				
				if(!union(cur.start, cur.end)) continue;
				cnt++;
				cost += cur.weight;
			}
			
			sb.append(cost).append('\n');
			
		}
		System.out.print(sb);
		
	}
}