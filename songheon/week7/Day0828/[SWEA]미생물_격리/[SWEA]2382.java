import java.io.*;
import java.util.*;

public class Solution {
	//군집 클래스 : 위치 정보, 미생물 수, 방향, 활성화 상태
	//입력
		//map 정보 업데이트 : 위치한 군집의 배열 idx 기록
		//미생물 배열 생성
	//시간이 m까지 반복
		//시간 증가
		//미생물 이동 (방향따라) -> 군집 클래스 위치 정보 업데이트, map 0으로 업뎃
		//가장 자리면 미생물 수 조절 -> 0이라면 활성화상태 업뎃
		//이동한 위치의 map 값이 0이라면 이동한 위치 맵에 표시
		//아니라면 해당 위치 idx 값의 미생물 군집과 비교
			//미생물 수 업데이트, map 업데이트
			//활성화 상태 업데이트
	//시간이 지난 경우, 활성화 상태의 군집의 미생물 수 합 출력
	
	static int tc;
	static int n, m, k;
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1}; // 상 하 좌 우
	static HashMap<Integer, Integer> changeDir = new HashMap<Integer, Integer>();
	static int map[][];
	static Group groups[];
	static int answer;
	
	static class Group{
		int x;
		int y;
		int cnt;
		int merge = 0;
		int dir;
		boolean active;
		public Group(int x, int y, int cnt, int dir, boolean active) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir-1;
			this.active = active;
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		changeDir.put(0, 1);
		changeDir.put(1, 0);
		changeDir.put(2, 3);
		changeDir.put(3, 2);
		
		tc = sc.nextInt();
		for(int t = 1; t <= tc; t++) {
			
			sb.append("#").append(t).append(" ");
			n = sc.nextInt();
			m = sc.nextInt();
			k = sc.nextInt();
			
			map = new int[n][n];
			groups = new Group[k+1];
			answer = 0; 
			
			for(int i = 1; i <= k; i++) {
				groups[i] = new Group(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), true);
				Group tmp = groups[i];
				map[tmp.x][tmp.y] = i;
			}
			
			
			while(m > 0) {
				//미생물 이동
				for(int i = 1; i <= k; i++) {
					Group tmp = groups[i];
					
					if(!tmp.active) continue;
					
					map[tmp.x][tmp.y] = 0;
					groups[i].x += dx[tmp.dir];
					groups[i].y += dy[tmp.dir];
				}
				
				//가장 자리인 경우
				for(int i = 1; i <= k; i++) {
					Group tmp = groups[i];
					
					if(!tmp.active) continue;
					
					if(tmp.x == 0 || tmp.x == n-1
							|| tmp.y == 0 || tmp.y == n-1) {
						groups[i].cnt /= 2;
						groups[i].dir = changeDir.get(tmp.dir);
						if(tmp.cnt == 0)
							tmp.active = false;
					}
				}
				
				//실제 이동 맵에 표시
				for(int i = 1; i <= k; i++) {
					Group tmp = groups[i];
					
					if(!tmp.active) continue;
					
					if(map[tmp.x][tmp.y] == 0) {
						map[tmp.x][tmp.y] = i;
					}
					else {
						int comp = map[tmp.x][tmp.y];
						if(tmp.cnt > groups[comp].cnt) {
							map[tmp.x][tmp.y] = i;
							groups[i].merge += (groups[comp].cnt + groups[comp].merge);
							groups[comp].active = false;
						}
						else {
							groups[i].active = false;
							groups[comp].merge += (tmp.cnt + tmp.merge);
						}
						
						
					}
				}
				//합치기
				for(int i = 1; i <= k; i++) {
					Group tmp = groups[i];
					
					if(!tmp.active) continue;
					tmp.cnt += tmp.merge;
					tmp.merge = 0;
				}
				

				m--;
			}
			
			for(int i = 1; i <= k; i++) {
				Group tmp = groups[i];
				if(!tmp.active) continue;
				answer += tmp.cnt;
			}
			sb.append(answer).append('\n');
			
		}
		System.out.print(sb);
	}
}