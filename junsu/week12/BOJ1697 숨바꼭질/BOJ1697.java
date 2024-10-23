//BOJ1697 숨바꼭질, 실버1
//1차원 배열을 활용한 BFS
import java.io.*;
import java.util.*;

public class BOJ1697 {
	static class P{
		int location;
		int cnt;
		public P(int location, int cnt) {
			this.location = location;
			this.cnt = cnt;
		}
	}
	static int[] point;
	static int N, K, time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		point = new int[100000+1];
		
		time = bfs();
		
		bw.write(time+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static int bfs() {
		if(N == K) return 0; // 0, 0일 때 예외 케이스 처리해줘야한다.
		Queue<P> q = new LinkedList<>();
		q.add(new P(N, 0));
		
		while(!q.isEmpty()) {
			P cur = q.poll();
			int location = cur.location;
			int cnt = cur.cnt;
			
//			System.out.print(location  +  "-");
			
			if(location-1 >= 0) {
				if(point[location-1] == 0 && location-1 != N) { //이동한 위치가 수빈이 원래 위치 아니라면 1초후 X-1로 이동
					if(location-1 ==  K) { //동생위치 찾았을 때
						time = cnt+1;
						return time;
					}
					point[location-1] = cnt+1;
					q.add(new P(location-1, cnt+1));
				}
			}
			
			if(location+1 <= 100000) {
				if(point[location+1] == 0 && location+1 != N) { //이동한 위치가 수빈이 원래 위치 아니라면 1초후 X+1로 이동
					if(location+1 ==  K) { //동생위치 찾았을 때
						time = cnt+1;
						return time;
					}
					point[location+1] = cnt+1;
					q.add(new P(location+1, cnt+1));
				}
			}
			
			if(location * 2 <= 100000) {
				if(point[location*2] == 0 && location*2 != N) { //이동한 위치가 수빈이 원래 위치 아니라면 1초후 2*X로 이동
					if(location*2 ==  K) { //동생위치 찾았을 때
						time = cnt+1;
						return time;
					}
					point[location*2] = cnt +1;
					q.add(new P(location*2, cnt+1));
				}
			}
		}
		return -1;
	}
}
