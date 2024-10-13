//BOJ14938 서강그라운드, 골드4
//플로이드 워샬 알고리즘, 최단경로를 찾으면 된다.
// 스스로 잘 풀었다
import java.io.*;
import java.util.*;

public class BOJ14938 {
	static int n, m, r;
	static int[] item;
	static int[][] arr;
	static int INF = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); //지역 수
		m = Integer.parseInt(st.nextToken()); //수색 범위
		r = Integer.parseInt(st.nextToken()); //연결된 길 수
		item = new int[n+1];
		arr = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				arr[i][j] = INF;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			arr[a][b] = arr[b][a] = length;
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(i == j) continue;
					if(arr[i][j] > arr[i][k] +  arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 1; j <= n; j++) {
//				if(i == j) {
//					arr[i][j] = 0;
//				}
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		int maxResult = 0;
		for(int i = 1; i <= n; i++) {
			int max = 0;
			for(int j = 1; j <= n; j++) {
				if(i == j) arr[i][j] = 0;
				if(arr[i][j] <= m) {
					max += item[j];
				}
			}
			maxResult = Math.max(maxResult, max);
		}
		
		
		bw.write(maxResult+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
