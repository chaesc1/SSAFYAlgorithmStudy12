//BOJ1389 케빈 베이컨의 6단계 법칙, 실버1
import java.io.*;
import java.util.*;

public class BOJ1389 {
	static int N, M, ans;
	static int[][] arr;
	static final int INF = 999999999;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		arr = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) arr[i][j] = 0;
				else {
					arr[i][j] = INF;
				}
			}
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}		
		
		//플루이드 워샬 알고리즘 ( DP )
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				System.out.print(arr[i][j] + "  ");
//			}
//			System.out.println();
//		}
		
		// 각 사람마다 케빈 케이컨의 수 구하기
		int[] count = new int[N+1];
		int total;
		for(int i = 1; i <= N; i++) {
			total = 0;
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				total += arr[i][j];
			}
			count[i] = total;
		}
		
		int compareNum = INF;
		for(int i = 1; i <= N; i++) {
			if(compareNum > count[i]) {
				compareNum = count[i];
				ans = i;
			}
		}
		
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
