import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int m;
	static int map[][];
	static int accum[][];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n+1][n+1];
		accum = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				accum[i][j] = accum[i-1][j] + accum[i][j-1] - accum[i-1][j-1] + map[i][j];
			}
		}
		
		for(int i = 0; i < m; i++) {
			int sx = sc.nextInt();
			int sy = sc.nextInt();
			int ex = sc.nextInt();
			int ey = sc.nextInt();
			
			int sum = accum[ex][ey] - accum[sx-1][ey] - accum[ex][sy-1] + accum[sx-1][sy-1];
			
			sb.append(sum).append('\n');
		}
		System.out.print(sb);
	}
}