//BOJ 17070 파이프 옮기기 1 골프5
//처음에 30분 동안 생각했지만 아이디어를 떠올리지 못했다. - 찾아보니 DP or DFS로 푸는거였음
// DFS로 결정하니 생각이 정리되며 어떻게 해야할지 방향을 정함
// 방향에 따라 0, 1, 2로 구별하여 switch를 활용하고 대각선은 모든 방향에서 갈 수 있어서
// 공통으로 따로 빼주었다.
import java.io.*;
import java.util.*;

public class BOJ17070 {
	static int N;
	static int home[][];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		home = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		dfs(0, 1, 0);
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void dfs(int x, int y, int direction) {
//		System.out.println(x+" "+y+" "+direction);
		if(x == N-1 && y == N-1) {
			ans++;
			return;
		}
		
		//현 방향에 따라서 조건문을 나눔
		switch(direction) {
		case 0: //가로일 경우 갈 수 있는 경우 동쪽, 대각선
			if(y+1 < N && home[x][y+1] == 0) {
				dfs(x, y+1, 0);
			}
			break;
		case 1: //세로일 경우 갈 수 있는 경우 남쪽, 대각선
			if(x+1 < N && home[x+1][y] == 0) {
				dfs(x+1, y, 1);
			}
			break;
		case 2: //대각선일 경우 갈 수 있는 경우 동쪽, 남쪽, 대각선
			if(y+1 < N && home[x][y+1] == 0) {
				dfs(x, y+1, 0);
			}
			if(x+1 < N && home[x+1][y] == 0) {
				dfs(x+1, y, 1);
			}
			break;
		}
		
		//스위치문 끝나고 대각선은 공통이니 모든 케이스에 경우의 수로 검사해줌
		//대각선은 모두가 갈 수 있으니 공통으로 뺀거
		if(x+1 < N && y+1 < N && home[x][y+1] == 0 && home[x+1][y] == 0 && home[x+1][y+1] == 0) { 
			dfs(x+1, y+1, 2);
		}
	}
}
