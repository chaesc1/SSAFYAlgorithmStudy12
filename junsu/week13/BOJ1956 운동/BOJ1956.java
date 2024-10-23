//BOJ1956 운동, 골드4
//플로이드 워샬 알고리즘
import java.io.*;
import java.util.*;

public class BOJ1956 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int INF = 987654321;
		int ans = INF;
		int[][] arr = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i != j)
					arr[i][j] = INF;
			}
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[a][b] = c;
			
		}
		
		//플로이드 워샬 알고리즘
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i == j) continue;
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		
		//사이클 찾기
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				if(arr[i][j] != 0 && arr[j][i] != 0)
					ans = Math.min(ans, arr[i][j] + arr[j][i]);
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		if(ans == INF)
			ans = -1;
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
