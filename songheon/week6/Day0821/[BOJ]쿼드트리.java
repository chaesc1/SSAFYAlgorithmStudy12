import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] map;
	static String answer = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
	
		search(0, 0, n);
		System.out.print(answer);
	}
	
	static void search(int r, int c, int size) {
		int sum = 0;
		for(int x = r; x < r + size; x++) {
			for(int y = c; y < c + size; y++) {
				sum += map[x][y];
			}
		}
	
		if(sum == 0) {
			answer += "0";
		}
		else if(sum == size * size) {
			answer += "1";
		}
		else {
			int half = size / 2;
			answer += "(";
			search(r, c, half);
			search(r, c + half, half);
			search(r + half, c, half);
			search(r + half, c + half, half);
			answer += ")";
		}
	
	}
}