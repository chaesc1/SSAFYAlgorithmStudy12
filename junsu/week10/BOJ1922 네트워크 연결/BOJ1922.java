//BOJ1922 네트워크 연결, 골드4
//유니온파인드 버전
//외부 클래스가 아직 익숙하진 않다 ex) Comparable<Node> 이거랑 compareTo
// 유니온파인드 parent로 연결상태를 확인할 수 있어서 ArrayList를 2차원형식으로 안써도 됐다.
import java.io.*;
import java.util.*;

public class BOJ1922 {
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
	
	static int N, M, ans;
	static int[] parent;
	static ArrayList<Node> nodeList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		ans = 0;
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList.add(new Node(from, to, cost));
		}
		
		Collections.sort(nodeList);
//		for(int i = 0; i < nodeList.size(); i++) {
//			Node cur = nodeList.get(i);
//			System.out.println(cur.from + " " + cur.to + " " + cur.cost);
//		}
		
		for(int i = 0; i < nodeList.size(); i++) {
			Node cur = nodeList.get(i);
			
			if(union(cur.from, cur.to))
				ans += cur.cost;
		}
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		parent[b] = a;
		return true;
	}
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
