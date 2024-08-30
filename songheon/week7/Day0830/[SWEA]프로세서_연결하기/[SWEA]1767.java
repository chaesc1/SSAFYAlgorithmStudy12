import java.util.*;
import java.io.*;

public class Solution {

	// 맵 상의 위치 정보 저장하는 Pair 클래스 생성
	// 입력 받으면서 프로세스 정보 저장 -> 프로세스 리스트 생성
	// dfs로 구현 (매개변수 : 현재 탐색하는 프로세스 인덱스)
		// 현재 프로세서의 4방향으로 전선 설치 여부 판단
		// 맵 상의 전선 설치 표시 
		// 다음 프로세서 넘겨서 dfs 진행
		// 맵 상에 표시했던 전선 원복
	// dfs에서 마지막 프로세서까지 탐색이 끝난 경우
		// 맵 상의 전선 카운트 해서 최솟값 비교하여 출력
	
	static int tc;
	static int n;
	static int map[][];
	static int answer;
	static ArrayList<Pair> cores;
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0}; // 우, 하, 좌, 상
	static int maxCount;
	
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			cores = new ArrayList<>();
			n = sc.nextInt();
			map = new int[n][n];
			answer = n * n;
			maxCount = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] == 1) cores.add(new Pair(i, j));
				}
			}
			
			findLine(0, 0);

			sb.append(answer).append('\n');
		}
		System.out.print(sb);
	}
	
	static void findLine(int idx, int count) {
		
		if(idx == cores.size()) {
			if(maxCount > count)
				return;
			else {
				int cnt = 0;
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(map[i][j] == -1) cnt++;
					}
				}
				
				if(maxCount < count) {
					maxCount = count;
					answer = cnt;
				}
				else {
					answer = Math.min(answer, cnt);
				}
			}
			return;
		}
		
		int cx = cores.get(idx).x;
		int cy = cores.get(idx).y;
		
		if(cx == 0 || cx == n-1 || cy == 0 || cy == n-1) findLine(idx + 1, count + 1);
		
		
		else {
			
			findLine(idx + 1, count);	
			
			for(int i = 0; i < 4; i++) {
				if(isAvailable(idx, i)) {
					int dirx = dx[i];
					int diry = dy[i];
					
					//전선 설치
					int tx = cx;
					int ty = cy;
					while(true) {
						tx += dirx;
						ty += diry;
						
						if(OOB(tx, ty)) break;
						
						map[tx][ty] = -1;
					}
					findLine(idx + 1, count + 1);
					//전선 원복
					tx = cx;
					ty = cy;
					while(true) {
						tx += dirx;
						ty += diry;
						
						if(OOB(tx, ty)) break;
						
						map[tx][ty] = 0;
					}
				}
			}
		}
		
		
		
	}
	
	static boolean isAvailable(int idx, int dir) {
		int tx = cores.get(idx).x;
		int ty = cores.get(idx).y;
		int dirx = dx[dir];
		int diry = dy[dir];
		
		while(true) {
			tx += dirx;
			ty += diry;
			
			if(map[tx][ty] != 0) return false;
			if(tx == 0 || tx == n-1 || ty == 0 || ty == n-1) return true;
		}
		
	}
}