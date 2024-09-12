import java.util.*;
import java.io.*;

public class Main {

	static int v, e;
	static Edge[] edges;
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
			return this.weight-o.weight;
		}
	}
	
	
	static void make(){
		parents = new int[v];
		for(int i = 0; i < v; i++)
			parents[i] = -1;
	}
	
	static int find(int a) {
		if(parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		if(parents[aRoot] < parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}
		else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;			
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		v = sc.nextInt();
		e = sc.nextInt();
		edges = new Edge[e];
		
		for(int i = 0; i < e; i++) {
			edges[i] = new Edge(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt());
		}
		
		Arrays.sort(edges);
		make();
		
		int cnt = 0;
		int cost = 0;
		for(int i = 0; i < e; i++) {
			
			if(cnt == v-1) break;
			
			Edge cur = edges[i];
			
			if(union(cur.start, cur.end)) {
				cnt++;
				cost += cur.weight;
			}
			
			
		}
		
		System.out.println(cost);
		
	}
}