import java.io.*;
import java.util.*;

public class Solution {
	static int tc;
	static int n;
	static Pair pos[];
	static long answer;
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
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
			sb.append("#").append(t).append(" ");
			
			answer = Long.MAX_VALUE;
			n = sc.nextInt();
			pos = new Pair[n];
			for(int i = 0; i < n; i++) {
				pos[i] = new Pair(sc.nextInt(), sc.nextInt());
			}
			combi(0, 0, 0, 0, 0); // order, start, end, x-vec, y-vec
			
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void combi(int order, int start, int end, long x, long y) {
		if(order == n) {
			answer = Math.min(answer, x * x + y * y);
			return;
		}
		
		//start
		if(start < n/2)
			combi(order + 1, start + 1, end, x - pos[order].x, y - pos[order].y);
		
		//end
		if(end < n/2)
			combi(order + 1, start, end + 1, x + pos[order].x, y + pos[order].y);
	}
}