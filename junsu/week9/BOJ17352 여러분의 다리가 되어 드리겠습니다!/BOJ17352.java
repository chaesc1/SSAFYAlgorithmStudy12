//BOJ17352 여러분의 다리가 되어 드리겠습니다!, 골드5
//기본적인 union-find 기법을 활용하여 품
//핵심 부분 - 연결 안된 부분이 있으면 다리로 이을 두 섬의 번호를 출력한다.
import java.io.*;
import java.util.*;

public class BOJ17352 {
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
			
		int N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < N-2; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		//핵심 부분 - 연결 안된 부분이 있으면 다리로 이을 두 섬의 번호를 출력한다.
		Loop1:
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				int a = find(i);
				int b = find(j);
				if(a != b) {
					bw.write(a+" "+b+"\n");
					break Loop1;
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
