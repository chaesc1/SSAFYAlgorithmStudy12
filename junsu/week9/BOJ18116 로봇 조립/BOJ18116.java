//BOJ18116 로봇 조립, 골드4
//유니온 파인드를 기반으로 하고 별도의 배열(count)을 만들어서 각 노드에 대한 정보를
//담았다.
import java.io.*;
import java.util.*;

public class BOJ18116 {
	static int[] parent;
	static int[] count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		parent = new int[1000000+1];
		count = new int[1000000+1];
		for(int i = 0; i < 1000000+1; i++) {
			parent[i] = i;
			count[i] = 1;
		}
		
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char ask = st.nextToken().toCharArray()[0];
			if(ask == 'I') {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			else if(ask == 'Q') {
				int search = Integer.parseInt(st.nextToken());
				search = find(search);
				bw.write(count[search]+"\n");
			}

		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			parent[b] = a;
			count[a] += count[b];
		}
	}
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
