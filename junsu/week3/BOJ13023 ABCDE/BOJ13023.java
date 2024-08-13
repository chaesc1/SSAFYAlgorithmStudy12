import java.io.*;
import java.util.*;

public class BOJ13023 {
	public static ArrayList<Integer>[] tree;
	public static boolean visited[];
//	public static int depth;
	public static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//노드 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int resultDepth = 5;
		
		//트리 노드들 생성
		tree = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
		int a, b;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
//		System.out.println(Arrays.toString(tree));
		for(int i = 0; i < N; i++) {
			dfs(i, 1);
			if(answer == 1) {
				break;
			}
		}
//		System.out.println("깊이"+max);
		System.out.println(answer);
		
		
	}
	static void dfs(int v, int depth) {
		if(depth == 5) {
			answer = 1;
			return;
		}
		visited[v] = true;
		
		for(int cur : tree[v]) {
			if(!visited[cur]) {
//				System.out.println(cur);
				dfs(cur, depth+1);
			}
	
		}
//		Arrays.fill(visited, false);
		visited[v] = false;
		
	}
}





