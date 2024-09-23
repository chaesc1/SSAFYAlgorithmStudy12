//BOJ1774 우주신과의 교감, 골드3
//MST - 크루스칼(유니온 파인)로 풀었다.
//Node class에 평소에 int형을 compareTo로 비교하여 정렬했는데 이번에 double형을 사용하며
//정렬이 안되는 문제가 발생 - Double.compare() 함수로 해결
//double 실수형 더하는 과정에서 근사값문제로 BigDecimal을 사용하는 방향으로 감
// BigDecimal을 처음 써보게 되는 문제였음 BigDecimal 어렵..

// ###########틀린 부분 수정############ 
// -3. 주요 수정 사항:
//BigDecimal 제거: BigDecimal을 제거하고, double을 사용해 계산 후 출력 시에 소수점 2자리까지 출력하도록 변경했습니다.
//소수점 출력 처리: String.format("%.2f")을 사용해 소수점 2자리까지 결과를 정확하게 출력합니다.
import java.io.*;
import java.util.*;

public class BOJ1774 {
	static class Node implements Comparable<Node>{
		int from;
		int to;
		double cost;
		public Node(int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
//			return this.cost - o.cost;
			return Double.compare(this.cost, o.cost);
		}
	}
	static int N, M;
	static double ans = 0;
	static int[] parent;
	static ArrayList<Node> nodeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		int[][] arr = new int[N+1][2];
		nodeList = new ArrayList<>();
		
		//좌표 x, y를 우선 2차원 배열에 저장한다.
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i][0] = x;
			arr[i][1] = y;
		}
		
		//2차원 배열에 있는 좌표들을 활용해 통로 길이를 구해 Node class에 넣는다.
		for(int i = 1; i <= N; i++) {
			for(int j = i; j <= N; j++) {
				if(i == j) continue;
//				System.out.print(i + " " + j + " -- ");
				int from = i;
				int to = j;
				
				// 2차원 두 점 사이의 거리 구하기
				double x_dist = Math.pow(arr[to][0] - arr[from][0], 2);
				double y_dist = Math.pow(arr[to][1] - arr[from][1], 2);
				double cost = Math.sqrt(x_dist + y_dist);
//				System.out.println(cost);
				nodeList.add(new Node(from, to, cost)); //유니온 파인드를 사용하기 위해 Node class 형태로 넣는다.
			}
		}
		// cost별로 크기 정렬 해준다.
		Collections.sort(nodeList);
		
		//M개의 줄, 이미 연결된 통로
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int tmpX = Integer.parseInt(st.nextToken()); 
			int tmpY = Integer.parseInt(st.nextToken()); 
			union(tmpX, tmpY);
		}
		
		for(Node cur : nodeList) {
//			System.out.println(cur.from +" "+cur.to+" " + cur.cost);
			if(union(cur.from, cur.to)) {
				// 소수점 2자리까지 반올림하여 BigDecimal에 추가
//				BigDecimal num = new BigDecimal(cur.cost).setScale(2, BigDecimal.ROUND_HALF_UP);
//				ans = ans.add(num);
				ans += cur.cost;
			}
		}
		
//		System.out.println(Arrays.deepToString(arr));
		bw.write(String.format("%.2f\n", ans));
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
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
