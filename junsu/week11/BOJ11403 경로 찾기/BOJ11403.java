//BOJ11403 경로 찾기, 실버1
//플로이드 와샬 알고리즘을 공부할 수 있는 문제였다.( DP 를 기반 )
//플로이드 와샬 알고리즘은 모든 지점에서 다른 모든 지점까지의 최단 경로를 모두 구하는 알고리즘이다.
//다익스트라는 한 지점에서 다른 지점까지의 최단 거리이기 때문에 1차원 리스트에 저장한다.
//다익스트라는 그리디 알고리즘
import java.io.*;
import java.util.*;

public class BOJ11403 {
	static int N;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				arr[i][j] = n;
			}
		}
		
		//플로이드 워샬 알고리즘
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(arr[i][k] == 1 && arr[k][j] == 1) {
						arr[i][j] = 1;
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		
		
		
		bw.flush();
		bw.close();
		br.close();
	}
}
