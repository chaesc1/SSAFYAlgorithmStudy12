//BOJ1719 택배, 골드3
//플로이드 워샬
//첫번째 방문 위치를 따로 저장해주면 된다.
import java.io.*;
import java.util.*;

public class BOJ1719 {
	static int n, m, ans;
	static int INF = 987654321;
	static int[][] map, res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		res = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				map[i][j] = INF;
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());	
			map[from][to] = cost;
			map[to][from] = cost;
			res[from][to] = to; //초기화 같이해줘야한다.
			res[to][from] = from;
		}
		
		//플로이드 워샬 알고리즘
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
//						res[i][j] = k; //이것도 가능
						res[i][j] = res[i][k]; //첫번째 방문 위치 저장
					}
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j) bw.write("- ");
				else  bw.write(res[i][j] + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
