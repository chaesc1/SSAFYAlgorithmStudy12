//BOJ17144 미세먼지 안녕!, 골드4
// 구현&시뮬레이션 문제
import java.io.*;
import java.util.*;

public class BOJ17144 {
	static int R, C, T, result;
	static int[] airUp = new int[2]; //위 공기청정기 위치
	static int[] airDown = new int[2]; //아래 공기청정기 위치
	static int[][] map;
	static int[][] diffuse;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		result = 0;
		map = new int[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
			}
		}

		for (int t = 1; t <= T; t++) {
			spread(); // 미세먼지 확산
			airPurifier(); // 공기청정기 작동
		}

//		for(int i = 1; i <= R; i++) {
//			for(int j = 1; j <= C; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(map[i][j] == -1 || map[i][j] == 0) continue;
				result += map[i][j];
			}
		}
		bw.write(result+"\n");
		bw.flush();
		bw.close();
		br.close();
	}

	static void spread() {
		diffuse = new int[R + 1][C + 1];
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if(map[i][j] == -1 || map[i][j] == 0) continue;
				if(map[i][j] / 5 >= 1) {
					int cnt = 0;
					for(int dic = 0; dic < 4; dic++) {
						int nextI = i + dx[dic];
						int nextJ = j + dy[dic];
						
						if(nextI < 1 || nextJ < 1 || nextI > R || nextJ > C) continue;
						if(map[nextI][nextJ] == -1) continue;
						diffuse[nextI][nextJ] += map[i][j] / 5;
						cnt++;
					}
					//4방 탐색 끝내고 빼줘야한다.
					map[i][j] -= (map[i][j] / 5) * cnt;
				}
			}
		}
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				map[i][j] += diffuse[i][j];
			}
		}

	}

	static void airPurifier() {
		Loop :
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(map[i][j] == -1) {
					airUp[0] = i;
					airUp[1] = j;
					airDown[0] = i+1;
					airDown[1] = j;
					break Loop;
				}
			}
		}
		
		//위에 공기청정기 작동
		int curX = airUp[0];
		int curY = airUp[1] + 1; //오른쪽부터 작동할거니 +1 해줌
		int direction = 0;
		int save = 0;
		int temp = 0;
		while(true) {
//			System.out.println(curX + " " + curY);
			if(curX == airUp[0] && curY == airUp[1]) break;
			save = map[curX][curY];
			map[curX][curY] = temp;
			temp = save;
			
			int nextX = curX + dx[direction];
			int nextY = curY + dy[direction];
			
			if(nextX < 1 || nextY < 1 || nextX > R || nextY > C) {
				if(direction == 0) direction = 3;
				else direction--;
				nextX = curX + dx[direction];
				nextY = curY + dy[direction];
			}
			curX = nextX;
			curY = nextY;
		}
		
		//아래 공기청정기 작동
		curX = airDown[0];
		curY = airDown[1] + 1; //오른쪽부터 작동할거니 +1 해줌
		direction = 0;
		save = 0;
		temp = 0;
		while(true) {
//			System.out.println(curX + " " + curY);
			if(curX == airDown[0] && curY == airDown[1]) break;
			save = map[curX][curY];
			map[curX][curY] = temp;
			temp = save;
			
			int nextX = curX + dx[direction];
			int nextY = curY + dy[direction];
			
			if(nextX < 1 || nextY < 1 || nextX > R || nextY > C) {
				if(direction == 3) direction = 0;
				else direction++;
				nextX = curX + dx[direction];
				nextY = curY + dy[direction];
			}
			curX = nextX;
			curY = nextY;
		}
	}
}
