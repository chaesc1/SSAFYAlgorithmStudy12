//BOJ3190 뱀, 골드4
//Deque(처음 사용해봄), HashMap 여러 자료구조 사용
//Deque를 처음 사용하며 익숙해지는 시간이 필요했다.
import java.io.*;
import java.util.*;

public class BOJ3190 {
	static int[][] board;
	static int N, K, L, cnt;
	static int[] dx = {0, 1, 0, -1}; //우 하 좌 상
	static int[] dy = {1, 0, -1, 0}; //시계방향 회전
	static int[] direction;
	static HashMap<Integer, Character> map = new HashMap<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		cnt = 0;
		
		//board 값이 0이면 빈칸, 1이면 뱀있는거, 2는 사과있을때
		board = new int[N+1][N+1];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			board[row][col] = 2;  
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				bw.write(board[i][j] + " ");
//			}
//			bw.write("\n");
//		}
		
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int dirc = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			map.put(dirc, ch);
		}
		
		baaam();
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				bw.write(board[i][j] + " ");
//			}
//			bw.write("\n");
//		}
		
		bw.write(cnt+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void baaam() {
		Deque<int[]> deq = new ArrayDeque<>();
		int dir = 0; //방향 이동은 오른쪽부터
		deq.add(new int[] {1, 1});
		board[1][1] = 1;
		
		while(true) {
			int[] cur = deq.peekLast();
			int curX = cur[0];
			int curY = cur[1];
			
//			System.out.println(cnt + " - " + curX +" "+curY);
			//방향 정보 시간 주어졌을 때
			if(map.containsKey(cnt)) {
				if(map.get(cnt) == 'D') { //시계방향 회전
					dir = (dir+1) % 4;
				}else if(map.get(cnt) == 'L') { //반시계 방향으로 회전
					if(dir - 1 == -1) {
						dir = 3;
					}else {
						dir -= 1;
					}
				}
			}
			
			//다음 방향 구하기
			int dirX = curX + dx[dir];
			int dirY = curY + dy[dir];
			
			
			//벽에 부딪힌거
			if(dirX <= 0  || dirY <= 0 || dirX > N || dirY > N) {
				cnt++;
				break;
			}
			
			//자기 몸에 부딪힌거
			if(board[dirX][dirY] == 1) {
				cnt++;
				break;
			}
			
			//이동한 칸에 사과 있다면
			if(board[dirX][dirY] == 2) {
				//사과 먹었으니 꼬리는 안움직이고 머리만 이동한 상태
				board[dirX][dirY] = 1;
				deq.add(new int[] {dirX, dirY});
				
//				board[curX][curY] = 1; // 꼬리를 그대로 고정시킨다.
//				deq.add(cur); // 꼬리를 그대로 고정시킨다.
				
			}else if(board[dirX][dirY] == 0) {
				//머리를 다음칸에 위치시킨다.
				board[dirX][dirY] = 1;
				deq.add(new int[] {dirX, dirY});
				
				//몸길이를 줄여 꼬리가 위치한 칸을 비워줌
				int[] del = deq.poll();
				int x = del[0];
				int y = del[1];
				board[x][y] = 0;
			}
			cnt++;
		}
	}
}
