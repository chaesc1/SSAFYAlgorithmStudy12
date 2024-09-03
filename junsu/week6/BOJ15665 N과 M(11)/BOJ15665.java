//백준 N과 M(11) 실버2
import java.io.*;
import java.util.*;

public class BOJ15665 {
	static int N, M;
	static int[] input, res;
	static Set<Integer> set = new HashSet<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
//		System.out.println(set);
		
		dfs(0);
	}
	
	static void dfs(int depth) {
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(res[i] +" ");
			}
			System.out.println();
			return;
		}
		
		Iterator<Integer> iter = set.iterator();
//		int cnt = 0;
		while(iter.hasNext()) {
			res[depth] = iter.next();
			dfs(depth+1);
		}
		
	}
}
