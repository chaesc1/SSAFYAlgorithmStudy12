//BOJ1717 집합의 표현 골드5
//유니온 파인드를 처음으로 사용하며 연습해봄

import java.io.*;
import java.util.*;

public class BOJ1717 {
	static int n, m;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		for(int i = 0; i <= n; i++) {
			parent[i] = i;
		}
//		System.out.println(Arrays.toString(parent));
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int calc = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(calc == 0) {
				union(a, b);
			}
			else if(calc == 1) {
				if(find(a) == find(b)) {
					bw.write("YES" + "\n");
				}else {
					bw.write("NO" + "\n");
				}
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		// 부모가 다를때
		if(a != b) {
			parent[b] = a;
		}
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		return find(parent[x]);
	}
}
