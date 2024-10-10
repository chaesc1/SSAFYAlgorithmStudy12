//BOJ11404 플로이드, 골드4
//플로이드 워샬 알고리즘(DP)
//INF가 MAX_VALUE인 상태에서 더하면 오버플로우 발생하는 문제가 있었음
import java.io.*;
import java.util.*;

public class BOJ11404 {
	static int n, m;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		int INF = 987654321; //INF가 MAX_VALUE인 상태에서 더하면 오버플로우 발생하는 문제가 있었음
		arr = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				arr[i][j] = INF;
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
//			System.out.println(a+" "+b+" "+ cost);
			if(arr[a][b] != 0) {
				arr[a][b] = Math.min(arr[a][b], cost);
			}else {
				arr[a][b] = cost;
			}
		}
		
//		플로이드 워샬 알고리즘(DP) 구현
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(i == j) continue;
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(arr[i][j] == INF)
					arr[i][j] = 0;
				bw.write(arr[i][j] + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
