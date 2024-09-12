import java.util.*;
import java.io.*;

public class Main {
	//파이프 경로가 겹치는 경우 해당 파이프 경로는 확인할 필요 없음 (백트래킹, 가지치기)
	//방문한 경로는 다시 방문하지 않도록 처리
	//맨 위의 행부터 탐색 시, 아래의 영역에 최대한 영향을 주지 않도록 할 필요 (최대의 개수 구해야 함)
		//위쪽, 가운데, 아래쪽 순서로 탐색 (그리디)
	//각 행마다 dfs 진행하면서 경로 진행
		//파이프의 경로를 완성한 경우, 해당 경로와 겹치면 안되기 때문에 dfs 종료
	
	static int r, c;
	static boolean map[][];
	static int answer;
	static int dx[] = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new boolean[r][c];
		for(int i = 0; i < r; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < c; j++) {
				 if(tmp.charAt(j) == '.') {
					 map[i][j] = true; //지나갈 수 있음
				 }
				 else map[i][j] = false; //지나갈 수 없음
			}
		}
		
		
		for(int i = 0; i < r; i++) {
			if(dfs(i, 0))
				answer++;
		}
		
		System.out.println(answer);
	}
	
	static boolean OOB(int x) {
		if(x >= 0 && x < r)
			return false;
		return true;
	}
	
	static boolean dfs(int row, int order) {
		if(order == c-1) {
			return true;
		}
		
		for(int i = 0; i < 3; i++) {
			if(!OOB(row + dx[i]) && map[row+dx[i]][order + 1]) {
				map[row + dx[i]][order + 1] = false;
				if(dfs(row+ dx[i], order + 1))
					return true;
			}
		}
		return false;
		
	}
}