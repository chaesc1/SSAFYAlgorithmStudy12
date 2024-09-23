//BOJ16168 �۷��̵�, ���4
//���Ϸ� ��Ģ & ���Ͽ�-���ε带 Ȱ���Ͽ� Ǯ �� �ִ�.
//���Ϸ� ��Ģ�� �Ѻױ׸��� ��Ģ�� �����ؾ���
//�Ѻױ׸��� 
//1. ¦������ �ִ�  ���
//2. Ȧ������ 2���� �����鼭
// ���Ͽ� ���ε� Ȱ���ϴ� ���� - ��� ��尡 �ϳ��� ������Ʈ�� ����Ǿ� �־����
// ��� ��尡 �ϳ��� ������Ʋ�� ����� �� Ȯ���ϴ°� ���Ͽ�-���ε��� ���� �θ� ã�� �۾���

// ��� Ʋ�� ���� : ��� ������ �Ϸ�Ǿ��� �� �θ� ����Ʈ�� parent[ ]�� ���� �θ� ���õǾ� 
// ���� ���̶�� �����ߴ�. ������ �װ��� Ư���� ��Ȳ������ �׷��Ͽ���.
// Ư�� ����� �θ� �˰� ���� ����, parent[x]�� ���� �����ϴ� ���� �ƴ϶�, getParent(x) 
// �Լ��� ȣ�������ν� �����ؾ� �Ѵ�. getParent(x)�� ȣ���ؾ� ��μ� ��¥ �θ� �� �� �ִ�.
// �̷��� ��Ȳ �߻� ���� : "�������� ������ ���ĵ��� �ʾұ� ����"
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
			if(count[i] % 2 == 1) { //Ȧ���ΰ��
				cnt++;
			}
		}
		
		// ���⼭ temp = parent[1]; �̷��� �ϸ� ��¥ ���� �θ� �ƴ϶� Ʋ��
		int temp = find(parent[1]);
		boolean flag = true;
		for(int i = 1; i < V+1; i++) {
			// ���⼭�� temp != parent[i]; �̷��� �ϸ� ��¥ ���� �θ� �ƴ϶� Ʋ��
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
