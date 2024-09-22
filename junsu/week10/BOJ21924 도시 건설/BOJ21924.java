//BOJ21924 도시 건설, 골드4
//MST - 프림 알고리즘으로 구현
//프림이라서 우선순위 큐 + BFS를 활용하였다.
//ArrayList를 배열에 담아서 그래프를 구현
//틀린 이유 : 정답이 int형 범위를 넘어갈수 있어서 ans를 long으로 선언해야 함
import java.io.*;
import java.util.*;

public class BOJ21924 {
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
	
	static int N, M;
	static long ans, total, mst;
	static boolean[] visited;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		total = 0;
		mst = 0;;
		nodeList = new ArrayList[N+1];
		visited = new boolean[N+1];
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
			total += cost; //모든 도로 다 설치할 때 드는 비용
		}
		
		prim();
		ans = total - mst; //모든 도로 설치 비용 - 모든 건물을 연결하는데 최소 비용
		
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) { //모든 건물이 연결되어 있지 않는다면 -1을 출력
				ans = -1;
			}
		}
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		//프림 알고리즘 시작 위치 1번 노드로 초기값 설정, 초기값이니까 가중치 0으로 시작
		pq.add(new Node(1, 0)); 
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int to = cur.to;
			int cost = cur.cost;
			
			if(visited[to]) continue;
			visited[to] = true;
			mst += cur.cost;
			
			for(Node next : nodeList[to]) {
				if(visited[next.to]) continue;
				pq.add(next);
			}
		}
	}
}
