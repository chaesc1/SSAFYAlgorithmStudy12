//SWEA 1868. 파핑파핑 지뢰찾기
import java.io.*;
import java.util.*;

public class SWEA1868 {
	static StringBuilder sb;
	static int N;
	static char [][]map;
	static boolean[][] visited;
	static int max = 0;
	static int bomb = 0;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				String temp = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = temp.charAt(j);
				}
			}
			
//			System.out.println(Arrays.deepToString(map));
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			
//			char c = (char)(max + '0');
//			map[1][1] = c;
//			if(map[1][1] == '0') {
//				System.out.println("ddd"+map[1][1]);
//			}
			
//			System.out.println(Arrays.deepToString(map));
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j] || map[i][j] != '*') {
						search(i, j);
//						max++;
					}
					bomb = 0;
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					visited[i][j] = false;
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != '*' && map[i][j] == '0' && !visited[i][j]) {
						searchCnt(i, j);
						max++;
					}
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != '*' && !visited[i][j]) {
						searchCnt(i, j);
						max++;
					}
				}
			}
			
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			System.out.println("#"+test_case+" "+max);
			max = 0;

		}
		
	}
	
	static void search(int y, int x) {
		visited[y][x] = true;
		if(map[y][x] == '*') {
			return;
		}
		
		
		//8방 탐색
		int dicX = x;
		int dicY = y;
		for(int dic = 0; dic < 8; dic++) {
			dicY = y + dy[dic];
			dicX = x + dx[dic];
			if(dicX < 0 || dicX == N || dicY < 0 || dicY == N) {
				continue;
			}
			if(map[dicY][dicX] == '*') {
				visited[dicY][dicX] = true;
//				System.out.println(dicY + " " + dicX);
				bomb += 1;
			}
		}
		
		map[y][x] = (char)(bomb+'0');
		bomb = 0;
		
		
//		if(map[y][x] == '0') {
//			for(int dic = 0; dic < 8; dic++) {
//				dicY = y + dy[dic];
//				dicX = x + dx[dic];
//				if(dicX < 0 || dicX == N || dicY < 0 || dicY == N) {
//					continue;
//				}
//
//				if(!visited[dicY][dicX]) {
//					search(dicY, dicX);
//				}
//			}
//		}
		
	}
	
	static void searchCnt(int y, int x) {
		visited[y][x] = true;
		if(map[y][x] == '*') {
			return;
		}
		
		
		int dicX = x;
		int dicY = y;
		if(map[y][x] == '0') {
			for(int dic = 0; dic < 8; dic++) {
				dicY = y + dy[dic];
				dicX = x + dx[dic];
				if(dicX < 0 || dicX == N || dicY < 0 || dicY == N) {
					continue;
				}

				if(!visited[dicY][dicX]) {
					visited[y][x] = true;
					searchCnt(dicY, dicX);
				}
			}
		}
		
	}
	
}




