//BOJ1647 도시 분할 계획, 골드4
//프림 알고리즘을 활용한 MST 문제
//##틀린 실수##
//1. 도시 분할 할 때 간선 중에 가장 값을 저장해서 분할해야 하는데 마지막에 저장되는 간선값으로 분할함
// 프림은 마지막에 연결되는 간선이 가장 큰 값이 아니다.
//2. 집이 2개 일 때는 연결할 필요도 없고 이미 분리된 상태라서 예외처리 해줘야한다.
// 집이 3개 이상일 때부터 prim알고리즘 실행해주면 된다.
import java.io.*;
import java.util.*;

public class BOJ1647 {
	static class Node implements Comparable<Node> {
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
	static ArrayList<Node>[] nodeList;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		visited = new boolean[N+1];
		nodeList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList[from].add(new Node(to, cost));
			nodeList[to].add(new Node(from, cost));
		}
		if(N > 2) {
			prim();
			
		}
			
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0)); //초기값 시작을 위해 1번 노드에서 시작, 초기값이라 cost는 0이다.
		int delete = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int to = cur.to;
			int cost = cur.cost;
			
			if(visited[to]) continue;
			visited[to] = true;
			ans += cost;
			
			//간선 중에 가장 값이 큰 간선의 값을 저장한다.
			delete = Math.max(delete, cost); //저장되는 cost값을 기준으로 분할할 거다.
			
			for(Node next : nodeList[to]) {
				if(visited[next.to]) continue;
//				pq.add(new Node(next.to, next.cost)); // 에러는 아니지만 표현에 아쉬움이 있었던 실수
				pq.add(next); //이렇게 하면 깔끔
			}
			
		}
		// 가장 큰 cost 기준으로 마을을 분리한다.
		ans -= delete;
	}
}



