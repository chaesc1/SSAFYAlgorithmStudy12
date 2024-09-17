//BOJ16562 ģ����, ���4
//ó������ union-find�� ��� ����� ���� �� ���ؾ��ϴ��� �˾Ҵ�.
//�����ο��� �κ������� Ȱ���ؾ� �ϴ��� �˾����� ���� �� �����غ���
//union-find�� ����� Ư���� Ȱ���ϸ� �����θ� ����� �ʿ䰡 ������.
import java.io.*;
import java.util.*;

public class BOJ16562 {
	static int N, M, k;
	static int[] friend, cost;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		friend = new int[N+1];
		cost = new int[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i < N+1; i++) {
			friend[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			int costNum = Integer.parseInt(st.nextToken());
			cost[i] = costNum;
		}
		
		for(int e = 0; e < M; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
//			System.out.println(a+ " "+b);
		}
//		System.out.println(Arrays.toString(friend));
//		System.out.println(Arrays.toString(cost));
		
		int totalcost = 0;
		boolean flag = true;
		for(int i = 1; i < N+1; i++) {
			int root = find(i); //�� ����� �θ� ã��
			
			if(!visited[root]) { // ����� �θ� �湮�ѰŸ� �̹� ģ���������̴�.
				if(k - cost[root] >= 0) { //���� ������ ģ���� �� �� �ִ� ����
					visited[root] = true; //ģ���� �����ϰ� ģ���Դ´�.
					k -= cost[root];
					totalcost += cost[root];
				} else { //���� ������ ģ���� �� �� ���� ����(��� ����� ģ�� X)
					flag = false;
				}
			}
			
		}
		
		if(flag) {
			bw.write(totalcost+"\n");
		}else {
			bw.write("Oh no\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		if(a < b) {
			friend[b] = a;
			
			// union�ϸ� ģ���� ģ���� �Ǵ� ģ������� ���� ������ �����ش�.
			// �̷��� ����� ģ���鳢���� ���� ����� ���س����Ŵ�.
			if(cost[a] > cost[b]) {
				cost[a] = cost[b];
			}else {
				cost[b] = cost[a];
			}
			
		}else if(a > b) {
			friend[a] = b;
			
			// union�ϸ� ģ���� ģ���� �Ǵ� ģ������� ���� ������ �����ش�.
			if(cost[a] > cost[b]) {
				cost[a] = cost[b];
			}else {
				cost[b] = cost[a];
			}
		}
	}
	
	static int find(int x) {
		if(friend[x] == x) {
			return x;
		}
		return find(friend[x]);
	}
}
