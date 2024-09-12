import java.io.*;
import java.util.*;

public class Main {

	static int n, m, d;
	static int map[][];
	static int pos[];
	static ArrayList<Enemy> enemies;
	static ArrayList<Enemy> buffer;
	static int answer;
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Enemy{
		int x, y;
		boolean target = false;
		boolean active = true;
		public Enemy(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		d = sc.nextInt();
		
		map = new int[n][m];
		pos = new int[3];
		enemies = new ArrayList<>();
		
		for(int i = 0;  i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1)
					enemies.add(new Enemy(i, j));
			}
		}
		
		//궁수 자리 뽑음
		picking(0, 0);
		
		System.out.println(answer);
		
	}
	
	
	static void picking(int order, int start) {
		if(order == 3) {
//			System.out.println(Arrays.toString(pos));
			for(int j = 0; j < enemies.size(); j++) {
				Enemy cur = enemies.get(j);
				cur.active = true;
				cur.target = false;
			}
			answer = Math.max(answer, startGame());
			return;
		}
		for(int i = start; i < m; i++) {
			pos[order] = i;
			picking(order + 1, i+1);
		}
	}
	
	static int startGame() {
		int kill = 0;
		int row = n;
		
		while(true) {
			
			//적 타겟팅
			for(int i = 0; i < pos.length; i++) {
				int dist = n;
				Enemy target = null;
				for(int j = 0; j < enemies.size(); j++) {
					Enemy cur = enemies.get(j);
					if(!cur.active) continue;
					int tmp = Math.abs(cur.x - row) + Math.abs(cur.y - pos[i]);
					if(tmp <= d && tmp <= dist) { //타겟 후보
						if(target == null || dist > tmp) {
							target = cur;	
							dist = tmp;
						}
						else if(dist == tmp) {
							if(target.y > cur.y)
								target = cur;	
						}
					}
				}
				
				if(target != null) {
					target.target = true;
				}
			}
			
			//적 공격
			for(int j = 0; j < enemies.size(); j++) {
				Enemy cur = enemies.get(j);
				if(!cur.active) continue;
				if(cur.target) {
					cur.active = false;
					kill++;
				}
			}
//			System.out.println(kill);
			
			//적 이동
			row--;
			for(int i = 0; i < enemies.size(); i++) {
				Enemy cur = enemies.get(i);
				if(!cur.active) continue;
				if(cur.x >= row) cur.active = false;	
			}
			
			//종료 조건 판단
			for(int i = 0; i < enemies.size(); i++) {
				Enemy cur = enemies.get(i);
				if(cur.active) break;
				if(i == enemies.size()-1) return kill;
			}
		}
		
	}
}