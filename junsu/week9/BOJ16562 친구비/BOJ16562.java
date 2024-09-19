//BOJ16562 친구비, 골드4
//처음에는 union-find와 모든 경우의 수를 다 구해야하는줄 알았다.
//순조부에서 부분집합을 활용해야 하는줄 알았으나 조금 더 생각해보니
//union-find의 연결된 특성을 활용하면 순조부를 사용할 필요가 없었다.
import java.io.*;
import java.util.*;

public class BOJ16562 {
	static int N, M, k;
	static int[] friend, cost;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		friend = new int[N+1];
		cost = new int[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i < N+1; i++) {
			friend[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			int costNum = Integer.parseInt(st.nextToken());
			cost[i] = costNum;
		}
		
		for(int e = 0; e < M; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
//			System.out.println(a+ " "+b);
		}
//		System.out.println(Arrays.toString(friend));
//		System.out.println(Arrays.toString(cost));
		
		int totalcost = 0;
		boolean flag = true;
		for(int i = 1; i < N+1; i++) {
			int root = find(i); //각 노드의 부모를 찾음
			
			if(!visited[root]) { // 노드의 부모를 방문한거면 이미 친구먹은사이다.
				if(k - cost[root] >= 0) { //가진 돈으로 친구를 살 수 있는 상태
					visited[root] = true; //친구비 지불하고 친구먹는다.
					k -= cost[root];
					totalcost += cost[root];
				} else { //가진 돈으로 친구를 살 수 없는 상태(모든 사람과 친구 X)
					flag = false;
				}
			}
			
		}
		
		if(flag) {
			bw.write(totalcost+"\n");
		}else {
			bw.write("Oh no\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		if(a < b) {
			friend[b] = a;
			
			// union하면 친구의 친구가 되니 친구비용은 낮은 쪽으로 맞춰준다.
			// 이러면 연결된 친구들끼리는 최저 비용을 구해놓은거다.
			if(cost[a] > cost[b]) {
				cost[a] = cost[b];
			}else {
				cost[b] = cost[a];
			}
			
		}else if(a > b) {
			friend[a] = b;
			
			// union하면 친구의 친구가 되니 친구비용은 낮은 쪽으로 맞춰준다.
			if(cost[a] > cost[b]) {
				cost[a] = cost[b];
			}else {
				cost[b] = cost[a];
			}
		}
	}
	
	static int find(int x) {
		if(friend[x] == x) {
			return x;
		}
		return find(friend[x]);
	}
}
