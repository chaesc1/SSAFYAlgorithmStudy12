//BOJ16933 벽 부수고 이동하기3, 골드1
//기존 벽 부수고 이동하기에서 벽을 부술 수 있는 수가 1개에서 K개로 변경되었다.
//check 배열을 4차원 배열로 받아서 [낮 or 밤][K+1][][] 만큼 해준다.
//각 벽을 깨순 경우에 따라 거리를 계산해준다.
//낮과 밤 조건 추가, 벽은 낮에만 부술 수 있음
//이동하지않고 머물르기 가능
//중요한건 벽을 만났을 때 낮이냐 아니냐가 중요한걸라 4차원 경로 기록해줄 때 낮과 밤 둘 다 
//기록해줘도 괜찮은듯? 시간 복잡도가 줄어들었다.
import java.io.*;
import java.util.*;

public class BOJ16933 {
	static int N, M, K, ans;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		ans = Integer.MAX_VALUE;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int j = 1; j <= M; j++) {
				char ch = temp.charAt(j-1);
				String tmp = Character.toString(ch);
				map[i][j] = Integer.parseInt(tmp);
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		ans = bfs();
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		int[][][][] check = new int[2][K+1][N+1][M+1]; //낮밤, 벽부수기 가능, x, y
		q.add(new int[] {0, 0, 1, 1}); //시작 지점(밤, 벽 0 부숨, 1, 1)
		check[0][0][1][1] = 1; //시작 경로
		
		
		while(!q.isEmpty()) {
			//큐 값 받고
			int[] cur = q.poll();
			int sun = cur[0];
			int w = cur[1]; //벽 몇번 뿌서졌는지
			int curX = cur[2];
			int curY = cur[3];
			
			if(curX == N && curY == M) return check[sun][w][curX][curY];
			
			//for문 방향체크
			for(int dic = 0; dic < 4; dic++) {
				int nextX = curX + dx[dic];
				int nextY = curY + dy[dic];
				int nextSun;
				if(sun == 0) nextSun = 1;
				else nextSun = 0;
				
				if(nextX <= 0 || nextY <= 0 || nextX > N || nextY > M) continue;
				
				//다음 노드가 벽이 아닐 때(0)
				if(map[nextX][nextY] == 0) {
					if(check[nextSun][w][nextX][nextY] == 0) { //이걸 빼먹고 안해줬음
						q.add(new int[] {nextSun, w, nextX, nextY});
						check[nextSun][w][nextX][nextY] = check[sun][w][curX][curY] + 1;
						check[sun][w][nextX][nextY] = check[sun][w][curX][curY] + 1; //이거 넣으면 되는지 실험
						if(nextX == N && nextY == M) {
							return check[nextSun][w][nextX][nextY];
						}
					}
				}
				else if(map[nextX][nextY] == 1 && nextSun == 1) { //벽 만났을 때 & 낮에만 부술 수 있다.
					if(w < K) { //벽을 더 깰 수 있을 때
						if(check[nextSun][w+1][nextX][nextY] == 0) { //벽 부술 때도 이거 해줘야함 - 이걸 빼먹고 안해줬음
							q.add(new int[] {nextSun, w+1, nextX, nextY});
							check[nextSun][w+1][nextX][nextY] = check[sun][w][curX][curY] + 1;
							check[sun][w+1][nextX][nextY] = check[sun][w][curX][curY] + 1; //이거 넣으면 되는지 실험
						}
					}else { //더 이상 벽을 못 깰 때
						continue;
					}
				}else if(map[nextX][nextY] == 1 && nextSun == 0) {  //머물러야함
					q.add(new int[] {nextSun, w, curX, curY});
					check[nextSun][w][curX][curY] =  check[sun][w][curX][curY] + 1;
				}
			}
		}
		//q가 다 비었는데 N, M 위치에 도달 못했을 경우
		return -1;
	}
}
