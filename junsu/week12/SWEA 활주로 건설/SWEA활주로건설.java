//SWEA [모의 SW 역량테스트] 활주로 건설
//순수 빡구현 문제.. 생각할 조건이 많아서 번거로웠다.
//위 아래, 좌우 해줄때 배열에 visited기록해주기
import java.io.*;
import java.util.*;

public class SWEA활주로건설 {
	static int N, X, ans;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
//		int T = Integer.parseInt(br.readLine());
//		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			ans = 0;
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			widthFunc();
			heightFunc();
			bw.write(ans + "\n");
			
//		bw.write("#"+tc+" " + ans + "\n");
//		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void heightFunc() {
		boolean flag = true;
		for(int j = 0; j < N; j++) {
			visited = new boolean[N];
			flag = true;
			for(int i = 0; i < N-1; i++) {
				if(map[i][j] == map[i+1][j]) continue;
				if(map[i][j] - 1 > map[i+1][j]) {
					flag = false;
					break;
				}
				if(map[i][j] - 1 == map[i+1][j] && !visited[i+1]) { //위에서 아래로 하강할때
//					System.out.println("왼쪽에서 하강 실행" + i	 + " " + j);
					flag = HeightFuncDown(i+1, j);
					if(!flag) {
						break; //경사로 설치 기준에 벗어났으므로 다음 줄 탐색한다.
					}
				}else if(map[i][j] - 1 == map[i+1][j] && visited[i+1]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				for(int i = N-1; i > 0; i--) {
					if(map[i][j] == map[i-1][j]) continue;
					if(map[i][j] - 1 > map[i-1][j] && !visited[i-1]) {
						flag = false;
						break;
					}
					if(map[i][j] - 1 == map[i-1][j] && !visited[i-1]) { //아래에서 위로 하강할때
						flag = HeightFuncUp(i-1, j);
						if(!flag) {
							break; //경사로 설치 기준에 벗어났으므로 다음 줄 탐색한다.
						}
					}else if(map[i][j] - 1 == map[i-1][j] && visited[i-1]) {
						flag = false;
						break;
					}
				}
			}
			if(flag) { //한 라인이 경사로 설치 기준이 부합할 때 +1;
//				System.out.println("세로 라인"+j);
				ans++;
			}
		}
		
	}
	
	static void widthFunc() {
		boolean flag = true;
		for(int i = 0; i < N; i++) {
			visited = new boolean[N];
			flag = true;
			for(int j = 0; j < N-1; j++) {
				if(map[i][j] == map[i][j+1]) continue;
				if(map[i][j] - 1 > map[i][j+1]) {
					flag = false;
					break;
				}
//				System.out.println(i + " " + j + "현재 상황 " + (map[i][j] - 1) +  " "+map[i][j+1]);
				if(map[i][j] - 1 == map[i][j+1] && !visited[j+1]) { //왼쪽에서 하강할때
//					System.out.println("왼쪽에서 하강 실행" + i	 + " " + j);
					flag = widthFuncLeft(i, j+1);
					if(!flag) {
						break; //경사로 설치 기준에 벗어났으므로 다음 줄 탐색한다.
					}
				}else if(map[i][j] - 1 == map[i][j+1] && visited[j+1]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				for(int j = N-1; j > 0; j--) {
					if(map[i][j] == map[i][j-1]) continue;
					if(map[i][j] - 1 > map[i][j-1]) {
						flag = false;
						break;
					}
					if(map[i][j] - 1 == map[i][j-1] && !visited[j-1]) { //오른쪽에서 하강할때
						flag = widthFuncRight(i, j-1);
						if(!flag) {
							break; //경사로 설치 기준에 벗어났으므로 다음 줄 탐색한다.
						}
					}else if(map[i][j] - 1 == map[i][j-1] && visited[j-1]) {
						flag = false;
						break;
					}
				}
			}
			if(flag) { //한 라인이 경사로 설치 기준이 부합할 때 +1;
//				System.out.println("가로 라인"+i);
				ans++;
			}
		}
	}
	
	// 위에서 아래로 탐색
	static boolean HeightFuncDown(int x, int y) {
		int Xlength = 1;
		for(int i = x; i < N; i++) {
			if(X == Xlength) {
				visited[i] = true;
				return true;
			}
			if(i == N-1) continue;
			if(map[i][y] == map[i+1][y] && !visited[i+1]) {
				visited[i] = true;
				Xlength++;
			}else {
				return false;
			}
		}
		return false;
	}
	
	// 아래에서 위로 탐색
	static boolean HeightFuncUp(int x, int y) {
		int Xlength = 1;
		for(int i = x; i >= 0; i--) {
			if(X == Xlength) {
				visited[i] = true;
				return true;
			}
			if(i == 0) continue;
			if(map[i][y] == map[i-1][y] && !visited[i-1]) {
				visited[i] = true;
				Xlength++;
			}else {
				return false;
			}
		}
		return false;
	}
	
	// 오른쪽에서 왼쪽으로 탐색
	static boolean widthFuncRight(int x, int y) {
		int Xlength = 1;
		for(int j = y; j >= 0; j--) {
			if(X == Xlength) {
				visited[j] = true;
				return true;
			}
			if(j == 0) continue;
			if(map[x][j] == map[x][j-1] && !visited[j-1]) {
				visited[j] = true;
				Xlength++;
			}else {
				return false;
			}
		}
		return false;
	}
	
	// 왼쪽에서 오른쪽으로 탐색
	static boolean widthFuncLeft(int x, int y) {
		int Xlength = 1;
		for(int j = y; j < N; j++) {
			if(X == Xlength) {
				visited[j] = true;
				return true;
			}
			if(j == N-1) continue;
			if(map[x][j] == map[x][j+1] && !visited[j+1]) {
				visited[j] = true;
				Xlength++;
			}else {
				return false;
			}
		}
		return false;
	}
}
