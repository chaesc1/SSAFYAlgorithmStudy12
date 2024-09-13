// 2105 . [모의 SW 역량테스트] 디저트 카페
import java.io.*;
import java.util.*;

public class SWEA2105 {
	static int N;
	static int[][] cafe;
	static boolean[][] visited;
	static boolean[] index;
	static int dessert, dessertMax = 0;
	static int[] dr = {1, 1, -1, -1}; //하우, 하좌, 상좌, 상우
	static int[] dc = {1, -1, -1, 1};
	static int startRow, startCol, why1, why2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cafe = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					cafe[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.print(cafe[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			
//			int cnt = 1;
//			dessert = 0;
//			visited = new boolean[N][N];
//			index = new boolean[100+1];
//			startRow = 3;
//			startCol = 5;
//			dfs(3, 5, cnt, 0);
	
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int cnt = 1;
					dessert = 0;
					visited = new boolean[N][N];
					index = new boolean[100+1];
					startRow = i;
					startCol = j;
					dfs(i, j, cnt, 0);
//					System.out.println();
				}
			}
			
			if(dessertMax == 0) {
				dessertMax = -1;
			}
			sb.append("#").append(test_case).append(" ").append(dessertMax).append("\n");
			dessertMax = 0;
		}
		System.out.println(sb);
//		System.out.println(why1 +", "+why2);
	}
	static void dfs(int row, int col, int cnt, int start) {

//		if(row == startRow && col == startCol && visited[row][col] == true) {
//			dessert = cnt;
//			return;
//		}
		if(visited[row][col]) {
			return;
		}
		visited[row][col] = true;
		if(index[cafe[row][col]]) {
			return;
		}
//		System.out.println("최초 등장 : " + cafe[row][col]+"("+ row+", "+col+")");
		index[cafe[row][col]] = true;
		
		for(int dic = start; dic < 4; dic++) {
			int r = row + dr[dic];
			int c = col + dc[dic];
			if(r < 0 || r >= N || c < 0 || c >= N) {
				continue;
			}
			if(!visited[r][c] && !index[cafe[r][c]]) {
//////				System.out.print("현재 위치 : "+ cafe[row][col] + " ->" +cafe[r][c]+"("+r+", "+c+") - "+cnt );
				dfs(r, c, cnt+1, dic);
				index[cafe[r][c]]  = false;
				visited[r][c] = false;
			}
			if(r == startRow && c == startCol && visited[r][c] == true && dic == 3 && index[cafe[r][c]] && cnt >= 4) {
//				System.out.print("초기위치 도착 : "+ cafe[row][col] + " ->" +cafe[r][c]+"("+r+", "+c+") - "+cnt+" 현재 방향: "+dic+"  " );
				dessert = cnt;
				if(dessertMax < dessert) {
					dessertMax = dessert;
					why1 = startRow;
					why2 = startCol;
				}
				return;
			}


		}
//		System.out.println();
	}
	
}





