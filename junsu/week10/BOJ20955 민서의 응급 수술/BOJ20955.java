//BOJ20955 �μ��� ���� ����, ���4
//�ݷ�
//6 10
//3 4
//1 3
//1 5
//3 5
//1 6
//3 6
//2 5
//1 2
//5 6
//2 3
//answer: 5

//���̵��
//�����ϴ� ���� 2�����̴�.
//- 1. ������� ���� �� ������ �����ϱ�
//- 2. �̹� ����� �� ������ ������ ����(Ƚ���� ���� �ȴ�.)
//- 2���� �ϵ��� ���� Ƚ���� union-find�� Ȱ���Ͽ� ���ϸ� ���� �������� 

import java.io.*;
import java.util.*;

public class BOJ20955 {
	static int N, M, cnt;
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = 0;
		parent = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		int temp = find(parent[1]);
		for(int i = 1; i < N+1; i++) {
			int num = find(parent[i]);
			if(temp != num) {  //- 1. ������� ���� �� ������ �����ϱ�
				union(temp, num);
				cnt++;
			}
		}
//		System.out.println(Arrays.toString(parent));
		bw.write(cnt+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) {  //- 2. �̹� ����� �� ������ ������ ����(Ƚ���� ���� �ȴ�.)
			cnt++;
			return;
		}
		if(a < b) {
			parent[b] = a;
		}else {
			parent[a] = b;
		}
		
	}
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
