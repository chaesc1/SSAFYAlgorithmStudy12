
//백준 15650번 N과 M(2) 실버3
import java.io.*;
import java.util.*;

public class BOJ15650 {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int [] arr;
	static boolean[] visit;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visit = new boolean[N];
		arr = new int[M];
		dfs(0, 0);
		System.out.println(sb);
	}
	
	public static void dfs(int depth, int start) {
     if (depth == M) {
         for (int value : arr) {
             sb.append(value).append(" ");
         }
         sb.append("\n");
         return;
     }
		
		for(int i = start; i < N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				arr[depth] = i+1;
				dfs(depth+1, i+1);
				visit[i] = false; // 중복해제
			}
			
		}
	}
}
