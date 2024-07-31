import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int person1;
	public static int person2;
	public static int[] visited;
	public static int[][] arr;
	public static ArrayList<Integer> sb = new ArrayList<>();;
	public static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		person1 = Integer.parseInt(st.nextToken());
		person2 = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1][N + 1];
		visited = new int[N + 1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = arr[b][a] = 1;
		}
		
		dfs(person1);
		System.out.println(visited[person2] == 0 ? -1 : visited[person2]);
	}
	
	public static void dfs(int num) {
		if(num == person2) return;

		for(int i = 1; i <= N; i++) {
			if(arr[num][i] == 1 && visited[i] == 0) {
				visited[i] = visited[num] + 1;
				dfs(i);
			}
		}
	}

}