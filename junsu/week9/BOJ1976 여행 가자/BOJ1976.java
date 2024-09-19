//BOJ1976 ���� ����, ���4
//ó���� ������ �о������� ArrayList�� ������� �Ͽ� ��������Ʈ �������
//Ǫ�� ����� ��������.
//������ ���� �� �����غ��� ���Ͽ� ���ε尡 ������ �� ���ٴ� ������ ��
//���� Ǯ�̿� ���� ����� �پ��ϰ� �����غ��� ��ȸ����.
import java.io.*;
import java.util.*;

public class BOJ1976 {
	static int N, M;
	static int[] parent;
	static int[] trip;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		trip = new int[M+1];
		
		for(int i = 0; i < N+1; i++) {
			parent[i] = i;
		}
		
		for(int a = 1; a <= N; a++) {
			st = new StringTokenizer(br.readLine());
			for(int b = 1; b <= N; b++) {
				int connect = Integer.parseInt(st.nextToken());
				if(connect == 1) //1�̸� ����ȰŶ� union���ش�.
					union(a, b);
			}
		}
		
//		System.out.println(Arrays.toString(parent));
		
		//���� ��ȹ�� �־�����.
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) { 
			int planNum = Integer.parseInt(st.nextToken());
			trip[i] = planNum;
		}

		//���� ��ȹ���� ��� �ᱹ ���� �θ� �ش��ϴ��� �˻�(���� �θ�� ����Ȱ�)
		int ans = find(trip[1]); //ù��° �������� �ʱⰪ���� ����
		boolean answer = true;
		if(M > 1) { //���� ��ȹ�� 2�� �̻��϶��� ����
			for(int i = 2; i <= M; i++) {
				if(ans != find(trip[i])) { //���� �θ� �ƴҶ�(���� �ȵȰ�)
					answer = false; //���� �ȵ����� false
					break;
				}
			}
		}
		
		if(answer) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
//		System.out.println(Arrays.toString(trip));
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
