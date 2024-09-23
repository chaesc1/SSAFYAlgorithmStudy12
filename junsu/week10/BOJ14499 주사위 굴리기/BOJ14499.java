//BOJ14499 주사위 굴리기, 골드4
//아이디어 
//- 주사위 놓인 위치에서 1차원 배열을 펼친다.
//- 델타 탐색 느낌은 아니었다. 그냥 idx를 활용해 배열 옮기기였음
//- 각 idx 별로 위치를 고정해두고 1차원 주사위 배열 값 위치를 변경한다.
import java.io.*;
import java.util.*;

public class BOJ14499 {
	static int[] dice;
	static int[][] map;
	static int N, M, x, y, K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dice = new int[6+1];
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int direction = Integer.parseInt(st.nextToken());
			int ans = tumble(direction);
			if(ans == -1) //바깥으로 이동하는 경우 명령 무시로 출력하지 않는다.
				continue;
			bw.write(ans+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	static int tumble(int direction) {
		int tmp;
		switch(direction) {
		case 1: //동쪽
			y += 1; //동쪽으로 이동이라 좌표값 변경
			if(y >= M) {
				//바깥으로 이동하는 경우 명령 무시로 출력하지 않는다.
				y -= 1;
				return -1; 
			}
			tmp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
			break;
		case 2: //서쪽
			y -= 1; // 좌표값 이동
			if(y < 0) {
				y += 1;
				return -1;
			}
			tmp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = tmp;
			break;
		case 3: //북쪽
			x -= 1; // 좌표값 이동
			if(x < 0) {
				x += 1;
				return -1;
			}
			tmp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
			break;
		case 4: //남쪽
			x += 1; // 좌표값 이동
			if(x >= N) {
				x -= 1;
				return -1;
			}
			tmp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
			break;
		}
		
		if(map[x][y] == 0) {
			//이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다. 
			map[x][y] = dice[1];
		} else {
			//0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
			dice[1] = map[x][y];
			map[x][y] = 0;
		}
		
		return dice[6];
	}
	
}
