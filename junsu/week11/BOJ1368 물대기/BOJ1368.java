//BOJ1368 물대기, 골드
//1번 우물만 다 파서 물대기
//2번 다른 논에서 물 끌어와서 물대기
//처음에는 1시간 동안 생각해도 방법을 못찾았다 (너무 복잡하게 생각함)
//사실 우물이라는 가상의 노드(0)를 추가하면 해결되는 문제였다.
//우물이라는 가상의 노드(0)를 추가하고 우물을 포함한 모든 노드를 연결시켜 주면 끝나는 문제
//MST - 크루스칼(유니온 파인드) 로 품

import java.io.*;
import java.util.*;

public class BOJ1368 {
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
	static int[] parent, well;
	static int N, ans;
	static ArrayList<Node> nodeList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		well = new int[N+1]; //우물 값
		nodeList = new ArrayList<>();
		ans = 0;
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			well[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if(i == j) { //#### 이때는 가상의 노드(0)를 추가해주는거다. ######
					//가상의 노드 추가 - 우물 파서 물대기
					nodeList.add(new Node(0, i, well[i])); 
				}else {
					//다른 논으로부터 물을 끌어와서 물대기
					nodeList.add(new Node(i, j, cost)); 
				}
			}
		}
		
		Collections.sort(nodeList);
		
		for(Node cur : nodeList) {
			int a = cur.from;
			int b = cur.to;
			int cost = cur.cost;
			if(union(a, b)) {
				ans += cost;
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
