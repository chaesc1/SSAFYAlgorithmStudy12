//백준, 촌수계산, 실버2
import java.io.*;
import java.util.*;

public class BOJ2644 {
	static int n, m, res;
	static int p1, p2;
	static ArrayList<Integer>[] arr;
	static int[] dist;
	static boolean visited[];
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		
		arr = new ArrayList[n+1];
		dist = new int[n+1];
		visited = new boolean[n+1];
		for(int i = 1; i <= n; i++) {
			arr[i] = new ArrayList<>();
		}
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}
//		System.out.println(Arrays.deepToString(arr));
		bfs(p1);
		if(dist[p2] == 0) dist[p2] = -1;
		System.out.println(dist[p2]);
	}	
	static void bfs(int p1) {
		
		q.offer(p1);
		visited[p1] = true;
//		System.out.println(p1+"시작!!");
		
		while(!q.isEmpty()) {
			int node = q.poll();
			if(node == p2) break;
			
			for(int i = 0; i < arr[node].size(); i++) {
				int temp = arr[node].get(i);
				if(dist[temp] == 0 && !visited[temp]) {
					
//					System.out.println(node+"와 연결된 애들 "+temp);
					
					dist[temp] = dist[node]+1;
//					System.out.println(Arrays.toString(dist));
					visited[temp] = true;
					q.offer(temp);
				}
			}
			
			
		}
	}
}




