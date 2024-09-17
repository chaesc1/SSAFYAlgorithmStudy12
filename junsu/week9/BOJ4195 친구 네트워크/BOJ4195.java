//BOJ4195 ģ�� ��Ʈ��ũ, ���2
// ���ݱ��� ���Ͽ�-���ε带 int�θ� �����ߴµ� String���� �����Ϸ� �ϴ� ������.
// HashMap�� Ȱ���ϴ� ��Ʈ�� ����
// HashMap�� key, value�� Ȱ���ϸ� �� String(ģ���̸�)�� key�� ��� Integer ������ value�� ����
// �ؼ� ����� �� �ִ�. (�̷��� ���� ���Ͽ�-���ε带 int �������� ��� �����ϴ�!)
import java.io.*;
import java.util.*;
public class BOJ4195 {
	static HashMap<String, Integer> name = new HashMap<>();
	static int[] parent;
	static int[] count;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			name.clear();
			
			int N = Integer.parseInt(br.readLine());
			parent = new int[N*2]; //ģ�� ������ ���� 3�� �� ��� �ٸ� ģ�����̸� �� ģ���� 6���� �ȴ�.
			count = new int[N*2];  //�׷��� N * 2�� ���ִ� �Ŵ�.
			for(int i = 0; i < N*2; i++) {
				parent[i] = i;
				count[i] = 1;
			}
					
			int idx = 0; //���Ͽ� ���ε带 int�� ����ϱ� ���� idx ���
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				
				if(!name.containsKey(f1)) {
					name.put(f1, idx++); //���Ͽ� ���ε带 int�� ����ϱ� ���� idx�� ����
				}
				if(!name.containsKey(f2)) {
					name.put(f2, idx++); //idx�� Ȱ���ϸ� parent�� count �迭 ��� ����
				}
				
				int a = name.get(f1); //idx�� ���´�.
				int b = name.get(f2);
				
				int ans = union(a, b);
				bw.write(ans+"\n");
//				System.out.println(ans);
//				System.out.println(name);
			}
			
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	static int union(int f1, int f2) {
		f1 = find(f1);
		f2 = find(f2);

		if(f1 == f2) return count[f1];
		parent[f2] = f1;
		count[f1] += count[f2];
		return count[f1];
	}
	static int find(int x) {
		if(parent[x] == x)
			return x;
		return find(parent[x]);
	}
}
