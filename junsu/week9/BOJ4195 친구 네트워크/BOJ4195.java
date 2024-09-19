//BOJ4195 친구 네트워크, 골드2
// 지금까지 유니온-파인드를 int로만 연결했는데 String으로 연결하려 하니 막혔다.
// HashMap을 활용하는 힌트를 얻음
// HashMap의 key, value를 활용하면 각 String(친구이름)을 key로 잡고 Integer 형태의 value를 저장
// 해서 사용할 수 있다. (이러면 기존 유니온-파인드를 int 형식으로 사용 가능하다!)
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
			parent = new int[N*2]; //친구 관계의 수가 3일 때 모두 다른 친구들이면 총 친구는 6명이 된다.
			count = new int[N*2];  //그래서 N * 2를 해주는 거다.
			for(int i = 0; i < N*2; i++) {
				parent[i] = i;
				count[i] = 1;
			}
					
			int idx = 0; //유니온 파인드를 int로 사용하기 위해 idx 사용
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				
				if(!name.containsKey(f1)) {
					name.put(f1, idx++); //유니온 파인드를 int로 사용하기 위해 idx를 저장
				}
				if(!name.containsKey(f2)) {
					name.put(f2, idx++); //idx를 활용하면 parent와 count 배열 사용 가능
				}
				
				int a = name.get(f1); //idx를 얻어냈다.
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
