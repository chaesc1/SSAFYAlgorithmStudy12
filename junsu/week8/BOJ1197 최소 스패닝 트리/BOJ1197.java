//BOJ �ּ� ���д� Ʈ�� ����4
//Node Ŭ������ �׷��� ����, ����ġ ������ ��Ƽ� Ȱ��
//Node Ÿ���� ArrayList�� ��Ƽ� �迭�������� ����
//ũ�罺Į�� ������� �Ͽ� union-find�� ����Ͽ���
//MST�� ũ�罺Į�� �����ϴµ� ������ �ƴ�.
import java.io.*;
import java.util.*;

public class BOJ1197 {
	static class Node implements Comparable<Node>{
		int from;
		int to;
		int cost;
		
		public Node(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static int V, E;
	static int[] parents;
	static ArrayList<Node> nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];
		nodeList = new ArrayList<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList.add(new Node(from, to, cost));
		}
//			System.out.println(nodeList);
		Collections.sort(nodeList);
//			System.out.println(); 
//			System.out.println(nodeList);
		
		make();
		
		long sum = 0;
		long cnt = 0;
		
//			for(Node n : nodeList) {
//				System.out.println(n.from +" "+n.to +" "+n.cost);
//			}
		for(Node n : nodeList) {
			if(union(n.from, n.to)) {
				sum+= n.cost;
				cnt++;
				
				if(cnt == V-1) break;  //���� E-1�� V-1�� ��ħ
			}
		}
		
//			sb.append("#").append(tc).append(" ").append(sum).append("\n");
	    System.out.println(sum);
	}
	
	static boolean union(int from, int to) {
		int fromRoot = findSet(from);
		int toRoot = findSet(to);
		
		if(fromRoot == toRoot) return false;
		else parents[toRoot] = fromRoot;
		return true;
	}
	
	static int findSet(int v) {
		if(parents[v] == v) return v;
		else return parents[v] = findSet(parents[v]);
	}
	
	static void make() {
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
}









