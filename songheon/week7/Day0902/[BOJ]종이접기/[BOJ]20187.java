import java.io.*;
import java.util.*;

public class Main {
	
	static int k;
	static char[] command;
	static int pos;
	static int map[][];
	static StringBuilder sb;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// input
		k = Integer.parseInt(br.readLine());
		size = (int) Math.pow(2, k);
		command = new char[2 * k];
		map = new int[size][size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2 * k; i++) {
			command[i] = st.nextToken().charAt(0);
		}
		pos = Integer.parseInt(br.readLine());
		
		
		solve(0, 0, 0, size, size);
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void solve(int x, int y, int comm, int h, int v) {
		if(comm == 2 * k) {
			map[x][y] = pos;
			return;
		}
//		System.out.println(comm);
		if(command[comm] == 'R') {
			solve(x, y + v/2, comm + 1, h, v/2);
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < v/2; j++) {
					int cur = map[x + i][y + v - 1 - j];
					if(cur == 0 || cur == 2)
						map[x + i][y + j] = (cur + 1) % 4;
					else
						map[x + i][y + j] = (cur + 3) % 4;
				}
			}
		}
		else if(command[comm] == 'L') {
			solve(x, y, comm + 1, h, v/2);
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < v/2; j++) {
					int cur = map[x + i][y + v/2 - 1 - j];
					if(cur == 0 || cur == 2)
						map[x + i][y + v/2 + j] = (cur + 1) % 4;
					else
						map[x + i][y + v/2 + j] = (cur + 3) % 4;
				}
			}
		}
		else if(command[comm] == 'U') {
			solve(x, y, comm + 1, h/2, v);
			for(int i = 0; i < h/2; i++) {
				for(int j = 0; j < v; j++) {
					map[x + h/2 + i][y + j] = (map[x + h/2 - 1 - i][y + j] + 2) % 4;
				}
			}
		}
		else if(command[comm] == 'D') {
			solve(x + h/2, y, comm + 1, h/2, v);
			for(int i = 0; i < h/2; i++) {
				for(int j = 0; j < v; j++) {
					map[x + i][y + j] = (map[x + h - 1 - i][y + j] + 2) % 4;
				}
			}
		}
		
		
//		for(int i = 0; i < 2 * k; i++) {
//			for(int j = 0; j < 2 * k; j++) {
//				sb.append(map[i][j]).append(" ");
//			}
//			sb.append('\n');
//		}
		
		
	}
}