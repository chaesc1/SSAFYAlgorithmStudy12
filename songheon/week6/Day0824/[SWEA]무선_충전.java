import java.util.*;
import java.io.*;

public class Solution {

	static int tc, answer;
	static int m, a;
	static int m1[], m2[];
	static Battery bc[];
	static PriorityQueue<Info> pq1, pq2;
	static int dx[] = {0, -1, 0, 1, 0};
	static int dy[] = {0, 0, 1, 0, -1}; //제자리, 상, 우, 좌, 하
	
	static class Info{
		int idx;
		int val;
		public Info(int idx, int val) {
			super();
			this.idx = idx;
			this.val = val;
		}
	}
	
	static class Battery{
		int posx;
		int posy;
		int range;
		int value;
		public Battery(int posy, int posx, int range, int value) {
			super();
			this.posx = posx;
			this.posy = posy;
			this.range = range;
			this.value = value;
		}
	}
	
	static class User{
		int x;
		int y;
		public User(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			
			answer = 0;
			User u1 = new User(1, 1);
			User u2 = new User(10, 10);
			
			m = sc.nextInt();
			a = sc.nextInt();
			
			m1 = new int[m + 1];
			m2 = new int[m + 1];
			bc = new Battery[a];
			pq1 = new PriorityQueue<Info>((i1, i2) -> i2.val - i1.val);
			pq2 = new PriorityQueue<Info>((i1, i2) -> i2.val - i1.val);
			
			m1[0] = 0; m2[0] = 0;
			for(int i = 1; i <= m; i++) {
				m1[i] = sc.nextInt();
			}
			for(int i = 1; i <= m; i++) {
				m2[i] = sc.nextInt();
			}
			for(int i = 0; i < a; i++) {
				bc[i] = new Battery(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			}
			
			for(int move = 0; move <= m; move++) {
				
				
				u1.x += dx[m1[move]];
				u1.y += dy[m1[move]];
				u2.x += dx[m2[move]];
				u2.y += dy[m2[move]];
				
				for (int i = 0; i < a; i++) {
					int dist1 = Math.abs(bc[i].posx - u1.x) + Math.abs(bc[i].posy - u1.y);
					if (dist1 <= bc[i].range)
						pq1.add(new Info(i, bc[i].value ));
					int dist2 = Math.abs(bc[i].posx - u2.x) + Math.abs(bc[i].posy - u2.y);
					if (dist2 <= bc[i].range)
						pq2.add(new Info(i, bc[i].value ));
				}
				
				//calculate the sum of charging value
				int char1, char2;
				//if two are empty
				if (pq1.isEmpty() && pq2.isEmpty()) {
					char1 = 0; char2 = 0;
				}
				//if one of them is empty
				else if (pq1.isEmpty() && !pq2.isEmpty()) {
					char1 = 0;
					char2 =  pq2.peek().val;
				}
				else if (!pq1.isEmpty() && pq2.isEmpty()) {
					char1 = pq1.peek().val;
					char2 = 0;
				}
				//both are not empty
				else {
					//if the top value is diff
					if (pq1.peek().idx != pq2.peek().idx) {
						char1 = pq1.peek().val;
						char2 = pq2.peek().val;
					}
					//if the top value is equal
					else {
						//if two length is 1
						if (pq1.size() == 1 && pq2.size() == 1) {
							char1 = pq1.peek().val / 2;
							char2 = pq2.peek().val / 2;
						}
						//one of them length is bigger than 1
						else if (pq1.size() == 1 && pq2.size() != 1) {
							pq2.poll();
							char1 = pq1.peek().val;
							char2 = pq2.peek().val;
						}
						else if (pq1.size() != 1 && pq2.size() == 1) {
							pq1.poll();
							char1 = pq1.peek().val;
							char2 = pq2.peek().val;
						}
						//both of them length is bigger than 1
						else {
							int tmp = pq1.peek().val;
							pq1.poll(); pq2.poll();
							if (pq1.peek().val >= pq2.peek().val) {
								char1 = pq1.peek().val;
								char2 = tmp;
							}
							else {
								char1 = tmp;
								char2 = pq2.peek().val;
							}
						}
					}
					
				}
			
				//cout << "user1 " << char1 << ", user2 " << char2 << '\n';
				answer = answer + char1 + char2;

				while (!pq1.isEmpty()) {
					pq1.poll();
				}
				while (!pq2.isEmpty()) {
					pq2.poll();
				}
			}
			
			sb.append("#").append(t).append(" ").append(answer).append('\n');
		}
		
		System.out.print(sb);
	}
}