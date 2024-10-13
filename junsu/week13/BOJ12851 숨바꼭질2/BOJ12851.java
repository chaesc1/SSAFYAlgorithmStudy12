//BOJ12851 숨바꼭질2, 골드4
//BFS 문제
//첫 방문이거나 (point[next] == 0)
//이미 방문한 곳이어도 같은 시간에 방문했다면 (point[next] == point[cur] + 1)
//경우의 수에 추가될 수 있기 때문에 큐에 한번 더 넣어준다.
//이미 방문한 곳도 같은 시간일 경우를 생각해야 했음
import java.io.*;
import java.util.*;

public class BOJ12851 {
	static int N, K, time, cnt, check;
	static int[] point;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		time = 987654321;
		cnt = 0;
		point = new int[100001];
		
		bfs();
	
		bw.write(time + "\n");
		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void bfs() {
		if(N == K) {
			cnt = 1;
			time = 0;
			return;
		}
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		point[N] = 1; //구분을 위해 해둠
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			int next;
			
			//실수한 부분 : 최소 시간을 찾아야하는데 이미 시간을 초과한 케이스는 넘긴다.
			if(time < point[cur]) return; 
			
			for(int i = 0; i < 3; i++) {
				if(i == 0) next = cur - 1;
				else if(i == 1) next = cur + 1;
				else next = cur * 2;
				
				if(next < 0 || next > 100000) continue;
				
				if(next == K) {
					time = point[cur];
					cnt++;
				}
				
				//첫 방문이거나 (point[next] == 0)
				//이미 방문한 곳이어도 같은 시간에 방문했다면 (point[next] == point[cur] + 1)
				//경우의 수에 추가될 수 있기 때문에 큐에 한번 더 넣어준다.
				else if(point[next] == 0 || point[next] == point[cur] + 1) {
					point[next] = point[cur] + 1;
					q.add(next);
				}
			}
			
		}
	}
}
