//BOJ1504 특정한 최단 경로, 골드4
//다익스트라
//에러 원인 1 - ans 누적합 과정에서 생겼다
//두 가지 경로의 값을 비교할 때 각각 별도의 변수를 사용해 계산하고, 그 결과를 비교해야 합니다. 
//예를 들어, 두 경로를 각각 따로 저장하고 마지막에 비교하는 방식으로 코드를 수정
//에러 원인 2 - INF = 범위 설정
// INF를 987654321 로 하면 89퍼 에러 발생한다. 
// INF를 200000000 으로 하면 해결, 오버플로 이슈인듯
import java.io.*;
import java.util.*;

public class BOJ1504 {
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
	static int N, E, ans;
	static int[] dist;
	static int INF = 200000000;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[N+1];
		nodeList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList[from].add(new Node(to, cost));
			nodeList[to].add(new Node(from, cost));			
		}
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int res1 = 0;
		res1 += dijkstra(1, v1);
		res1 += dijkstra(v1, v2);
		res1 += dijkstra(v2, N);

		int  res2 = 0;
		res2 += dijkstra(1, v2);
		res2 += dijkstra(v2, v1);
		res2 += dijkstra(v1, N);
		ans = Math.min(res1, res2);
		
		if(ans >= INF) {
			ans = -1;
		}

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static int dijkstra(int start, int end) {
		Arrays.fill(dist, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.to;
			int cost = curNode.cost;
			
			for(Node next : nodeList[cur]) {
				if(dist[next.to] > dist[cur] + next.cost) {
					dist[next.to] = dist[cur] + next.cost;
					pq.add(new Node(next.to, dist[next.to]));
				}
			}
		}
//		if(dist[end] != INF) {
//			ans += dist[end];
////		System.out.println(dist[end]);			
//		}
		return dist[end];
	}
}
