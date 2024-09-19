//BOJ7511 소셜 네트워킹 어플리케이션, 골드5
// 기존 union-find 기법을 사용하면 간단하게 해결 가능
import java.io.*;
import java.util.*;

public class BOJ7511 {
	static int n, k, m;
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			parent = new int[n];
			for(int i = 0; i < n; i++) {
				parent[i] = i;
			}
			
			k = Integer.parseInt(br.readLine());
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			
			bw.write("Scenario "+tc+":\n");
			m = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				u = find(u);
				v = find(v);
				if(u == v) {
					bw.write(1+"\n");
				}else if(u != v) {
					bw.write(0+"\n");
				}
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
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
