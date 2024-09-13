
import java.io.*;
import java.util.*;

public class BOJ11724 {
	static int N, M;
	static ArrayList<Integer>[] arr;
	static int num = 0;
	static boolean visited[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i < N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}
//		System.out.println(Arrays.deepToString(arr));
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				dfs(i);
				num++;
			}
		}
//		System.out.println(Arrays.toString(visited));
		System.out.println(num);
	}
	static void dfs(int idx) {
		if(visited[idx]) return;
		visited[idx] = true;
//		System.out.println(idx);
		
		for(int cur : arr[idx]) {
			if(!visited[cur]) {
//				System.out.println(cur+"에서 dfs 드감");
				dfs(cur);
//				System.out.println(cur+"에서 나옴");
			}
		}
		
	}
}
