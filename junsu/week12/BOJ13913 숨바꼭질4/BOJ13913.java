//BOJ13913 숨바꼭질4, 골드4
//기존 숨바꼭질 문제에서 BFS 최단거리 경로를 저장하는 배열을 사용
//경로 기록은 순서대로 구하기 위해 stack에 담았다가 pop하면된다.
//최적화 완료
import java.io.*;
import java.util.*;

public class BOJ13913 {
	static int[] point, parent;
	static int N, K, time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		point = new int[100000+1];
		parent = new int[100000+1];
		time = bfs();
		
		bw.write(time+"\n");
		
		//순서대로 구하기 위해 stack에 담았다가 pop하면된다.
		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		int index = K;
		
		while(index != N) {
			index = parent[index];
			stack.push(index);
		}
		
		while(!stack.isEmpty()) {
			bw.write(stack.pop() + " ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	static int bfs() {
		if(N == K) return 0; // 0, 0일 때 예외 케이스 처리해줘야한다.
		
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		point[N] = 1;
		
		while(!q.isEmpty()) {
			int location = q.poll();
			
//			System.out.print(location  +  "-");
			
			for(int i = 0; i < 3; i++) {
				int next;
				
				if(i == 0) next = location - 1;
				else if(i == 1) next = location + 1;
				else next = location * 2;
				
				if(next < 0 || next > 100000) continue;
				
				if(point[next] == 0) {
					q.add(next);
					point[next] = point[location] + 1;
					parent[next] = location;
				}
			}

		}
		if(point[K] != 0) return point[K] - 1;
		return -1;
	}
}
