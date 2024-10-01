//BOJ17142 연구소3, 골드3
//접근 방법 실수
//1. 연구소에 주어진 0, 1, 2 숫자들을 활용해서 바이러스가 다 퍼졌을때 맵에 
//최대 숫자 값이 퍼뜨리는 최소시간으로 잡았다.
//이러지 말고 그냥 bfs 돌때 while 2개 써서 한 개 끝날 때마다 time변수에 1초 추가해주면 되는 거였음(대송헌..)
//2. visited 변수를 초기화 해주는 위치
//느낀점  
// - 처음 문제 풀이를 설계할 때 쉽게 생각할 수 있는 부분을 어렵게 생각해서 셀프 하드모드를 하고
// 있을 때가 많은 거 같다. (결국 많이 풀어보는 수밖에 없다..!)
// 시간 : 2시간 + 다음 날 2시간 30분 = 4시간 30분 걸림
import java.io.*;
import java.util.*;

public class BOJ17142 {
	static class Virus {
		int x;
		int y;
		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, ans;
	static int virusNum;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] VirusPick;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE; //실수 1. 여기서 최소값 구해야해서 최대값으로 초기화해줘야함
		map = new int[N][N];
		virusNum = 0;
		
		int empty = 0;
		Virus[] arr = new Virus[10];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 2) {
					//바이러스 추가
					arr[virusNum++] = new Virus(i, j);
				} else if(num == 0) {
					empty++;
				}
				map[i][j] = num;
			}
		}
		VirusPick = new boolean[virusNum];
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		if(empty == 0) { //빈 칸 없을 때 예외 처리
			ans = 0;
		}else {
			combination(arr, 0, virusNum, 0);			
		}
		
		if(ans == Integer.MAX_VALUE) { //바이러스 다 못 버텼을 때 예외 처리
			ans = -1;
		}
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void combination(Virus[] arr, int start, int VirusNum, int cnt) {
		if(M == cnt) {
			// 조합 확인하기 
//			print(arr, VirusNum);
			visited  = new boolean[N][N];
			BFS(arr, VirusNum);
			return;
		}
		
		for(int i = start; i < VirusNum; i++) {
			VirusPick[i] = true;
			combination(arr, i+1, VirusNum, cnt + 1);
			VirusPick[i] = false;
		}
	}
	
	static void BFS(Virus[] arr, int n) {
		int time = 0;
		int[][] temp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 2) { // 바이러스 일때
					temp[i][j] = map[i][j]; //바이러스 활성화
					visited[i][j] = true; //빈칸 아니라서 방문처리 해둠
					//활성 바이러스가 비활성화 바이러스가 있는 칸에 갈 수 있게 방문처리 안함
				} 
				else if(map[i][j] == 1) {
					temp[i][j] = map[i][j];
					visited[i][j] = true; //빈칸 아니라서 방문처리 해둠
				} 
				else {
					temp[i][j] = map[i][j];					
				}
			}
		}
		
		Queue<Virus> q = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			if(VirusPick[i]) {
				q.add(arr[i]); //바이러스 퍼지기 위해 큐에 추가
				visited[arr[i].x][arr[i].y] = true;
			}
		}
		
		int VirusTimeCheck = 0;
		while(!q.isEmpty()) {
			
			//######바이러스가 퍼지는 1초를 볼 수 있게 해줌 (중요) #############
			int qsize = q.size(); 
//			System.out.println(qsize);
			
			//다음 순서에 체크해야할 칸이 비활성화 바이러스들 뿐일 때 시간은 포함되지 않는다.
			//하지만 비활성화 바이러스들 뿐인데 아직 빈칸 0 남아있으면 계속 검사하고 시간도 그대로 간다.
			if(VirusTimeCheck == qsize) {
				boolean check = true;
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(temp[i][j] == 0) {
							check = false;
							break;
						}
					}
				}
				if(check) time--;
			}
			VirusTimeCheck = 0;
			
			while(qsize > 0) {
				Virus vi = q.poll();
				int curX = vi.x;
				int curY = vi.y;
				
				for(int dic = 0; dic < 4; dic++) {
					int nextX = curX + dx[dic];
					int nextY = curY + dy[dic];
					if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
					
					//비활성화된 바이러스를 활성화시키는데에 드는 시간은 포함되지 않는 예외 케이스
					if(visited[nextX][nextY] && temp[nextX][nextY] == 2) {
						temp[nextX][nextY] = 1;
						q.add(new Virus(nextX, nextY));
						VirusTimeCheck++;
						continue;
					} else if(visited[nextX][nextY]) continue;
					
//					temp[nextX][nextY] = temp[curX][curY] + 1;
					temp[nextX][nextY] = 1;
					q.add(new Virus(nextX, nextY));
					visited[nextX][nextY] = true;
				}
				qsize--;
			}
			time++;
		}
		
		time--; //바이러스가 다 퍼진 상태에서 한 번 더 주위를 확인하느라 시간 1초 줄여야한다.
//		System.out.println("시간 : "+time);
		
		boolean flag = true;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(temp[i][j] == 0) {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			ans = Math.min(time, ans);
		}
		
	}
	
	//조합 확인 코드
	static void print(Virus[] arr, int n) {
		for(int i = 0; i < n; i++) {
			if(VirusPick[i]) {
				System.out.println(arr[i].x + " "+ arr[i].y + " ------ ");
			}
		}
	}
}

