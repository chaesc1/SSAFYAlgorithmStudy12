//BOJ1976 여행 가자, 골드4
//처음에 문제를 읽었을때는 ArrayList를 기반으로 하여 인접리스트 방식으로
//푸는 방식이 생각났다.
//하지만 조금 더 생각해보니 유니온 파인드가 가능할 거 같다는 생각이 듬
//문제 풀이에 대한 방법을 다양하게 생각해보는 기회였다.
import java.io.*;
import java.util.*;

public class BOJ1976 {
	static int N, M;
	static int[] parent;
	static int[] trip;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		trip = new int[M+1];
		
		for(int i = 0; i < N+1; i++) {
			parent[i] = i;
		}
		
		for(int a = 1; a <= N; a++) {
			st = new StringTokenizer(br.readLine());
			for(int b = 1; b <= N; b++) {
				int connect = Integer.parseInt(st.nextToken());
				if(connect == 1) //1이면 연결된거라 union해준다.
					union(a, b);
			}
		}
		
//		System.out.println(Arrays.toString(parent));
		
		//여행 계획이 주어진다.
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) { 
			int planNum = Integer.parseInt(st.nextToken());
			trip[i] = planNum;
		}

		//여행 계획들이 모두 결국 같은 부모에 해당하는지 검사(같은 부모면 연결된거)
		int ans = find(trip[1]); //첫번째 여행지를 초기값으로 저장
		boolean answer = true;
		if(M > 1) { //여행 계획이 2곳 이상일때만 실행
			for(int i = 2; i <= M; i++) {
				if(ans != find(trip[i])) { //같은 부모가 아닐때(연결 안된거)
					answer = false; //연결 안됐으니 false
					break;
				}
			}
		}
		
		if(answer) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
//		System.out.println(Arrays.toString(trip));
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		parent[b] = a;
	}
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
