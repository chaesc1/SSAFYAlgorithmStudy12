import java.util.*;
import java.io.*;

public class Solution {
	
	static int tc;
	static int n;
	static int map[][];
	static int buffer[][];
	static int charge[][];
	static String move;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			
			n = sc.nextInt();
			move = sc.next();
			map = new int[n][n];
			buffer = new int[n][n];
			charge = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			
			solve(move);
			
			sb.append("#").append(t).append('\n');
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
		
	}
	
	static void solve(String move) {
		switch(move) {
		case "right":
			moving();
			break;
		case "up":
			clock(1);
			moving();
			cntclock(1);
			break;
		case "left":
			clock(2);
			moving();
			cntclock(2);
			break;
		case "down":
			cntclock(1);
			moving();
			clock(1);
			break;
		}
	}
	
	static void moving() {
		for(int i = 0; i < n; i++) {
			for(int j = n-2; j >= 0; j--) {
				if(map[i][j] == 0)
					continue;
				
				int next = j + 1;
				while(true) {
					if(next >= n) {
						map[i][n-1] = map[i][j];
						map[i][j] = 0;
						break;
					}
					else if(map[i][next] != 0) {
						if(map[i][j] == map[i][next]){
							if(charge[i][next] == 0) {
								charge[i][next] = 1;
								map[i][next] = map[i][next] * 2;
								map[i][j] = 0;
							}
							else {
								map[i][next-1] = map[i][j];
								map[i][j] = 0;
							}
						}
						else {
							if(next -1 != j) {
								map[i][next-1] = map[i][j];
								map[i][j] = 0;
								
							}
						}
						break;
						
					}
					else
						next++;
				}
			}
		}
	}
	static void clock(int time) {
		while(time != 0) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					buffer[j][n-1-i] = map[i][j];
				}
			}
			//옮기기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = buffer[i][j];
				}
			}
			time--;
		}
		
	}
	static void cntclock(int time) {
		while(time != 0) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					buffer[n-1-j][i] = map[i][j];
				}
			}
			//옮기기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = buffer[i][j];
				}
			}
			time--;
		}
	}
}