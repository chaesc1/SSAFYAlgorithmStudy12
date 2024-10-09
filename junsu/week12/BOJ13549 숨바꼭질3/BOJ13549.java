//BOJ13549 숨바꼭질3, 골드5
//기존 숨바꼭질 문제에서 최단경로 기능이 추가됨
import java.io.*;
import java.util.*;

public class BOJ13549 {
	static class Node{
		int idx;
		int time;
		public Node(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}
	
	static int[] point;
	static int N, K, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		point = new int[100000+1];
		ans = bfs();
		
		bw.write(ans + "\n");	
		bw.flush();
		bw.close();
		br.close();
	}
	static int bfs() {
		if(N == K) return 0; // 0, 0일 때 예외 케이스 처리해줘야한다.
		
		Queue<Node> q = new LinkedList<>();
		// 시작 time을 1로 해놓고, 결과 출력시 1 빼기. point의 값이 0인 것(방문 안한 곳)과 구별해주기 위해서.
		q.add(new Node(N, 1));
		point[N] = 1;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.idx + 1 >= 0 && cur.idx+1 <= 100000) { //앞으로 한칸
				if(point[cur.idx+1] == 0 || point[cur.idx+1] > cur.time+1) {
					point[cur.idx+1] = cur.time + 1;
					q.add(new Node(cur.idx + 1, cur.time+1));
				}
			}
			
			if(cur.idx - 1 >= 0 && cur.idx-1 <= 100000) { //뒤로 한칸
				if(point[cur.idx-1] == 0 || point[cur.idx-1] > cur.time+1) {
					point[cur.idx-1] = cur.time + 1;
					q.add(new Node(cur.idx - 1, cur.time+1));
				}
			}
			
			if(cur.idx * 2 >= 0 && cur.idx*2 <= 100000) { //순간이동
				if(point[cur.idx*2] == 0 || point[cur.idx*2] > cur.time) {
					point[cur.idx*2] = cur.time;
					q.add(new Node(cur.idx * 2, cur.time));
				}
			}


		}
		if(point[K] != 0) return point[K] - 1;
		return -1;
	}
}
