//BOJ16398 행성 연결, 골드4
//MST - 크루스칼 알고리즘(유니온파인드)를 사용하였다.
//입력값이 2차원 인접행렬로 주어지는 케이스
// 인접행렬 -> ArrayList에 Node class로 변환하여 저장하고 풀었다.
// 틀린 이유 : 정답이 int형 범위를 넘어갈수 있어서 ans를 long으로 선언해야 함
import java.io.*;
import java.util.*;

public class BOJ16398 {
	static class Node implements Comparable<Node>{
		int from;
		int to;
		int cost;
		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static int N;
	static long ans;
	static int[] parent;
	static ArrayList<Node> nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		nodeList = new ArrayList<>();
		ans = 0;
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
//				map[i][j] = num;
				if(i == j) continue;
				nodeList.add(new Node(i, j, num));
			}
		}
		
		Collections.sort(nodeList);
		for(int i = 0; i < nodeList.size(); i++) {
			Node cur = nodeList.get(i);
			if(union(cur.from, cur.to)) {
				ans += cur.cost;
			}
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
		if(a < b)
			parent[b] = a;
		else {
			parent[a] = b;
		}
		return true;
	}
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
