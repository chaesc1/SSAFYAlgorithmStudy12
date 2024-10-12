//BOJ2660 회장뽑기, 골드5
//플로이드 워샬 문제
import java.io.*;
import java.util.*;

public class BOJ2660 {
	static int N;
	static int[][] arr;
	static int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i != j) arr[i][j] = INF;
			}
		}
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == -1 && b == -1) {
				break;
			}
			arr[a][b] = arr[b][a] = 1;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i == j) continue;
					if(arr[i][k] != 0 && arr[k][j] != 0) {
						arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
					}
				}
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}

		int maxScore = 0;
		int[] ansArr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				ansArr[i] = Math.max(ansArr[i], arr[i][j]);
			}
		}
//		System.out.println(Arrays.toString(ansArr));
		int minScore = INF;
		for(int i = 1; i <= N; i++) {
			minScore = Math.min(minScore, ansArr[i]);
		}
//		System.out.println(minScore);
		
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			if(ansArr[i] == minScore) {
				result.add(i);
			}
		}
		
		
		bw.write(minScore + " " + result.size()+"\n");
		for(int cur : result) {
			bw.write(cur+" ");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
