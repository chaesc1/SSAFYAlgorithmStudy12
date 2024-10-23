//BOJ10159 저울, 골드4
//플로이드 워샬 알고리즘(DP)
//경로가 정점마다 연결됐는지만 확인하면 된다.
import java.io.*;
import java.util.*;
public class BOJ10159 {
	static int N, M;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int INF = 100000000;
		arr = new int[N+1][N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1; //연결된거는 1로 표현
		}
		
		//플로이드 워샬 알고리즘(DP)
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i == j) continue;
					if(arr[i][k] == 1 && arr[k][j] == 1) 
						arr[i][j] = 1;
				}
			}
		}
		
		//인접리스트 (i, j)가 1이면 (j, i)도 1로 해준다. 그래야 물건 개수 파악 가능
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(arr[i][j] == 1)
					arr[j][i] = 1;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			int ans = 0;
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				if(arr[i][j] == 0) ans++; //물건 i와 비교 결과를 알 수 없는 물건의 개수 구하기
			}
			bw.write(ans+"\n");
		}
		
		
		
		bw.flush();
		bw.close();
		br.close();
	}
}
