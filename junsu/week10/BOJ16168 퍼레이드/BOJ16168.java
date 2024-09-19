//BOJ16168 퍼레이드, 골드4
//오일러 법칙 & 유니온-파인드를 활용하여 풀 수 있다.
//오일러 법칙에 한붓그리기 법칙을 적용해야함
//한붓그리기 
//1. 짝수점만 있는  경우
//2. 홀수점이 2개만 있으면서
// 유니온 파인드 활용하는 이유 - 모든 노드가 하나의 컴포넌트로 연결되어 있어야함
// 모든 노드가 하나의 컴포넌틀로 연결된 걸 확인하는게 유니온-파인드의 최종 부모 찾는 작업임

// 계속 틀린 이유 : 모든 연결이 완료되었을 때 부모 리스트인 parent[ ]에 최종 부모가 세팅되어 
// 있을 것이라고 생각했다. 하지만 그것은 특정한 상황에서만 그러하였다.
// 특정 노드의 부모를 알고 싶을 때는, parent[x]를 통해 접근하는 것이 아니라, getParent(x) 
// 함수를 호출함으로써 접근해야 한다. getParent(x)를 호출해야 비로소 진짜 부모를 알 수 있다.
// 이러한 상황 발생 이유 : "간선들의 정보가 정렬되지 않았기 때문"
import java.io.*;
import java.util.*;

public class BOJ16168 {
	static int[] parent, count;
	static int V, E;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V+1];
		count = new int[V+1];
		for(int i = 1; i < V+1; i++) {
			parent[i] = i;
		}
		
//		System.out.println(Arrays.toString(parent));
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		int cnt = 0;
		for(int i = 1; i < V+1; i++) {
			if(count[i] % 2 == 1) { //홀수인경우
				cnt++;
			}
		}
		
		// 여기서 temp = parent[1]; 이렇게 하면 진짜 최종 부모가 아니라서 틀림
		int temp = find(parent[1]);
		boolean flag = true;
		for(int i = 1; i < V+1; i++) {
			// 여기서도 temp != parent[i]; 이렇게 하면 진짜 최종 부모가 아니라서 틀림
			if(temp != find(parent[i])) { 
				flag = false;
			}
		}
		
		if(cnt == 2 || cnt == 0 && flag) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
//		System.out.println(Arrays.toString(count));
//		System.out.println(Arrays.toString(parent));
		
	}
	static void union(int a, int b) {
		count[a]++;
		count[b]++;
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
