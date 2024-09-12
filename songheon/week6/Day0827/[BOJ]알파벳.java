package day0827;

import java.util.*;
import java.io.*;

//12412kb	928ms

public class Main_B_1987_알파벳_한송헌 {
	//알파벳 크기 만큼의 boolean 배열 생성
		//처음부터 A순서대로 생각
	//dfs 진행하면서 방문한 알파벳은 true로 바꿈
		//파라메터로 현재 이동한 거리 기록
		//원복 필요
		//false인 알파벳만 이동
	//더 이상 이동할 알파벳이 없다면 현재까지의 거리와 최댓값 기록
	
	static int r, c;
	static char map[][];
	static boolean alph[];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		alph = new boolean[26];
		answer = 1;
		
		for(int i = 0; i < r; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < c; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		alph[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		System.out.print(answer);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= r || y < 0 || y >= c;
	}
	
	static void dfs(int x, int y, int len) {
		answer = Math.max(len, answer);
		for(int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if(OOB(tx, ty)) continue;
			if(alph[map[tx][ty] - 'A']) continue;
			alph[map[tx][ty] - 'A'] = true;
			dfs(tx, ty, len + 1);
			alph[map[tx][ty] - 'A'] = false;
		}
	}
	
	
}
