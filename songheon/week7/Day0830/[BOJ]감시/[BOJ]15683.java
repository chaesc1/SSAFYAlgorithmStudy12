import java.io.*;
import java.util.*;

public class Main {
	// 입력 받으면서 카메라 리스트 만들기
		// 카메라 클래스 : 위치 정보, 카메라 타입 정보
	// 카메라 리스트 dfs로 순회하면서 해당 카메라의 방향 정해주기 -> 부분집합
	// 카메라 리스트 순회하면서 정해진 카메라 방향을 기반으로 확인 가능한 방향 맵에 표시하기
	// 전체 맵에서 사각지대 개수 세고 출력하기
	
	static int n, m;
	static int map[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0}; //우 하 좌 상
	static int dir[];
	static ArrayList<Cam> cams;
	static int answer;
	
	static class Cam{
		int x, y;
		int type;
		public Cam(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		cams = new ArrayList<>();
		answer = n * m;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] != 0 && map[i][j] != 6)
					cams.add(new Cam(i, j, map[i][j]-1));
			}
		}
		dir = new int[cams.size()];
		
		dircombi(0); // 카메라의 방향 정해주기
		
		System.out.println(answer);
	}
	
	static void dircombi(int order) {
		if(order == cams.size()) {
			answer = Math.min(answer, calc()); // 사각지대 구하기
			return;
		}
		
		int type = cams.get(order).type;
		if(type == 4) {
			dir[order] = 0;
			dircombi(order + 1);			
		}
		else if(type == 1) {
			dir[order] = 0;
			dircombi(order + 1);
			dir[order] = 1;
			dircombi(order + 1);
		}
		else {
			for(int i = 0; i < 4; i++) {
				dir[order] = i;
				dircombi(order + 1);
			}
		}
			
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
	
	static int calc() {
//		System.out.println("CAMERA SIZE" + cams.size());
		for(int i = 0; i < cams.size(); i++) {
			Cam cur = cams.get(i);
			int tx = cur.x;
			int ty = cur.y;
			int type = cur.type;
			int dirx, diry;
			
			// 카메라로 확인 가능한 방향 표시
			int recur = type;
			if(type == 0) recur = 1;
			else if(type == 1) recur = 2;
			for(int t = 0; t < recur; t++) {
				tx = cur.x; ty = cur.y;
				if(type == 1) {
					dirx = dx[(dir[i] + t * 2) % 4];
					diry = dy[(dir[i] + t * 2) % 4];
				}
				else{
					dirx = dx[(dir[i] + t) % 4];				
					diry = dy[(dir[i] + t) % 4];
				}
				while(true) {
					tx += dirx;
					ty += diry;
					
					if(OOB(tx, ty)) break;
					if(map[tx][ty] == 6) break;
					if(map[tx][ty] > 0) continue;
					map[tx][ty] = -1;
				}		
			}
		}
		
		// 사각지대 개수 세기
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		
		// 맵 상에서 사각지대 부분 다시 원복
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == -1) map[i][j] = 0;
			}
		}
		
		return cnt;
	}
}