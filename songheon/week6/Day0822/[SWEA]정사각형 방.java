import java.util.*;
import java.io.*;

public class Solution {
	//처음 방 번호 입력 받을 때 우선순위 큐에 좌표값과 방 번호 삽입
	//우선 순위 큐는 방번호가 작은 순서대로 정렬
	//우선순위큐에서 하나씩 뽑아서 해당 좌표 값이 visit이 안되었다면 bfs 실행
	//최대 방 개수 업데이트
	//bfs 큐에 현재 원소 좌표, 지나온 방의 개수 전달
	//현재 원소의 값과 1차이 나고, visit 안되었다면 큐에 삽입
	//큐에 삽입할 때마다 visit 표시
	//큐에 삽입할 때마다 방의 최대 개수 업데이트
	
	static int tc;
	static int n;
	static int[][] map;
	static int max;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static int answer;
	static boolean[][] visit;
	
	static class Room{
		int x;
		int y;
		int num;
		public Room(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
		
	}
	
	static class Tuple{
		int x;
		int y;
		int route;
		public Tuple(int x, int y, int route) {
			super();
			this.x = x;
			this.y = y;
			this.route = route;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			answer = 0;
			max = 0;
			n = sc.nextInt();
			map = new int[n][n];
			visit = new boolean[n][n];
			PriorityQueue<Room> pq = new PriorityQueue<>((m1,m2)->m1.num-m2.num);
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j ++) {
					map[i][j] = sc.nextInt();
					pq.add(new Room(i, j, map[i][j]));
				}
			}
			
			
			while(!pq.isEmpty()) {
				
				//bfs
				if(visit[pq.peek().x][pq.peek().y]) {
					pq.poll();
					continue;
				}
				
				Queue<Tuple> q = new LinkedList<>();
				q.add(new Tuple(pq.peek().x, pq.peek().y, 1));
				visit[pq.peek().x][pq.peek().y] = true;
				
				while(!q.isEmpty()) {
					int curx = q.peek().x;
					int cury = q.peek().y;
					int route = q.peek().route;
					q.poll();
					
					if(max < route) {
						answer = pq.peek().num;
						max = route;
					}
					
					for(int i = 0; i < 4; i++) {
						int tx = curx + dx[i];
						int ty = cury + dy[i];
						
						if(tx < 0 || tx >= n || ty < 0 || ty >= n)
							continue;
						if(!visit[tx][ty] && Math.abs(map[tx][ty] - map[curx][cury]) == 1) {
							visit[tx][ty] = true;
							q.add(new Tuple(tx, ty, route + 1));
						}
					}
				}
				
				
				pq.poll();
				
				
			}
			
			
			sb.append("#").append(t).append(" ").append(answer).append(" ").append(max).append('\n');
			
		}
		System.out.print(sb);
	}
	
	
}