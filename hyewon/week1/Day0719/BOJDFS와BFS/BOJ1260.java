import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int V;
	public static boolean[] visited;
	public static int[][] arr;
	public static StringBuilder sb = new StringBuilder();
	public static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[a][b] = arr[b][a] = 1;
		}

		dfs(V);
		Arrays.fill(visited, false);
		bfs(V);
		System.out.println(sb);
	}

	public static void dfs(int num) {
		visited[num] = true;
		sb.append(num).append(" ");
		for(int i = 1; i <= N; i++) {
			if(arr[num][i] == 1 && !visited[i]) dfs(i);
		}
	}

	public static void bfs(int num) {
		q.add(num);
		visited[num] = true;
		sb.append('\n').append(num).append(" ");
		while(!q.isEmpty()) {
			int idx = q.poll();
			for(int i = 1; i <= N; i++) {
				if(arr[idx][i] == 1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
					sb.append(i).append(" ");
				}
			}
		}
	}
}