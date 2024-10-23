//BOJ12893 적의 적
//유니온 파인드로 각자의 친구들을 연결
// enemy(적) 배열을 활용
// 적대관계인 사람의 적이 있다면 내 친구로(union) 등록한다.
import java.io.*;
import java.util.*;

public class BOJ12893 {
	static int N, M;
	static int[] parent;
	static int[] enemy;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		enemy = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		boolean flag = true;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			
			if(find(a) == find(b)) {
				flag = false;
				break;
			};
			
			// a한테 적이 있을 시 b랑 친구다
			if(enemy[a] != 0) {
				union(enemy[a], b);
			}else { //a한테 적이 없다면 a의 적으로 b를 등록한다.
				enemy[a] = b;
			}
			
			//b한테 적이 있을 시 a랑 친구다.
			if(enemy[b] != 0) {
				union(enemy[b], a);
			}else { //b한테 적이 없다면 b의 적으로 a를 등록한다.
				enemy[b] = a;
			}
		}
		
		if(flag) {
			bw.write(1+"\n");
		}else {
			bw.write(0+"\n");
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		parent[b] = a;
		return true;
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
}
