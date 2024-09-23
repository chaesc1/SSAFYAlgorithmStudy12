//BOJ14621 나만 안되는 연애, 골드3
//MST - 프림 알고리즘 사용
//char형태로 성별을 기준으로 한 조건을 추가하였다.
import java.io.*;
import java.util.*;

public class BOJ14621 {
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
	static boolean[] visited;
	static char[] gender;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		gender = new char[N+1];
		nodeList = new ArrayList[N+1];
		ans = 0;
		for(int i = 1; i <= N; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		// 성별을 char 배열에 저장
		st = new StringTokenizer(br.readLine());
		for(int g = 1; g <= N; g++) {
			char ch = st.nextToken().charAt(0);
			gender[g] = ch;
		}
		
//		System.out.println(Arrays.toString(gender));
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//남초와 여초 대학을 연결하는 도로만 이루어져야한다. 남초끼리 소개팅하면 게이임
			if(gender[from] == gender[to]) {
//				System.out.println(gender[from] + " " + gender[to]);
				continue;
			}
			nodeList[from].add(new Node(to, cost));
			nodeList[to].add(new Node(from, cost));
		}

		prim();
		
		//모든 학교를 연결하는 경로가 없을 경우 -1 출력
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
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
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int to = cur.to;
			int cost = cur.cost;
			
			if(visited[to]) continue;
			visited[to] = true;
			ans += cost;
			
			for(Node next : nodeList[to]) {
				if(visited[next.to]) continue;
				pq.add(next);
			}
		}
	}
}
