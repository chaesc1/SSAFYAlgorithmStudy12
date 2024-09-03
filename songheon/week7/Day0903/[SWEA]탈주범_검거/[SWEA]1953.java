import java.util.*;
import java.io.*;

public class Solution {
	//레벨별 bfs
	//레벨 = 소요 시간 (시작 노드 부터 1시간)
	//노드 : 위치 정보, 터널 종류
	//큐에 노드를 삽입할 때마다 answer 증가
	//삽입할 때 방문 체크
	//레벨 별로 탐색
	//현재 레벨이 L+1과 같아지는 경우
		//현재 cnt 출력하고 종료
	//큐에서 빼낸 노드, 터널 종류에 따라 탐색 범위 조절
	//조건 만족하면 큐에 삽입
		//경계 벗어나지 않음
		//방문 안 됨 
		//연결되어 있는 경우 : 파이프 별로
	//파이프 별로 : 연결되어 있는 것도, 탐색할 수 있는 것도 다름
		//상하좌우 dir 값 정해서 파이프 종류 별로 연결 가능, 탐색 가능 정해주기
	
	static int tc;
	static int n, m, r, c, l;
	static int map[][];
	static boolean visit[][];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1}; //상하좌우
	static int dir[][] = new int[8][]; 
	static int answer;
	
	static class Path{
		int x, y, type;

		public Path(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		dir[1] = new int[] {0, 1, 2, 3};
		dir[2] = new int[] {0, 1};
		dir[3] = new int[] {2, 3};
		dir[4] = new int[] {0, 3};
		dir[5] = new int[] {1, 3};
		dir[6] = new int[] {1, 2};
		dir[7] = new int[] {0, 2};
		
		tc = sc.nextInt();
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			answer = 0;
			n = sc.nextInt();
			m = sc.nextInt();
			r = sc.nextInt();
			c = sc.nextInt();
			l = sc.nextInt();
			
			map = new int[n][m];
			visit = new boolean[n][m];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			answer = bfs(r, c);
			
			sb.append(answer).append('\n');
		}
		
		System.out.print(sb);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
	static boolean available(int td, int next) {
		
		if(next == 1)
			return true;
		
		if(td == 0) {
			if(next == 2 || next == 5 || next == 6)
				return true;
		}
		else if(td == 1) {
			if(next == 2 || next == 4 || next == 7)
				return true;
		}
		else if(td == 2) {
			if(next == 3 || next == 4 || next == 5)
				return true;
		}
		else if(td == 3) {
			if(next == 3 || next == 6 || next == 7)
				return true;
		}
		return false;
	}
	
	static int bfs(int r, int c) {
		Queue<Path> q = new ArrayDeque<>();
		q.add(new Path(r, c, map[r][c]));
		visit[r][c] = true;
		int cnt = 1;
		int time = 1;
		while(!q.isEmpty()) {
			if(++time >= l+1) break;
			
			int size = q.size();
			
			while(size > 0) {
				Path cur = q.poll();
				
				int type = cur.type;
//				System.out.println("type " + type);
				int recur = 0;
				if(type == 1) recur = 4;
				else recur = 2;
				
				for(int i = 0; i < recur; i++) {
					int td = dir[type][i];
//					System.out.println("dir " + td);
					int tx = cur.x + dx[td];
					int ty = cur.y + dy[td];
					
					if(OOB(tx, ty)) continue;
					if(visit[tx][ty]) continue;
					if(map[tx][ty] == 0) continue;
					
					//탐색 방향따라 이어지는 터널인지 확인
					if(available(td, map[tx][ty])) {
						q.add(new Path(tx, ty, map[tx][ty]));
						visit[tx][ty] = true;
						cnt++;
					}
				}
				size--;
			}
		}
		
		return cnt;
		
	}
}